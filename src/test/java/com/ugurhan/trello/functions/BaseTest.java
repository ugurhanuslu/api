package com.ugurhan.trello.functions;
import io.restassured.config.DecoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Random;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class BaseTest {
    String baseUrl= "https://api.trello.com/1/";
    String APIKey = "6de429d97ee7756c1ab77bedc4685ecb";
    String APIToken = "a9ad2a22bd72fe5a768221f4d597bd2e6af76b4532a475752fda6658ef4cefd6";

    public final Logger logger = LogManager.getLogger(this.getClass());
    public static String boardId;
    public static String boardListId;
    public static String lastCardId;
    public static List<String> cardsID;


    public void createBoard(String name) {
        BasicConfigurator.configure();
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("name", name)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post(baseUrl+"boards/?name={name}&key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();


        boardId = response.jsonPath().get("id");
        logger.info("Oluşturulan board id: " + boardId);
    }

    public String getBoardId() {
        Response response = given().config(config().decoderConfig(new DecoderConfig().noContentDecoders())).
                when().
                get(baseUrl+"members/me/boards?key={APIKey}&token={APIToken}", APIKey, APIToken).
                then().extract().response();
        List<String> id = response.jsonPath().getList("id");
        boardId = id.get(0);
        logger.info("getirilen board id: "+boardId);
        return boardId;
    }

    public void deleteBoard() {
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("id", boardId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(baseUrl+"boards/{id}?key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        logger.info("silinen board id: " + boardId);
    }

    public void deleteCard() {
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("id", lastCardId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .when()
                .delete(baseUrl+"cards/{id}?key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        logger.info("silinen card id: " + lastCardId);
    }

    public String getBoardLists() {
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("id", boardId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .accept(ContentType.JSON)
                .when()
                .get(baseUrl+"boards/{id}/lists?key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        List<String> id = response.jsonPath().getList("id");
        boardListId = id.get(0);
        logger.info("getirilen board list id: "+ boardListId);
        return boardListId;

    }

    public void createCard(String name,String desc) {
        String body = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"desc\": \""+desc+"\"}";
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("IDList", boardListId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(body)
                .post(baseUrl+"cards?idList={IDList}&key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        lastCardId = response.jsonPath().get("id");
        logger.info("Oluşturulan card id: " + lastCardId);
    }

    public String randomGetCards() {
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("idList", boardListId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .accept(ContentType.JSON)
                .when()
                .get(baseUrl+"lists/{idList}/cards?key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        cardsID = response.jsonPath().getList("id");
        Random random = new Random();
        int randomCardIndex = random.nextInt(cardsID.size());
        lastCardId = cardsID.get(randomCardIndex);
        logger.info("random getirilen card id: "+ lastCardId);
        return lastCardId;

    }
    public void updateCard(String name,String desc) {
        String body = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"desc\": \""+desc+"\"}";
        Response response = given()
                .config(config().decoderConfig(new DecoderConfig().noContentDecoders()))
                .pathParams("id", lastCardId)
                .pathParams("APIKey", APIKey)
                .pathParams("APIToken", APIToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(baseUrl+"cards/{id}?key={APIKey}&token={APIToken}")
                .then()
                .statusCode(200)
                .extract().response();

        lastCardId = response.jsonPath().get("id");
        logger.info("update edilen card id: " + lastCardId);
    }


}