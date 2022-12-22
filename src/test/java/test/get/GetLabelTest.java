package test.get;

import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;


public class GetLabelTest extends BaseTest {

    @Test
    public void CheckGetLabel() {
        requestWithAuth()
                .queryParam("fields", "color")
                .pathParam("id", UrlParamValues.EXISTING_LABEL_ID)
                .get(BoardsEndPoints.GET_LABEL_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_label.json"));
    }
}
