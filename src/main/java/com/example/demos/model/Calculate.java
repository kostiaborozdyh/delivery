package com.example.demos.model;


import java.time.LocalDate;

public class Calculate {
    public static Integer avgPrice(Integer distance){
        if (distance==0) return 60;
        return 6*distance/10;
    }
    public static Integer deliveryPrice(Integer distance,Integer volume, String weight){
        return avgPrice(distance)*Integer.parseInt(weight)*volume/10;
    }
    public static Integer volume(String height,String length, String width){
        return Integer.parseInt(height)*Integer.parseInt(length)*Integer.parseInt(width);
    }
    public static LocalDate arrivalTime(Integer distance){

        if(distance==0) distance=1;
        else distance = distance / 100;
        return LocalDate.now().plusDays(distance);
    }
}
