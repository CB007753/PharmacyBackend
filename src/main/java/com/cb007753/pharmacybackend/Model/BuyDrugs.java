package com.cb007753.pharmacybackend.Model;

import javax.persistence.*;

//this model is used for drugs available in supplier market.
@Entity
@Table(name = "buy_drugs")
public class BuyDrugs {

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

    public BuyDrugs(Long id, String name, String description, int price, String unit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public BuyDrugs(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BuyDrugs() {

    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
