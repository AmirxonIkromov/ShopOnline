package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.service.ProductService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;


    @Override
    public ResponseEntity<?>  add(MultipartHttpServletRequest request) throws IOException {
        return productService.add(request);
    }

    @Override
    public ResponseEntity<?>  addList(MultipartHttpServletRequest request) throws IOException {
        return productService.addList(request);
    }

    @Override
    public ResponseEntity<?>  getAll(Integer page) {
        return productService.getAll(page);
    }


    @Override
    public ResponseEntity<?>  edit(Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        return productService.edit(id, multipartHttpServletRequest);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return productService.delete(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")

    public ResponseEntity<?>  filter(String name, String priceOT, String priceDO, Integer id) {
        return productService.filter(name,priceOT,priceDO,id);
    }
}
