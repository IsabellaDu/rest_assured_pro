package arguments.providers;

import arguments.holders.StickerIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class StickerIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new StickerIdValidationArgumentsHolder(
                        Map.of("id", "invalid",
                                "idSticker", "6398f0bbfc95bf00d71c128d"),
                        "invalid id",
                        400
                ),
                new StickerIdValidationArgumentsHolder(
                        Map.of("id", "6392618248114f064f035740",
                                "idSticker", "6398f0bbfc95bf00d71c128d"),
                        "The requested resource was not found.",
                        404
                )
        ).map(Arguments::of);
    }
}
