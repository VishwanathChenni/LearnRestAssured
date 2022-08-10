package Methods;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class MyFunction {
    public static  void main(String args[]){


        RestAssured.baseURI="https://reqres.in";
//        given().
//                param("mykey","myvalue").
//                param("key2","value2");
      System.out.println(""+given().
       when().get("/api/users/2").
       then().assertThat().statusCode(200).toString());
//       body("data.first_name",equalTo("Janet"));



        //BaseURL
        //given:- Passing headers/Parameters/Request cookies/body
        //when  :-get(resource info)/post(resource info)/put(Resource info)
        //then  :- Put validation
        //extract Extracting results
    }
}
