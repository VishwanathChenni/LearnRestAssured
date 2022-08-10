package Methods;

import groovy.transform.ASTTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import resources.Resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GooglePlaceSearchTestNG {
    @Test
    public void firstFunction(){
        System.out.println("Hello World");
        RestAssured.baseURI="https://maps.googleapis.com";
        given().param("input","Sai Vatika, Madhav Nagar,Pune,india").
                param("inputtype","textquery").
                param("fields","photos,formatted_address,name,rating,opening_hours,geometry") .
                param("key","AIzaSyAnjVuouKOD7uMeIrFEX9u5Bn_v85HLIec").
        when().get(Resource.fngetResource()).
        then().assertThat().statusCode(200).body("candidates[0].name",equalTo("Sai Vatika")).header("Server","scaffolding on HTTPServer2");
    }
}
