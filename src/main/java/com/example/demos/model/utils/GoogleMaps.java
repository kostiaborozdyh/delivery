package com.example.demos.model.utils;

import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.Point;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class GoogleMaps {
    private static final Logger log = Logger.getLogger(GoogleMaps.class);
    public static final String API_KEY = "&key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4";
    public static final String HTTP_MAPS = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
    public static final String HTTP_GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    public static final String DESTINATIONS = "&destinations=";
    public static final String LANGUAGE = "&language=uk&departure_time=now";

    public static List<InfoTable> getDistance(String cityFrom, String cityTo) throws ParseException {
        log.info("Дистанція між двома містами");
        List<InfoTable> distanceList = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();
            String url = HTTP_MAPS + cityFrom + DESTINATIONS + cityTo + LANGUAGE + API_KEY;
            Request googleRequest = new Request.Builder()
                    .url(url)
                    .build();
            Response googleResponse = client.newCall(googleRequest).execute();
            assert googleResponse.body() != null;
            String js = googleResponse.body().string();
            distanceList = JsonParser.parseGoogleApiDistance(js);
            googleResponse.close();
            log.info("Дистанція між двома містами завершено");
        } catch (Exception ex) {
            log.error("Помилка дистанція між двома містами "+ex);
        }
        return distanceList;
    }

    public static Point getCityCoordinates(String city) {
        Point point = new Point();
        log.info("Координати міста");
        try {
            OkHttpClient client = new OkHttpClient();
            String url = HTTP_GEOCODE + city + API_KEY;
            Request googleRequest = new Request.Builder()
                    .url(url)
                    .build();
            Response googleResponse = client.newCall(googleRequest).execute();
            assert googleResponse.body() != null;
            String js = googleResponse.body().string();
             point = JsonParser.parseGoogleApiGeocode(js);
            googleResponse.close();
            log.info("Координати міста завершено");
        }
        catch (Exception ex) {
            log.error("Помилка, координати міста "+ex);
        }
        return point;
    }
}
