package com.kmood.word;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhoucx
 * @time: 2021/3/16 9:42
 */
public class DataContext {

    private DataContext(){

    }

    private static Map<String, String> vfEqnMap = new HashMap<>();


    static {
        //初始化
        initVfEqnMap();
    }


    /**
     *   <v:f> </v:f>  表签中 eqn 属性的 集合
     */
    private static void initVfEqnMap(){
        vfEqnMap.put("eqn1", "if lineDrawn pixelLineWidth 0");
        vfEqnMap.put("eqn2", "sum @0 1 0");
        vfEqnMap.put("eqn3", "sum 0 0 @1");
        vfEqnMap.put("eqn4", "prod @2 1 2");
        vfEqnMap.put("eqn5", "prod @3 21600 pixelWidth");
        vfEqnMap.put("eqn6", "prod @3 21600 pixelHeight");
        vfEqnMap.put("eqn7", "sum @0 0 1");
        vfEqnMap.put("eqn8", "prod @6 1 2");
        vfEqnMap.put("eqn9", "prod @7 21600 pixelWidth");
        vfEqnMap.put("eqn10", "sum @8 21600 0");
        vfEqnMap.put("eqn11", "prod @7 21600 pixelHeight");
        vfEqnMap.put("eqn12", "sum @10 21600 0");
    }


    public static Map<String, String> getVfEqnMap() {
        return vfEqnMap;
    }
    public static String getVfEqn(String key) {
        return vfEqnMap.get(key);
    }
}
