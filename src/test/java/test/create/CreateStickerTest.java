package test.create;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

public class CreateStickerTest extends BaseTest {

    private String createdStickerId;

    @Test
    public void checkCreateSticker() {
        String stickerImage = "star";

        Response response = requestWithAuth()
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .body(Map.of(
                        "image", stickerImage,
                        "top", 20,
                        "left", 10,
                        "zIndex", 10,
                        "rotate", 2
                ))
                .contentType(ContentType.JSON)
                .post(BoardsEndPoints.CREATE_STICKER_URL);
        createdStickerId = response.body().jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(createdStickerId));
        requestWithAuth()
                .queryParam("fields", "id,top,left,zIndex,rotate,image")
                .pathParam("id", UrlParamValues.EXISTING_STICKERS_ID)
                .get(BoardsEndPoints.GET_ALL_STICKERS_URL)
                .then()
                .body("id", Matchers.hasItem(createdStickerId));
    }

    @AfterEach
    public void deleteCreatedSticker() {
        requestWithAuth()
                .pathParams(Map.of("id", UrlParamValues.EXISTING_CARD_ID,
                        "idSticker", createdStickerId))
                .delete(BoardsEndPoints.DELETE_STICKER_URL);
    }
}
