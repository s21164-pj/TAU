package pl.pjatk.CarRental_v2.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startingDate = LocalDate.now();
    private LocalDate endDate;
    private int totalPrice;
    private String difficulties;
    private String comment;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Car car;

    public Rent() {
    }

    public Rent(int totalPrice, Customer customer, Car car) {
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.car = car;
    }

    public Rent(int totalPrice, String comment, Customer customer, Car car) {
        this.totalPrice = totalPrice;
        this.comment = comment;
        this.customer = customer;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int price) {
        this.totalPrice = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(String difficulties) {
        this.difficulties = difficulties;
    }
}
