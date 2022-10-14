package uz.pdp.internetmagazin.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Integer getId() {
        return id;
    }

    public Role(RoleEnum name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
