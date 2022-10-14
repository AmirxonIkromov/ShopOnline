package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagazinDTO {

    private Integer id;

    private String name;

    private String address;

    private String phone;
}
