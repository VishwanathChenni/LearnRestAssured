package Methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoopJsonObject {
    @Test
    public void fnLoopJsonObject(){
        RestAssured.baseURI="https://reqres.in";

        Response resp=given().
                param("page","2").log().all().
        when().get("/api/users").
        then().assertThat().statusCode(200).log().all().
        extract().response();
        String sResponseString=resp.asString();

        JsonPath js=new JsonPath(sResponseString);
        int size=js.get("data.size()");
        System.out.println("Length is :-"+size);
        for (int iStart=0;iStart<size;iStart++){
            String sMyString=js.get("data["+iStart+"].first_name");
            if(sMyString.equalsIgnoreCase("Tobias")){
                System.out.println("Name:--"+js.get("data["+iStart+"].first_name")+"-"+js.get("data["+iStart+"].last_name"));
            }

        }
    }
}
