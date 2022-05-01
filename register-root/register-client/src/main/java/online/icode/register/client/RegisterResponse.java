package online.icode.register.client;

import lombok.Data;

@Data
public class RegisterResponse {

    public final static String SUCCESS = "success";

    public final static String FAILURE ="failure";

    private String status;

}
