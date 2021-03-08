package online.icode.eshop.inventory.model.vo;

import lombok.Data;

/**
 * @author: zhoucx
 * @time: 2021/3/7 14:15
 */
@Data
public class Response<T> {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;

    private T data;

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response ok(Object data) {
        return new Response<>("200", SUCCESS, data);
    }
    public static Response ok() {
        return new Response<>("200", SUCCESS, null);
    }
    public static Response error(Object data) {
        return new Response<>("500", FAILURE, data);
    }
    public static Response error(String message) {
        return new Response<>("500", message);
    }

}
