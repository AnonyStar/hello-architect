package online.icode.jvm.classload;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.util.Arrays;

/**
 * @author: zhoucx
 * @time: 2021/1/22 17:25
 */
public class ClassLoader {


    public static void main(String[] args) {
        final URLClassPath classPath = Launcher.getBootstrapClassPath();
        final java.lang.ClassLoader classLoader = Launcher.getLauncher().getClassLoader();
        System.out.println(classLoader);
        System.out.println(Arrays.toString(classPath.getURLs()));
        String s = "Ad";
        switch (s){

        }
    }
}
