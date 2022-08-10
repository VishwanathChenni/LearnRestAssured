package Methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleGetPlaceTest {
    public static  void main(String args[]){
        System.out.println("Hello World");
        RestAssured.baseURI="https://maps.googleapis.com";

        given()
                .param("input","Sai Vatika, Madhav Nagar,Pune,india").
                param("inputtype","textquery").
                param("fields","photos,formatted_address,name,rating,opening_hours,geometry").
                param("key","AIzaSyAnjVuouKOD7uMeIrFEX9u5Bn_v85HLIec").
        when().
                get("/maps/api/place/findplacefromtext/json").
        then().
                assertThat().statusCode(200).and()
                .contentType(ContentType.JSON).body("candidates[0].name",equalTo("Sai Vatik"));
    }
}
