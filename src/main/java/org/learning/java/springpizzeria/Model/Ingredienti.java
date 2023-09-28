package org.learning.java.springpizzeria.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredienti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    //getter e setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
