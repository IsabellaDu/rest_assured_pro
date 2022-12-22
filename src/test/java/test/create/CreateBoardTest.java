package test.create;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard() {

        String boardName = "New Board from Intellij IDEA" + LocalDateTime.now();
        Response response = requestWithAuth()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(BoardsEndPoints.CREATE_BOARD_URL);
        createdBoardId = response.body().jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardsEndPoints.GET_ALL_BOARDS_URL)
                .then()
                .body("name", Matchers.hasItem(boardName));
    }

    @AfterEach
    public void deleteCreatedBoard() {
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(BoardsEndPoints.DELETE_BOARD_URL);
    }
}
