package uz.pdp.internetmagazin.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.internetmagazin.payload.MagazinDTO;

public interface MagazineService {
    ResponseEntity<?> add(MagazinDTO magazinDTO);

    ResponseEntity<?> getAll();

    ResponseEntity<?> get(Integer id);

    ResponseEntity<?> edit(Integer id, MagazinDTO magazinDTO);

    ResponseEntity<?> delete(Integer id);
}
