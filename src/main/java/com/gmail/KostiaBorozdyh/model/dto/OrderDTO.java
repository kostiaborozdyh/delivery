package com.gmail.KostiaBorozdyh.model.dto;

/**
 * Describes Order's DTO
 */
public class OrderDTO {
    private Integer id;
    private String description;
    private Integer weight;
    private Integer height;
    private Integer length;
    private Integer width;
    private Integer volume;
    private Integer price;
    private String address;
    private Integer distance;
    private String cityFrom;
    private String cityTo;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public static class Builder {
        private OrderDTO newOrderDTO;

        public Builder() {
            newOrderDTO = new OrderDTO();
        }

        public Builder id(Integer id) {
            newOrderDTO.id = id;
            return this;
        }

        public Builder description(String description) {
            newOrderDTO.description = description;
            return this;
        }

        public Builder weight(Integer weight) {
            newOrderDTO.weight = weight;
            return this;
        }

        public Builder height(Integer height) {
            newOrderDTO.height = height;
            return this;
        }

        public Builder length(Integer length) {
            newOrderDTO.length = length;
            return this;
        }

        public Builder width(Integer width) {
            newOrderDTO.width = width;
            return this;
        }

        public Builder volume(Integer volume) {
            newOrderDTO.volume = volume;
            return this;
        }

        public Builder price(Integer price) {
            newOrderDTO.price = price;
            return this;
        }

        public Builder address(String address) {
            newOrderDTO.address = address;
            return this;
        }

        public Builder distance(Integer distance) {
            newOrderDTO.distance = distance;
            return this;
        }

        public Builder cityFrom(String cityFrom) {
            newOrderDTO.cityFrom = cityFrom;
            return this;
        }

        public Builder cityTo(String cityTo) {
            newOrderDTO.cityTo = cityTo;
            return this;
        }

        public OrderDTO build() {
            return newOrderDTO;
        }

    }

}
