package com.example.demos.model.utils;

import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<Distance> parseGoogleApiDistance(String jsonStr) throws ParseException {
        List<Distance> distanceList = new ArrayList<>();
        ArrayList<String> cityFromList;
        ArrayList<String> cityToList;
        int i = 0, j;
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
                distanceList.add(new Distance(cutName(cityFromList.get(i)), cutName(cityToList.get(j)), Integer.parseInt(distancesObj.get("value").toString()) / 1000));
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

    public static String cutName(String name) {
        long count = name.chars().filter(ch -> ch == ',').count();
        if (count == 1) {
            return name;
        }
        StringBuilder sb = new StringBuilder(name);
        int lastIndex = name.lastIndexOf(',');
        sb.delete(lastIndex, sb.length());
        if (count == 2) {
            return sb.toString();
        } else {
            return sb.replace(sb.indexOf(","), sb.lastIndexOf(","), "").toString();
        }

    }

}