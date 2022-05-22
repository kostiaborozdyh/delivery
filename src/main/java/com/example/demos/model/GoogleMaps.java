package com.example.demos.model;

import com.example.demos.model.entity.Distance;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class GoogleMaps {
    public static final String API_KEY="AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4";
    public static final String HTTP_MAPS="https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
    public static final String DESTINATIONS="&destinations=";
    public static final String LANGUAGE = "&language=uk&departure_time=now&key=";

    public static List<Distance> getDistance(String cityFrom, String cityTo) throws IOException, ParseException {
        List<Distance> distanceList;
        OkHttpClient client = new OkHttpClient();
        String url=HTTP_MAPS+cityFrom+DESTINATIONS+cityTo+ LANGUAGE + API_KEY;
        Request googleRequest = new Request.Builder()
                .url(url)
                .build();
        Response googleResponse = client.newCall(googleRequest).execute();
        assert googleResponse.body() != null;
        String js =  googleResponse.body().string();
        distanceList=JsonParser.parseGoogleApiDistance(js);
        googleResponse.close();
       return distanceList;
    }
}
