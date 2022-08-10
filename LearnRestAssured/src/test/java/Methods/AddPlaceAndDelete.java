package Methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceAndDelete {
    Properties prop;
    @BeforeTest
    public void fnLoadProperties() throws IOException {
        prop=new Properties();
        FileInputStream fis=new FileInputStream("config.properties");
        prop.load(fis);

    }
    @Test
    public void fnAddPalceAndDeletePalce() throws InterruptedException {
        System.out.println("Execution started");
        String myrequestBody="{\n" +
        "    \"location\":{\n" +
        "        \"lat\" : -38.383498,\n" +
        "        \"lng\" : 33.427362\n" +
        "    },\n" +
        "    \"accuracy\":50,\n" +
        "    \"name\":\"Frontline house\",\n" +
        "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
        "    \"address\" : \"29, side layout, cohen 09\",\n" +
        "    \"types\": [\"shoe park\",\"shop\"],\n" +
        "    \"website\" : \"http://google.com\",\n" +
        "    \"language\" : \"French-IN\"\n" +
        "}\n";
//Grab the response
        RestAssured.baseURI=prop.getProperty("HOST");
        Response res=given().
                queryParam("key","qaclick123").body(myrequestBody).
        when().
                post("/maps/api/place/add/json").
        then().
                assertThat().statusCode(200).and().body("status",equalTo("OK")).
        extract().response();
        String sResponseString=res.asString();
        System.out.println("Response is :-"+sResponseString);

        //Grab the place ID from response
        JsonPath js=new JsonPath(sResponseString);
        XmlPath xml=new XmlPath(sResponseString);
        System.out.println("Place ID is "+js.get("place_id"));;
        String sResponsePlaceID="{\n" +
                "    \"place_id\":\""+js.get("place_id")+"\"\n" +
                "}";
        Thread.sleep(3000);
        //Deleting place ID
        given().queryParam("key","qaclick123").body(myrequestBody).body(sResponsePlaceID).
        when().post("/maps/api/place/delete/json").
        then().assertThat().statusCode(200).and().body("status",equalTo("OK"));
        System.out.println("Execution completed");
    }
}
