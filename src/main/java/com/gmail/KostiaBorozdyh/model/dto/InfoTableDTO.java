package com.gmail.KostiaBorozdyh.model.dto;

/**
 * Describes InfoTable's DTO
 */
public class InfoTableDTO {
    private Integer id;
    private String cityFrom;
    private String cityTo;
    private Integer distance;
    private Integer price;


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
        return "InfoTableDTO{" +
                "cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", distance=" + distance +
                '}';
    }

    public InfoTableDTO(String cityFrom, String cityTo, Integer distance) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public InfoTableDTO() {

    }
}
