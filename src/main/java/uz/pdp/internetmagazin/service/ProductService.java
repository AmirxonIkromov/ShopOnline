package uz.pdp.internetmagazin.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.exception.InputDataExistsException;

import java.io.IOException;

public interface ProductService {
    ResponseEntity<?> add(MultipartHttpServletRequest request) throws InputDataExistsException;
    ResponseEntity<?>  addList(MultipartHttpServletRequest request) throws InputDataExistsException, IOException;

    ResponseEntity<?>  getAll(Integer page);


    ResponseEntity<?>  edit(Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws InputDataExistsException;

    ResponseEntity<?>  delete(Integer id);

    ResponseEntity<?>  filter(String name, String priceOT, String priceDO, Integer id);
}
