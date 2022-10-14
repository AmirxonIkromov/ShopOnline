package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Photo;
import uz.pdp.internetmagazin.entity.Product;


public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
