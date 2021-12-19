package com.cb007753.pharmacybackend.Model;

import javax.persistence.*;

@Entity
@Table(name = "drugs")
public class Drugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "unit")
    private String unit;



    //Constructors
    public Drugs() {
    }

    public Drugs(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Drugs(Long id, String description, String name, int price, String unit) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    //Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
