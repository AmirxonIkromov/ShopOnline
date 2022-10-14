package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Category;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);
}
