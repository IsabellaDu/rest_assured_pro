package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.BoardsEndPoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class GetCardsValidationTest extends BaseTest {

    // automated test to access a card with invalid id
    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardsWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardsEndPoints.GET_ALL_CARDS_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    // test method checking unauthorized permission requested
    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(BoardsEndPoints.GET_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    // test trying to access specific card with another user credentials
    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(BoardsEndPoints.GET_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}
