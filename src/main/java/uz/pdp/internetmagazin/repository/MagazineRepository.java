package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Magazine;


public interface MagazineRepository extends JpaRepository<Magazine,Integer> {


    boolean existsByName(String name);
}
