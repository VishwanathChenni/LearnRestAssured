package Methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingInforormation {
    @Test
    public void fnLoggingInforormation(){
        RestAssured.baseURI="https://reqres.in";

       Response resp= given().param("page","2").log().all().
        when().get("/api/users").
        then().assertThat().statusCode(200).log().all().
        extract().response();
      String respString=resp.asString();
      JsonPath js=new JsonPath(respString);
      int iSize=js.get("data.size()");
      for(int iStart=0;iStart<iSize;iStart++){
          System.out.println("First Name is:-"+js.get("data["+iStart+"].first_name"));
          System.out.println("Last Name is:-"+js.get("data["+iStart+"].last_name"));
      }

    }
}
