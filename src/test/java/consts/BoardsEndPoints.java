package consts;

public class BoardsEndPoints {

    public static final String GET_BOARD_URL = "/1/boards/{id}";
    public static final String GET_ALL_BOARDS_URL = "/1/members/{member}/boards";

    public static final String GET_CARD_URL = "/1/cards/{id}";
    public static final String GET_ALL_CARDS_URL = "/1/lists/{id}/cards";

    public static final String GET_LABEL_URL = "/1/labels/{id}";

    public static final String GET_STICKER_URL = "/1/cards/{id}/stickers/{idSticker}";
    public static final String GET_ALL_STICKERS_URL = "/1/cards/{id}/stickers";

    public static final String CREATE_BOARD_URL = "/1/boards";
    public static final String DELETE_BOARD_URL = "/1/boards/{id}";

    public static final String CREATE_CARD_URL = "/1/cards";
    public static final String DELETE_CARD_URL = "/1/cards/{id}";

    public static final String CREATE_LABEL_URL = "/1/boards/{id}/labels";
    public static final String DELETE_LABEL_URL = "/1/labels/{id}";

    public static final String CREATE_STICKER_URL = "/1/cards/{id}/stickers";
    public static final String DELETE_STICKER_URL = "/1/cards/{id}/stickers/{idSticker}";
}
