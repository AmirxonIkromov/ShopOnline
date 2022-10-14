package uz.pdp.internetmagazin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.internetmagazin.payload.MagazinDTO;

@RequestMapping("/magazine")
public interface MagazineController {

    @PostMapping
    ResponseEntity<?> add(@RequestBody MagazinDTO magazinDTO);

    @GetMapping
    ResponseEntity<?> getAll();

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable Integer id);



    @PutMapping("/{id}")
    ResponseEntity<?> edit(@PathVariable Integer id,
                   @RequestBody MagazinDTO magazinDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);


}
