package uz.pdp.internetmagazin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.internetmagazin.payload.BasketDTO;

@RequestMapping("/basket")
public interface BasketController {

    @PostMapping()
    ResponseEntity<?> add(@RequestBody BasketDTO basketDTO);

    @GetMapping
    ResponseEntity<?> getAll();

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable Integer id);



    @PutMapping("/{id}")
    ResponseEntity<?> edit(@PathVariable Integer id,
                   @RequestBody BasketDTO basketDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);


}
