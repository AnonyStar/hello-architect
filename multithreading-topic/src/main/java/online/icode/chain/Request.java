package online.icode.chain;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:29
 */

public class Request {

    private String name;

    public Request() {
    }

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
