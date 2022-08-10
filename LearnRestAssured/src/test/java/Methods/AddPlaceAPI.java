package Methods;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceAPI {
    @Test
    public void fnAddPlace(){
        System.out.println("Execution Starts");
        RestAssured.baseURI="http://216.10.245.166";
        String myRequestBody="{\n" +
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
