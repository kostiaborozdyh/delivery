package com.example.demos.model;


import java.sql.Date;
import java.time.LocalDate;

public class Calculate {
    public static Integer avgPrice(Integer distance){
        if (distance==0) return 100;
        return distance*15/10;
    }
    public static Integer deliveryPrice(Integer distance,Integer volume, String weight){
        return Math.max(avgPrice(distance)*Integer.parseInt(weight)*volume/10, 100);
    }
    public static Integer volume(String height,String length, String width){
        return Integer.parseInt(height)*Integer.parseInt(length)*Integer.parseInt(width);
    }
    public static LocalDate arrivalTime(Integer distance){

        if(distance==0) distance=1;
        else distance = distance / 100;
        return LocalDate.now().plusDays(distance);
    }
    public static LocalDate newArrivalTime(LocalDate dateOfCreate, LocalDate dateOfArrival) {
        int length1 = dateOfCreate.getDayOfYear();
        int length2 = dateOfArrival.getDayOfYear();
        if(dateOfArrival.getYear() != dateOfCreate.getYear()){
          return   LocalDate.now().plusDays(length2-length1+365);
        }
        else{
            return   LocalDate.now().plusDays(length2-length1);
        }
    }

}
