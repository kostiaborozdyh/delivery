package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.Table;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.util.List;
/**
 * InfoTable service
 */
public class InfoTableService {
    private static final Logger log = Logger.getLogger(InfoTableService.class);

    /**
     * Return infoTableDTOList by cityFrom and cityTo
     *
     * @param cityFrom String cityFrom
     * @param cityTo String cityTo
     *
     * @return List of infoTableDTO items. If any problems returns empty list.
     */
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

    /**
     * Return IntegerLIst by infoTableList
     *
     * @param infoTableList infoTable list
     *
     * @return List of Integer. If any problems returns null.
     */
    public static List<Integer> getPaginationList(List<InfoTableDTO> infoTableList){
        return  Calculate.getPaginationList(infoTableList);
    }

    /**
     * Return infoTableDTOList by infoTableList and IntegerList
     *
     * @param infoTable infoTable list
     * @param list Integer list
     *
     * @return infoTable if list == null, else first five elements infoTable list
     */
    public static List<InfoTableDTO> getShortInfoTable(List<InfoTableDTO> infoTable, List<Integer> list) {
        if (list == null) {
            return infoTable;
        }
        return Calculate.getFiveElements(infoTable, 1);
    }
}
