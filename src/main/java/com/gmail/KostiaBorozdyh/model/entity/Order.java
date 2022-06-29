package com.gmail.KostiaBorozdyh.model.entity;

import java.time.LocalDate;

/**
 * Describes Order's entity
 */
public class Order {
    private Integer id;
    private String description;
    private Integer weight;
    private Integer volume;
    private Integer price;
    private String address;
    private LocalDate dateCreate;
    private LocalDate dateOfSending;
    private LocalDate dateOfArrival;
    private String userLogin;
    private Integer userId;
    private Integer distance;
    private String cityFrom;
    private String cityTo;
    private String paymentStatus;
    private String locationStatus;

    public LocalDate getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(LocalDate dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public Integer getUserId() { return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getDistance() {return distance;}

    public void setDistance(Integer distance) {this.distance = distance;}

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", distance=" + distance +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}
