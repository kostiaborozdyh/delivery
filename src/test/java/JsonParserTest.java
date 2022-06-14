import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import com.gmail.KostiaBorozdyh.model.entity.Point;
import com.gmail.KostiaBorozdyh.model.utils.JsonParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class JsonParserTest {


    @Test
    public void parseGoogleApiDistanceTest() throws FileNotFoundException, ParseException {
        ArrayList<InfoTable> infoTableList = new ArrayList<>();
        infoTableList.add(new InfoTable("Київ, Україна", "Кривий Ріг, Україна", 420));
        infoTableList.add(new InfoTable("Київ, Україна", "Донецьк, Донецька область", 741));
        infoTableList.add(new InfoTable("Київ, Україна", "Харків, Харківська область", 479));
        infoTableList.add(new InfoTable("Київ, Україна", "Варшава, Польща", 782));
        infoTableList.add(new InfoTable("Київ, Україна", "Миколаїв, Україна", 601));

        infoTableList.add(new InfoTable("Черкаси, Україна", "Кривий Ріг, Україна", 247));
        infoTableList.add(new InfoTable("Черкаси, Україна", "Донецьк, Донецька область", 600));
        infoTableList.add(new InfoTable("Черкаси, Україна", "Харків, Харківська область", 370));
        infoTableList.add(new InfoTable("Черкаси, Україна", "Варшава, Польща", 969));
        infoTableList.add(new InfoTable("Черкаси, Україна", "Миколаїв, Україна", 571));

        infoTableList.add(new InfoTable("Канів, Україна", "Кривий Ріг, Україна", 326));
        infoTableList.add(new InfoTable("Канів, Україна", "Донецьк, Донецька область", 665));
        infoTableList.add(new InfoTable("Канів, Україна", "Харків, Харківська область", 475));
        infoTableList.add(new InfoTable("Канів, Україна", "Варшава, Польща", 910));
        infoTableList.add(new InfoTable("Канів, Україна", "Миколаїв, Україна", 612));

        infoTableList.add(new InfoTable("Чернігів, Україна", "Кривий Ріг, Україна", 574));
        infoTableList.add(new InfoTable("Чернігів, Україна", "Донецьк, Донецька область", 888));
        infoTableList.add(new InfoTable("Чернігів, Україна", "Харків, Харківська область", 613));
        infoTableList.add(new InfoTable("Чернігів, Україна", "Варшава, Польща", 911));
        infoTableList.add(new InfoTable("Чернігів, Україна", "Миколаїв, Україна", 757));

        infoTableList.add(new InfoTable("Луцьк, Волинська область", "Кривий Ріг, Україна", 790));
        infoTableList.add(new InfoTable("Луцьк, Волинська область", "Донецьк, Донецька область", 1152));
        infoTableList.add(new InfoTable("Луцьк, Волинська область", "Харків, Харківська область", 890));
        infoTableList.add(new InfoTable("Луцьк, Волинська область", "Варшава, Польща", 408));
        infoTableList.add(new InfoTable("Луцьк, Волинська область", "Миколаїв, Україна", 899));

        String text = fileReader("distanceJSON.txt");
        List<InfoTable> infoTableListTest = JsonParser.parseGoogleApiDistance(text);
        ArrayList<InfoTable> arrayList = (ArrayList<InfoTable>) infoTableListTest;

        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(arrayList.get(i).getCityFrom(), infoTableList.get(i).getCityFrom());
            assertEquals(arrayList.get(i).getCityTo(), infoTableList.get(i).getCityTo());
            assertEquals(arrayList.get(i).getDistance(), infoTableList.get(i).getDistance());
        }
    }

    @Test
    public void parseGoogleApiGeocodeTest() throws FileNotFoundException, ParseException {
        //first point test
        String text = fileReader("point1JSON.txt");
        Point testPoint = JsonParser.parseGoogleApiGeocode(text);
        Point truePoint = new Point("50.4501", "30.5234");
        assertEquals(truePoint.getLatitude(), testPoint.getLatitude());
        assertEquals(truePoint.getLongitude(), testPoint.getLongitude());

        //second point test
        text = fileReader("point2JSON.txt");
        testPoint = JsonParser.parseGoogleApiGeocode(text);
        truePoint = new Point("49.444433", "32.059767");
        assertEquals(truePoint.getLatitude(), testPoint.getLatitude());
        assertEquals(truePoint.getLongitude(), testPoint.getLongitude());
    }

    @Test
    public void cutNameTest() {
        assertEquals("Кривий Ріг, Україна", JsonParser.cutCityName("Кривий Ріг, Дніпропетровська область, Україна, 50000"));
        assertEquals("Донецьк, Донецька область", JsonParser.cutCityName("Донецьк, Донецька область, Україна"));
        assertEquals("Харків, Харківська область", JsonParser.cutCityName("Харків, Харківська область, Україна"));
        assertEquals("Варшава, Польща", JsonParser.cutCityName("Варшава, Польща"));
        assertEquals("Миколаїв, Україна", JsonParser.cutCityName("Миколаїв, Миколаївська область, Україна, 54000"));
    }

    private String fileReader(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return builder.toString();
    }
}
