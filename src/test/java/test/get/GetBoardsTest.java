package test.get;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetBoardsTest extends BaseTest {

    // JSON schema validation
    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardsEndPoints.GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }


    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardsEndPoints.GET_BOARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("updated name of the board"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
    }
}
