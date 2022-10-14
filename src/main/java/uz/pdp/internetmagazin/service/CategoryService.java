package uz.pdp.internetmagazin.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.internetmagazin.payload.CategoryDTO;

public interface CategoryService {

    ResponseEntity<?> add(CategoryDTO categoryDTO);

    ResponseEntity<?> getAll();

    ResponseEntity<?> edit(Integer id, CategoryDTO categoryDTO);

    ResponseEntity<?> delete(Integer id);
}
