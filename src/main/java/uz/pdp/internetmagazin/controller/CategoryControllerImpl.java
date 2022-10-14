package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.internetmagazin.payload.CategoryDTO;
import uz.pdp.internetmagazin.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<?> add(CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }


    @Override
    public ResponseEntity<?> getAll() {
        return categoryService.getAll();
    }


    @Override
    public ResponseEntity<?> edit(Integer id, CategoryDTO categoryDTO) {
        return categoryService.edit(id, categoryDTO);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return categoryService.delete(id);
    }

}
