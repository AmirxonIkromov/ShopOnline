package uz.pdp.internetmagazin.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.internetmagazin.payload.BasketDTO;

public interface BasketService {
    ResponseEntity<?> add(BasketDTO basketDTO);

    ResponseEntity<?> getAll();

    ResponseEntity<?> get(Integer id);

    ResponseEntity<?> edit(Integer id, BasketDTO basketDTO);

    ResponseEntity<?> delete(Integer id);
}
