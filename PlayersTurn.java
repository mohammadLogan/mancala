package main;

public class PlayersTurn {

    public boolean playerOneTurn(int gameState) {
        if (gameState == States.PLAYER_ONE_TURN.getValue())
            return true;
        else
            return false;
    }

    public boolean playerTwoTurn(int gameState) {
        if (gameState == States.PLAYER_TWO_TURN.getValue())
            return true;
        else
            return false;
    }

    public  boolean playerTwoWon(int[] pots) {
        if (pots[1] == 0 && pots[2] == 0 && pots[3] == 0 && pots[4] == 0 && pots[5] == 0 && pots[6] == 0)
            return true;
        else
            return false;
    }

    public  boolean playerOneWon(int[] pots) {
        if (pots[8] == 0 && pots[9] == 0 && pots[10] == 0 && pots[11] == 0 && pots[12] == 0 && pots[13] == 0)
            return true;
        else
            return false;
    }

    public boolean gameFinish(int[] pots) {
        if (playerOneWon(pots) || playerTwoWon(pots))
            return true;
        else
            return false;
    }



}
