package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket,Integer> {
}
