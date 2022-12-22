package arguments.providers;

import arguments.holders.LabelIdValidationArgumentsHolder;
import io.restassured.specification.Argument;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class LabelIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new LabelIdValidationArgumentsHolder(
                        Map.of("id", "invalid"),
                        "invalid id",
                        400
                ),
                new LabelIdValidationArgumentsHolder(
                        Map.of("id", "63893179bab4298981723650"),
                        "The requested resource was not found.",
                        404
                )
        ).map(Arguments::of);
    }
}
