package online.icode.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: zhoucx
 * @time: 2020/12/22 9:28
 */
public class MetaspaceOOM {

    /*
    -XX:MetaspceSize=10M
    -XX:MaxMetaspaceSize=10M
     */

    /*
    -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     */
    public static void main(String[] args) {
        //通过 cglib 动态生成类，加载到 metaspace 中，这里使用jdk8 ，设置metaspace 的大小为 10M
        int count = 0;
        while (true){
            System.out.println("第" + count + " 个对象创建");
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")){
                        System.out.println(" before run ...");
                        return methodProxy.invokeSuper(o,objects);
                    }
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            final Car car = (Car) enhancer.create();
            car.run();
            count++;
        }
    }

    static class Car{
        public void run(){
            System.out.println("Car runing ...");
        }
    }
}
