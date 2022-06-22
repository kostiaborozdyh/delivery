package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.Table;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.util.List;

public class InfoTableService {
    private static final Logger log = Logger.getLogger(InfoTableService.class);
    public static List<InfoTableDTO> getInfoTable(String cityFrom, String cityTo){
        try {
            log.info("calculating distance between cityFrom - "+cityFrom+" and cityTo - "+cityTo);
            return Table.getInfoTable(cityFrom, cityTo);
        } catch (ParseException e) {
            log.error("problem with parsing data that we take from GoogleAPI");
            log.error("Exception - "+e);
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
