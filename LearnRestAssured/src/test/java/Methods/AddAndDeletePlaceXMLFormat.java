package Methods;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;
import resources.Resource;
import java.io.IOException;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AddAndDeletePlaceXMLFormat {
    @Test
    public void fnAddAndDeletePlace() throws IOException {
        RestAssured.baseURI="http://216.10.245.166";
        String sXMLBody="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<root>\n" +
                "    <location>\n" +
                "        <lat>-38.383494</lat>\n" +
                "        <lng>33.427362</lng>\n" +
                "    </location>\n" +
                "    <accuracy>50</accuracy>\n" +
                "    <name>The Mens store</name>\n" +
                "    <phone_number>(+91) 983 893 3937</phone_number>\n" +
                "    <address>Anna Salai, Chennai</address>\n" +
                "    <types>shoe park</types>\n" +
                "    <types>kadai</types>\n" +
                "    <website>http://google.com</website>\n" +
                "    <language>tamil-IN</language>\n" +
                "</root>\n";

        Response resp=
        given().queryParam("key","qaclick123").body(sXMLBody).
        when().post("/maps/api/place/add/xml").
        then().assertThat().statusCode(200).
        extract().response();

        String sResponseString=resp.asString();
        System.out.println("Response String "+sResponseString);
        XmlPath xmlpath=new XmlPath(sResponseString);
        System.out.println("Status ID"+xmlpath.get("response.place_id"));
    }
}
