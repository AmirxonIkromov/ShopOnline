package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.internetmagazin.payload.BasketDTO;
import uz.pdp.internetmagazin.service.BasketService;

@RestController
@RequiredArgsConstructor
public class BasketControllerImpl implements BasketController {

    private final BasketService basketService;

    @Override
    public ResponseEntity<?> add(BasketDTO basketDTO) {
        return basketService.add(basketDTO);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return basketService.getAll();
    }

    @Override
    public ResponseEntity<?> getOne(Integer id) {
        return basketService.get(id);
    }

    @Override
    public ResponseEntity<?> edit(Integer id, BasketDTO basketDTO) {
        return basketService.edit(id, basketDTO);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return basketService.delete(id);
    }
}
