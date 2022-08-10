package POJOPractice;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class POJOPracticeClasses {

    public static void main(String args[]){

        System.out.println("Hello World!");
        RestAssured.baseURI="https://reqres.in";
        getUsersForPage2 getUsersForPage2=given().expect().defaultParser(Parser.JSON).
        when().get("/api/users?page=2").then().assertThat().statusCode(200).extract().as(getUsersForPage2.class);


        System.out.println("Page number is :-"+getUsersForPage2.getPage());
        System.out.println("Page number is :-"+getUsersForPage2.getPer_page());
        for(int iStart=0;iStart<getUsersForPage2.getData().size();iStart++){
            if(getUsersForPage2.getData().get(iStart).getFirst_name().trim().equalsIgnoreCase("Michael")){
                System.out.println("First Email Is :-"+getUsersForPage2.getData().get(iStart).getEmail());
            }

        }
    }

}
