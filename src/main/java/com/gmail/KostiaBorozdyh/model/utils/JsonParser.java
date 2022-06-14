package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import com.gmail.KostiaBorozdyh.model.entity.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<InfoTable> parseGoogleApiDistance(String jsonStr) throws ParseException {
        int i = 0, j;
        List<InfoTable> distanceList = new ArrayList<>();
        ArrayList<String> cityFromList;
        ArrayList<String> cityToList;
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(jsonStr);
        JSONArray rows = (JSONArray) jsonObj.get("rows");
        JSONArray cityFromArr = (JSONArray) jsonObj.get("origin_addresses");
        JSONArray cityToArr = (JSONArray) jsonObj.get("destination_addresses");
        cityFromList = parseCity(cityFromArr);
        cityToList = parseCity(cityToArr);
        for (Object distance :
                rows) {
            JSONObject distanceObj = (JSONObject) distance;
            JSONArray elements = (JSONArray) distanceObj.get("elements");
            j = 0;
            for (Object element :
                    elements) {
                JSONObject elementObj = (JSONObject) element;
                JSONObject distancesObj = (JSONObject) elementObj.get("distance");
                distanceList.add(new InfoTable(cutCityName(cityFromList.get(i)), cutCityName(cityToList.get(j)), Integer.parseInt(distancesObj.get("value").toString()) / 1000));
                j++;
            }
            i++;
        }
        return distanceList;
    }

    public static Point parseGoogleApiGeocode(String jsonStr) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonStr);
        JSONObject jsonObj = (JSONObject) obj;
        JSONArray results = (JSONArray) jsonObj.get("results");
        JSONObject information = (JSONObject) results.get(0);
        JSONObject geometry = (JSONObject) information.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        String lng = location.get("lng").toString();
        String lat = location.get("lat").toString();
        return new Point(lat, lng);
    }

    private static ArrayList<String> parseCity(JSONArray city) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Object o : city) {
            arrayList.add(o.toString());
        }
        return arrayList;
    }

    public static String cutCityName(String city) {
        long count = city.chars().filter(ch -> ch == ',').count();
        if (count == 1) {
            return city;
        }
        StringBuilder sb = new StringBuilder(city);
        int lastIndex = city.lastIndexOf(',');
        sb.delete(lastIndex, sb.length());
        if (count == 2) {
            return sb.toString();
        } else {
            return sb.replace(sb.indexOf(","), sb.lastIndexOf(","), "").toString();
        }

    }
    public static String cutCityNameForEmployee(String city)  {
        List<InfoTable> list=null;
        try {
            list = GoogleMaps.getDistance(city, city);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cutCityName(list.get(0).getCityFrom());
    }

}
