package src.main;

import main.PlayersTurn;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PlayersTurnTest {

    private static final String NEW_LINE = System.lineSeparator();
    private List<Error> errors = new ArrayList<>();
    private boolean printStackTraces = false;

    @Test
    public void playerOneTurnTest() {
        try {
            PlayersTurn playersTurn = new PlayersTurn();
            Assert.assertTrue(playersTurn.playerOneTurn(1));
            Assert.assertFalse(playersTurn.playerOneTurn(2));
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void playerTwoTurnTest() {
        try {
            PlayersTurn playersTurn = new PlayersTurn();
            Assert.assertTrue(playersTurn.playerTwoTurn(2));
            Assert.assertFalse(playersTurn.playerTwoTurn(1));
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void playerOneWonTest() {
        PlayersTurn playersTurn = new PlayersTurn();
        int[] pots = new int[14];
        pots[8] = 0;
        pots[9] = 0;
        pots[10] = 0;
        pots[11] = 0;
        pots[12] = 0;
        pots[13] = 0;
        try {
            Assert.assertTrue(playersTurn.playerOneWon(pots));
            pots[8] = 1;
            Assert.assertFalse(playersTurn.playerOneWon(pots));

        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void playerTwoWonTest() {
        PlayersTurn playersTurn = new PlayersTurn();
        int[] pots = new int[14];
        pots[1] = 0;
        pots[2] = 0;
        pots[3] = 0;
        pots[4] = 0;
        pots[5] = 0;
        pots[6] = 0;
        try {
            Assert.assertTrue(playersTurn.playerTwoWon(pots));
            pots[2] = 1;
            Assert.assertFalse(playersTurn.playerTwoWon(pots));

        } catch (AssertionError e) {
            errors.add(e);
        }
    }


    public void processAllAssertions() {
        if (errors.isEmpty()) {
            System.out.println("no error occurred");
        } else {
            StringBuilder msgBuilder = new StringBuilder();
            for (Error error : errors) {
                msgBuilder.append(NEW_LINE);
                msgBuilder.append(AssertionError.class.getSimpleName());
                msgBuilder.append(" ");
                msgBuilder.append(errors.indexOf(error) + 1);
                msgBuilder.append(": ");
                if (printStackTraces) {
                    msgBuilder.append(NEW_LINE);
                    msgBuilder.append(error.getStackTrace());
                } else {
                    msgBuilder.append(error.getMessage());
                }
            }
            Assert.fail(errors.size() + " " + AssertionError.class.getSimpleName() + "(s) were thrown:" + NEW_LINE
                    + msgBuilder.toString());
        }
    }
}
