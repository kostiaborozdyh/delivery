package com.example.demos.model.entity;

public class InfoTable {
    private Integer id;
    private String cityFrom;
    private String cityTo;
    private Integer distance;
    private Integer price;
    private Integer volume;
    private Integer weight;

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "InfoTable{" +
                "cityTo='" + cityTo + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                '}';
    }


    public InfoTable(Integer id,String cityFrom, String cityTo, Integer distance, Integer price) {
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
        this.price = price;
    }

    public InfoTable(String cityFrom, String cityTo, Integer distance, Integer price, Integer volume, Integer weight) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
    }

    public InfoTable(){

    }
}
