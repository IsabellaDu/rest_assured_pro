package arguments.providers;

import arguments.holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "invalid"),
                        "invalid id",
                        400
                ),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "6392618248114f064f035740"),
                        "model not found",
                        404
                )
        ).map(Arguments::of);
    }
}
