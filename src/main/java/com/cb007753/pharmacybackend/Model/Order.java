package com.cb007753.pharmacybackend.Model;

import javax.persistence.*;

//ORDER is a MySQL reserved word, so iam using backticks to enclose the table name order to prevent errors
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long order_id;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private String date;

    @Column(name = "drugname")
    private String drugname;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int qnty;

    @Column(name = "unit")
    private String unit;

    @Column(name = "status")
    private String status;

    @Column(name = "total")
    private int total;


    //Constructors

    public Order(Long order_id, String email, String date, String drugname, int price, int qnty, String unit, String status, int total) {
        this.order_id = order_id;
        this.email = email;
        this.date = date;
        this.drugname = drugname;
        this.price = price;
        this.qnty = qnty;
        this.unit = unit;
        this.status = status;
        this.total = total;
    }

    public Order(String date, String drugname, String email, int price, int qnty, String status, int total, String unit) {
        this.date = date;
        this.drugname = drugname;
        this.email = email;
        this.price = price;
        this.qnty = qnty;
        this.status = status;
        this.total = total;
        this.unit = unit;
    }

    public Order() {
    }

    //Getter and setters

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
