package Methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.client.RedirectStrategy;
import org.testng.annotations.Test;
import resources.Resource;

import static io.restassured.RestAssured.given;

public class AddBookAPI {
    @Test
    public void fnAddBookAPI(){

        RestAssured.baseURI="http://216.10.245.166";
        Response resp= given().log().all().body(Resource.fnAddBook()).
        when().post("/Library/Addbook.php").
        then().assertThat().statusCode(200).extract().response();

        String sMyStringResponse=resp.asString();
        JsonPath js=new JsonPath(sMyStringResponse);

        String sMyMessage=js.get("Msg");
        String sBookID=js.get("ID");
        if(sMyMessage.equalsIgnoreCase("successfully added")){
            System.out.println("Book ID is :-"+sBookID);
        }
        else {
            System.out.println("Book not added");
        }

    }
}
