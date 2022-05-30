package com.example.demos.model.utils;

import com.example.demos.model.entity.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    public static List<InfoTable> getInfoTable(String cityFrom, String cityTo) throws IOException, ParseException {
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
