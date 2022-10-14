package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Photo_content;
import uz.pdp.internetmagazin.entity.Product;

import java.util.Optional;


public interface Photo_ContentRepository extends JpaRepository<Photo_content,Integer> {

    Optional<Photo_content> findByPhotoId(Integer id);
}
