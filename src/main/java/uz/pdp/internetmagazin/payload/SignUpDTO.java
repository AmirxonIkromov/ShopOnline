package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private Long id;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;


}
