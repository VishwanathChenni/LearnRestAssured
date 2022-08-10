package POJOPractice;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceAPI {
    @Test
    public void fnAddPlace(){
        System.out.println("Execution Starts");
        RestAssured.baseURI="http://216.10.245.166";
        MyRequestBody myRequestBody= new MyRequestBody();

        Location location=new Location();
        location.setLat(-38.383498);
        location.setLng(33.427362);

        myRequestBody.setLocation(location);
        myRequestBody.setAccuracy(50);
        myRequestBody.setName("Frontline house");
        myRequestBody.setPhone_number("(+91) 983 893 3937");
        myRequestBody.setAddress("29, side layout, cohen 09");
        myRequestBody.setWebsite("http://google.com");
        myRequestBody.setLanguage("French-IN");

        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");
        myRequestBody.setTypes(types);

        given().
                queryParam("key","qaclick123").
                body(myRequestBody).
        when().
                post("/maps/api/place/add/json").
        then().
                assertThat().statusCode(200).and().body("status",equalTo("OK"));
        System.out.println("Execution is completed");
    }
}
