package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

    private Integer id;

    private String name;

    private Integer magazin_id;


}
