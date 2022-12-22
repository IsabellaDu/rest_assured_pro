package arguments.providers;

import arguments.holders.CardBodyValidationArgumentsHolder;
import consts.BoardsEndPoints;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class CardBodyValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", 12345,
                                "idList", UrlParamValues.EXISTING_LIST_ID
                        ),
                        "invalid value for name"
                ),
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", "new Card"
                        ),
                        "invalid value for idList"
                ),
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", "new Card",
                                "idList", "invalid"
                        ),
                        "invalid value for idList"
                ),
                new CardBodyValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "invalid value for idList"
                )
        ).map(Arguments::of);
    }
}
