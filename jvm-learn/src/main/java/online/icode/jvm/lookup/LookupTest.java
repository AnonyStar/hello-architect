package online.icode.jvm.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @author: zhoucx
 * @time: 2021/1/31 8:41
 */
public class LookupTest {


    public static void main(String[] args) throws Throwable {
        GrandFather obj = new Son();
        obj.thinking();
    }
}


class GrandFather{
    void thinking() throws Throwable {
        System.out.println("i am grandFather...");
    }
}


class Father extends GrandFather{
    @Override
    void thinking() throws Throwable {
        System.out.println("i am father...");
    }
}

class Son extends Father{
    @Override
    void thinking() throws Throwable {
        MethodType mt = MethodType.methodType(void.class);
//        MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
//        try {
//            mh.invoke(this);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        final Field implLookup = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");

        implLookup.setAccessible(true);

        final MethodHandle thinking = ((MethodHandles.Lookup) implLookup.get(null)).findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
        thinking.invoke(this);


    }
}

