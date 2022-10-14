package uz.pdp.internetmagazin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.internetmagazin.payload.CategoryDTO;

@RequestMapping("/category")
public interface CategoryController {

    @PostMapping
    ResponseEntity<?> add(@RequestBody CategoryDTO categoryDTO);

    @GetMapping
    ResponseEntity<?> getAll();


    @PutMapping("/{id}")
    ResponseEntity<?> edit(@PathVariable Integer id,
                   @RequestBody CategoryDTO categoryDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);


}
