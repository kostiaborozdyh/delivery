package com.example.demos.model;

import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.InfoTable;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoTableDao {
    public static List<InfoTable> getInfoTable(String cityFrom, String cityTo) throws IOException, ParseException {
        List<InfoTable> infoTable = new ArrayList<>();
        List<Distance> distances = GoogleMaps.getDistance(cityFrom,cityTo);
        int index = 0;
        for (Distance distance :
                distances) {
            infoTable.add(new InfoTable(index, distance.getCityFrom(), distance.getCityTo(), distance.getDistance(), Calculate.avgPrice(distance.getDistance())));
            index++;
        }
        return infoTable;
    }

}
