package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.Table;
import org.json.simple.parser.ParseException;

import java.util.List;

public class InfoTableService {

    public static List<InfoTableDTO> getInfoTable(String cityFrom, String cityTo){
        try {
            return Table.getInfoTable(cityFrom, cityTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> getPaginationList(List<InfoTableDTO> infoTableList){
        return  Calculate.getPaginationList(infoTableList);
    }

    public static List<InfoTableDTO> getShortInfoTable(List<InfoTableDTO> infoTable, List<Integer> list) {
        if (list == null) {
            return infoTable;
        }
        return Calculate.getFiveElements(infoTable, 1);
    }
}
