package resources;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Paths.*;

public class Resource {
    public static String fngetResource(){
        String sResource="/maps/api/place/findplacefromtext/json";
        return sResource;
    }


    public static void fnGetDataInXMLFormat(String sFilePath) throws FileNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(sFilePath);
//        fileInputStrea
    }

    public static String fnAddBook() {
        String sPayLoad="{\n" +
                "\n" +
                "\t\"name\":\"Learn Appium Automation with Java Updated\",\n" +
                "\t\"isbn\":\"bcd2\",\n" +
                "\t\"aisle\":\"227"+ RandomStringUtils.randomNumeric(7) +"\",\n" +
                "\t\"author\":\"John foe\"\n" +
                "}";
        return sPayLoad;
    }


    public static String fnAddBookFromFile(String sFilePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(sFilePath)));
    }
}
