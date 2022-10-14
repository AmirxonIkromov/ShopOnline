package uz.pdp.internetmagazin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;


@RequestMapping("/product")
public interface ProductController {

    @PostMapping
    ResponseEntity<?> add(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;
    @PostMapping("/listPr")
    ResponseEntity<?>  addList(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;


    @GetMapping
    ResponseEntity<?>  getAll(@RequestParam Integer page);


    @PutMapping("/{id}")
    ResponseEntity<?>  edit(@PathVariable Integer id,
                    MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;

    @DeleteMapping("/{id}")
    ResponseEntity<?>  delete(@PathVariable Integer id);

    @GetMapping("/filter")
    ResponseEntity<?>  filter(@RequestParam(required = false) String name , @RequestParam(required = false) String priceOT,
                              @RequestParam(required = false) String priceDO , @RequestParam(required = false) Integer id);

}
