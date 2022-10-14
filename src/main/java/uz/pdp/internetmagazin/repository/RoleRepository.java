package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Role;
import uz.pdp.internetmagazin.entity.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleEnum roleName);

}
