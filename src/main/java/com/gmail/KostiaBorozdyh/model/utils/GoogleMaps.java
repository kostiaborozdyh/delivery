package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.dto.PointDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * GoogleMapsAPI utils
 */
public class GoogleMaps {
    private static final Logger log = Logger.getLogger(GoogleMaps.class);
    public static final String API_KEY = "&key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4";
    public static final String HTTP_MAPS = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
    public static final String HTTP_GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    public static final String DESTINATIONS = "&destinations=";
    public static final String LANGUAGE = "&language=uk&departure_time=now";

    /**
     * Return List InfoTableDTO from GoogleMapsAPI by cityFrom and cityTo
     * <p>
     * 1)Make request to GoogleAPI matrix distance with param:cityTo and cityFrom
     * <p>
     * 2)Get response from GoogleAPI in JSON format
     * <p>
     * 3)Parse JSON response and convert to InfoTableDTO
     *
     * @param cityFrom String city From
     * @param cityTo   String city To
     * @return List InfoTableDTO
     */
    public static List<InfoTableDTO> getDistance(String cityFrom, String cityTo) throws ParseException {
        List<InfoTableDTO> distanceList = new ArrayList<>();
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
            log.info("Calculating distance between cityFrom - " + cityFrom + " and cityTo - " + cityTo + ", with GoogleAPI");
        } catch (Exception ex) {
            log.error("problem with connection that we take from GoogleAPI distance");
            log.error("Exception - " + ex);
        }
        return distanceList;
    }

    /**
     * Return PointDTO from GoogleMapsAPI by city
     * <p>
     * 1)Make request to GoogleAPI geocode with param:city
     * <p>
     * 2)Get response from GoogleAPI in JSON format
     * <p>
     * 3)Parse JSON response and convert to PointDTO
     *
     * @param city String city
     * @return PointDTO
     */
    public static PointDTO getCityCoordinates(String city) {
        PointDTO point = new PointDTO();
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
            log.info("Calculating current point city - " + city + ", with GoogleAPI");
        } catch (Exception ex) {
            log.error("problem with connection that we take from GoogleAPI geocode");
            log.error("Exception - " + ex);
        }
        return point;
    }
}
