package pl.pjatk.CarRental_v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    private String model;
    private String color;
    private int productionYear;
    private int pricePerDay;
    private boolean isAvailable = true;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<Rent> transactionsList = new ArrayList<>();

    public Car() {
    }

    public Car(String mark, String model, String color, int productionYear, int pricePerDay) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
        this.pricePerDay = pricePerDay;
    }

    public Car(String mark, String model, String color, int productionYear, int pricePerDay, boolean isAvailable) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public List<Rent> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Rent> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
