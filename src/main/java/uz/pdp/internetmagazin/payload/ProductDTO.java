package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.internetmagazin.entity.Photo;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private Double price;

    private Integer category_id;

    private Photo photo;


    public ProductDTO(Integer id, String name, Double price, Integer id1) {

    }
}
