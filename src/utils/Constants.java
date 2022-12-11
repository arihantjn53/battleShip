package utils;

public enum Constants {
    BOARD_ITEM_X('X'),
    BOARD_ITEM_WATER('-'),
    BOARD_ITEM_SHIP_P('P'),
    BOARD_ITEM_SHIP_Q('Q');

    public char label;
    private Constants(char label) {
        this.label = label;
    }
}
