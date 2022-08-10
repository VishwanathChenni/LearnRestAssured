package Methods;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class JIRALogin {
    @Test
    public void fnLoginToJIRA(){

        for(int iStart=0;iStart<=1;iStart++){

            RestAssured.baseURI="http://127.0.0.1:9090";
            String sMyLogin="{ \n" +
                        "\t\"username\": \"admin\",\n" +
                        "\t\"password\": \"admin\" \n" +
                        "}";
            Response resp=  given().header("Content-Type","application/json").body(sMyLogin).log().all().
                when().post("/rest/auth/1/session").
                then().assertThat().statusCode(200).extract().response();
            String  mystringResp=resp.asString();
            JsonPath js=new JsonPath(mystringResp);
            System.out.println("Session ID is :-"+js.get("session.value"));
            String sDefectBody="{\n" +
                      "    \"fields\": {\n" +
                      "       \"project\":\n" +
                      "       {\n" +
                      "          \"key\": \"RES\"\n" +
                      "       },\n" +
                      "       \"summary\": \"REST ye merry gentlemen.\",\n" +
                      "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                      "       \"issuetype\": {\n" +
                      "          \"name\": \"Bug\"\n" +
                      "       }\n" +
                      "   }\n" +
                      "}";
            Response resp2=given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+js.get("session.value")+"").body(sDefectBody).log().all().
                        when().post("/rest/api/2/issue/").
                        then().assertThat().statusCode(201).log().all().extract().response();
            String resp2ToString=resp2.asString();
                JsonPath js2=new JsonPath(resp2ToString);
                System.out.println("Defect ID is:-"+js2.get("key"));
            }

    }
}
