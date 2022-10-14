package uz.pdp.internetmagazin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean isActive = true;

    @ManyToOne
    private Magazine magazin;

    @ManyToOne
    private User user;
}
