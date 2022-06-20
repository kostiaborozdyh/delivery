package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import org.json.simple.parser.ParseException;

import java.util.List;

public class Table {
    public static List<InfoTableDTO> getInfoTable(String cityFrom, String cityTo) throws  ParseException {
        List<InfoTableDTO> distances = GoogleMaps.getDistance(cityFrom,cityTo);
        int index = 0;
        for (InfoTableDTO distance :
                distances) {
            distance.setId(index);
            distance.setPrice(Calculate.avgPrice(distance.getDistance()));
            index++;
        }
        return distances;
    }

}
