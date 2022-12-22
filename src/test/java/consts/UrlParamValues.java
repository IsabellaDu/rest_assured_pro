package consts;

import java.util.Map;

public class UrlParamValues {

    public static final String VALID_KEY = "aa743b15c3bb1e7710fe440c67e1f47a";
    public static final String VALID_TOKEN = "41e83bb5a28a399479ca3141669c545b2fd04a9fde1796c2f2207d77903c5643";

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "aa743b15c3bb1e7710fe440c67e1f47a",
            "token", "41e83bb5a28a399479ca3141669c545b2fd04a9fde1796c2f2207d77903c5611"
    );

    public static final String EXISTING_BOARD_ID = "63893179420445010054a330";
    public static final String USER_NAME = "izabelladudaryk";

    public static final String EXISTING_CARD_ID = "6392618248114f064f035748";
    public static final String EXISTING_LIST_ID = "63893179420445010054a337";

    public static final String EXISTING_LABEL_ID = "63893179bab429898172365f";

    public static final String EXISTING_STICKERS_ID = "6392618248114f064f035748";
    public static final String EXISTING_STICKER_ID = "6398f0bbfc95bf00d71c128d";

}
