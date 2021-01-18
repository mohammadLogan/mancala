package main;

public enum States {

    GAME_OVER(-2), GAME_TIE(-1),
    PLAYER_ONE_WON(1000), PLAYER_TWO_WON(2000),

    PLAYER_ONE_TURN(1), PLAYER_TWO_TURN(2),
    MANCALA_1(7), MANCALA_2(0);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    States(int value) {
        this.value = value;
    }
}
