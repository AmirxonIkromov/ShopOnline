package uz.pdp.internetmagazin.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInDTO {

    @NotBlank(message = "Oka usernamme bo'sh bo'lmasinda")
    private String username;

    @NotBlank(message = "Oka parol bo'sh bo'lmasinda")
    private String password;
}
