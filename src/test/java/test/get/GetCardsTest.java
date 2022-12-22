package test.get;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardsTest extends BaseTest {

    // JSON schema validation
    @Test
    public void checkGetCards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_LIST_ID)
                .get(BoardsEndPoints.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .queryParam("fields", "id,name,desc")
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(BoardsEndPoints.GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("new name for the second card"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}
