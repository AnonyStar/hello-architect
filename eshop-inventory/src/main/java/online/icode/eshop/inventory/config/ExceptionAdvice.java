package online.icode.eshop.inventory.config;

import online.icode.eshop.inventory.model.vo.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: zhoucx
 * @time: 2021/3/7 14:26
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Response handlerException(Exception e) {
        e.printStackTrace();
        return Response.error(e.getMessage());
    }
}
