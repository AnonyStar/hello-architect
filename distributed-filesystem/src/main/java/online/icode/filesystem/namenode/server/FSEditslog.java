package online.icode.filesystem.namenode.server;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * edits log管理核心组件
 * 1. 维护edits log的双缓冲机制.
 * 2. 批量刷磁盘机制.
 */
public class FSEditslog {


    /**
     * 递增txid 全局唯一值
     */
    private long txidSeq = 0L;

    /**
     * 正在同步到磁盘中的最大txid值
     */
    private long syncMaxTid = 0L;

    /**
     * 是否正在刷盘中
     */
    private volatile Boolean isSyncRunning = false;

    /**
     * 当前是否有线程正在等待刷新下一批的edits log
     */
    private volatile Boolean isWaitSync = false;

    private DoubleBuffer editslogBuffer = new DoubleBuffer();

    private ThreadLocal<Long> localTxid = new ThreadLocal<>();



    public void logEdit(String content) {
        synchronized (this) {
            txidSeq++;
            localTxid.set(txidSeq);
            Editslog editslog = new Editslog(txidSeq, content);
            editslogBuffer.write(editslog);
        }

        //将缓冲队列中的数据刷入到磁盘
        logSync();
    }

    /**
     * 将flushBuffer 中的数据刷到磁盘
     *
     */
    private void logSync() {
        //分段加锁，
        synchronized (this) {
            if (isSyncRunning) {
                //已有线程在刷盘，那么就等待，阻塞

                //如果当前线程中写入的txid 值小于等于目前正在刷入磁盘的txid ，则说明不需要再刷入.
                Long txid = localTxid.get();
                if (txid <= syncMaxTid) {
                    return;
                }

                //判断当前是否有已经等待的刷盘线程，如果有则说明下一批刷盘线程已有了，
                if (isWaitSync) {
                    return;
                }
                //如果没有则，将该线程作为等待执行的线程
                isWaitSync = true;
                while (isSyncRunning) {
                    try {
                        wait(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isWaitSync = false;

            }
            //交换两个缓冲区
            editslogBuffer.readyToSync();
            syncMaxTid = editslogBuffer.getSyncMaxTxid();
            isSyncRunning = true;
        }

        //刷盘
        editslogBuffer.flush();

        synchronized (this) {
            //刷完盘之后需要将 标记位复位，
            isSyncRunning = false;
            notifyAll();
        }
    }


    private static class Editslog {
        private long txid;
        private String content;

        public Editslog(long txid, String content) {
            this.txid = txid;
            this.content = content;
        }

        public long getTxid() {
            return txid;
        }

        public void setTxid(long txid) {
            this.txid = txid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Editslog{" +
                    "txid=" + txid +
                    ", content='" + content + '\'' +
                    '}';
        }
    }


    public static class DoubleBuffer {
        /**
         * 当前的用于写入的缓冲队列，
          */
        private LinkedList<Editslog> currentBuffer = new LinkedList<Editslog>();

        /**
         * 用于刷新到磁盘的缓冲队列.
         */
        private LinkedList<Editslog> flushBuffer = new LinkedList<>();

        /**
         * 写入操作日志
         * @param editslog
         */
        public void write(Editslog editslog) {
            currentBuffer.add(editslog);
        }

        /**
         * 进行缓冲区置换，交换缓冲区.
         */
        public void readyToSync() {
            LinkedList<Editslog> tmp = this.currentBuffer;
            this.currentBuffer = this.flushBuffer;
            this.flushBuffer = tmp;
        }


        /**
         * 将 flushBuffer 中的数据刷入到磁盘中.
         */
        public void flush() {
            try {
                TimeUnit.MILLISECONDS.sleep(900);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("【edits log】-刷磁盘文件中-log:" + flushBuffer.size());
            /*for (Editslog editslog : flushBuffer) {
                System.out.println("【edits log】-刷磁盘文件中-log:" + editslog);
                // IO操作
            }
*/
            flushBuffer.clear();
        }


        public long getSyncMaxTxid() {
            Editslog last = this.flushBuffer.getLast();
            return Objects.isNull(last) ? 0L : last.getTxid();
        }
    }
}
