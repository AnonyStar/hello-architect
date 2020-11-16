package online.icode.chain.v3;

import online.icode.chain.Request;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:31
 */
@FunctionalInterface
public interface Processer {

    void process(Request request);

}
