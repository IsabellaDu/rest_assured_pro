package test.get;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

public class GetStickersTest extends BaseTest {

    // JSON schema validation
    @Test
    public void checkGetStickers() {
        requestWithAuth()
                .pathParam("id", UrlParamValues.EXISTING_STICKERS_ID)
                .get(BoardsEndPoints.GET_ALL_STICKERS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_stickers.json"));
    }

    @Test
    public void checkGetSticker() {
        requestWithAuth()
                .pathParams(Map.of(
                        "id", UrlParamValues.EXISTING_STICKERS_ID,
                        "idSticker", UrlParamValues.EXISTING_STICKER_ID
                ))
                .get(BoardsEndPoints.GET_STICKER_URL)
                .then()
                .statusCode(200)
                .body("image", Matchers.equalTo("star"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_sticker.json"));
    }

}
