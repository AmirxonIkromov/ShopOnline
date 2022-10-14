package uz.pdp.internetmagazin.payload;

import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String fullName;
}
