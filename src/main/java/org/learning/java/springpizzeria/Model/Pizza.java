package org.learning.java.springpizzeria.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizza")

public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;
    @Column(length = 60, nullable = false, unique = true)
    @Size(min = 5, max = 60)
    @NotBlank
    private String description;
    @Min(0)
    @NotNull
    private BigDecimal price;


    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<Offer> offer;


    //getter e setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }
//metodi

    public boolean availableOffer() {
        if (offer.size() > 0) {
            return true;
        } else {
            return false;
        }


    }

}





