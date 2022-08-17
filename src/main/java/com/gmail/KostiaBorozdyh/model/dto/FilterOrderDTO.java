package com.gmail.KostiaBorozdyh.model.dto;

/**
 * Describes FilterOrder's DTO
 */
public class FilterOrderDTO {
    private String minPrice;
    private String maxPrice;
    private String[] paymentStatus;
    private String[] location;
    private String minDateCreate;
    private String maxDateCreate;
    private String minDateOfArrival;
    private String maxDateOfArrival;
    private String[] cityFrom;
    private String[] cityTo;
    private String sort;

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String[] getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String[] paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String getMinDateCreate() {
        return minDateCreate;
    }

    public void setMinDateCreate(String minDateCreate) {
        this.minDateCreate = minDateCreate;
    }

    public String getMaxDateCreate() {
        return maxDateCreate;
    }

    public void setMaxDateCreate(String maxDateCreate) {
        this.maxDateCreate = maxDateCreate;
    }

    public String getMinDateOfArrival() {
        return minDateOfArrival;
    }

    public void setMinDateOfArrival(String minDateOfArrival) {
        this.minDateOfArrival = minDateOfArrival;
    }

    public String getMaxDateOfArrival() {
        return maxDateOfArrival;
    }

    public void setMaxDateOfArrival(String maxDateOfArrival) {
        this.maxDateOfArrival = maxDateOfArrival;
    }

    public String[] getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String[] cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String[] getCityTo() {
        return cityTo;
    }

    public void setCityTo(String[] cityTo) {
        this.cityTo = cityTo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
