package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.LabelIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.LabelIdValidationArgumentsProvider;
import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class GetLabelValidationTest extends BaseTest {

    // automated test to access a label with invalid id
    @ParameterizedTest
    @ArgumentsSource(LabelIdValidationArgumentsProvider.class)
    public void checkGetLabelWithInvalidId(LabelIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardsEndPoints.GET_LABEL_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    // test method checking unauthorized permission requested
    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetLabelWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_LABEL_ID)
                .get(BoardsEndPoints.GET_LABEL_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    // test trying to access specific label with another user credentials
    @Test
    public void checkGetLabelWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", UrlParamValues.EXISTING_LABEL_ID)
                .get(BoardsEndPoints.GET_LABEL_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());

    }
}
