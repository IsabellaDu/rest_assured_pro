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

public class CreateLabelTest extends BaseTest {

    private String createdLabelId;

    @Test
    public void checkCreateLabel() {

        String labelName = "New Label" + LocalDateTime.now();

        Response response = requestWithAuth()
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .body(Map.of(
                        "name", labelName,
                        "color", "yellow"
                ))
                .contentType(ContentType.JSON)
                .post(BoardsEndPoints.CREATE_LABEL_URL);
        createdLabelId = response.body().jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(labelName));
        requestWithAuth()
                .queryParam("fields", "name,color")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get("/1/boards/{id}/labels")
                .then()
                .body("name", Matchers.hasItem(labelName));
    }

    @AfterEach
    public void deleteCreatedLabel() {
        requestWithAuth()
                .pathParam("id", createdLabelId)
                .delete(BoardsEndPoints.DELETE_LABEL_URL);
    }
}
