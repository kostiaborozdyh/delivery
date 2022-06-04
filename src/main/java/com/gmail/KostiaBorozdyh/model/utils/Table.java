package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import org.json.simple.parser.ParseException;

import java.util.List;

public class Table {
    public static List<InfoTable> getInfoTable(String cityFrom, String cityTo) throws  ParseException {
        List<InfoTable> distances = GoogleMaps.getDistance(cityFrom,cityTo);
        int index = 0;
        for (InfoTable distance :
                distances) {
            distance.setId(index);
            distance.setPrice(Calculate.avgPrice(distance.getDistance()));
            index++;
        }
        return distances;
    }

}
