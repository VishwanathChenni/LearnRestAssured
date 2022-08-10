package Methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.Resource;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ReadingDataFromJsonFile {
    @Test
    public void ReadingDataFromJsonFile() throws IOException {
        RestAssured.baseURI="http://216.10.245.166";
        String sFilePath="TestData/TestData.json";
        Response resp= given().log().all().body(Resource.fnAddBookFromFile(sFilePath)).
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
