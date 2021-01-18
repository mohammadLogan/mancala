package main;

import java.util.Scanner;

public class Mancala {
    private static PlayersTurn playersTurn = new PlayersTurn();
    private static int gameState = 1;
    private static int[] pots = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6};
    private static int lastindex;

    private int pot;

    public static void main(String[] args) {
        gameState = States.PLAYER_ONE_TURN.getValue();
        System.out.println("   6  6  6  6  6  6");
        System.out.println("0                    0");
        System.out.println("   6, 6, 6, 6, 6, 6");
        while ((gameState == States.PLAYER_ONE_TURN.getValue() || gameState == States.PLAYER_TWO_TURN.getValue()) && !playersTurn.gameFinish(pots)) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            if (playersTurn.playerOneTurn(gameState))
                System.out.println("\u001B[37m" + "Player 1 turn:");
            if (playersTurn.playerTwoTurn(gameState))
                System.out.println("\u001B[37m" + "Player 2 turn:");

            int potNumber = changeInputToInt(myObj.nextLine());  // Read user input
            if ((potNumber != States.MANCALA_1.getValue() && potNumber != States.MANCALA_2.getValue()) && pots[potNumber] != 0) {
                if (canPickPot(potNumber, gameState)) {
                    int startPotValue = pots[potNumber];
                    pots[potNumber] = 0;
                    for (int i = 1; i <= startPotValue; i++) {
                        potNumber++;

                        if (potNumber == 14)
                            potNumber = 0;
                        lastindex = potNumber;
                        int valueOfPot = pots[potNumber];
                        pots[potNumber] = valueOfPot + 1;
                    }

                    if (pots[lastindex] == 1 && (lastindex != States.MANCALA_1.getValue() && lastindex != States.MANCALA_2.getValue())) {
                        captureEnemyPot(gameState, lastindex);
                    }
                    if (lastindex == States.MANCALA_1.getValue() || lastindex == States.MANCALA_2.getValue()) {
                        if (playersTurn.playerOneTurn(gameState) && lastindex == States.MANCALA_1.getValue())
                            gameState = States.PLAYER_ONE_TURN.getValue();
                        else if (playersTurn.playerTwoTurn(gameState) && lastindex == States.MANCALA_2.getValue())
                            gameState = States.PLAYER_TWO_TURN.getValue();
                    } else {
                        if (gameState == States.PLAYER_ONE_TURN.getValue())
                            gameState = States.PLAYER_TWO_TURN.getValue();
                        else
                            gameState = States.PLAYER_ONE_TURN.getValue();
                    }

                } else {
                    if (playersTurn.playerOneTurn(gameState))
                        System.out.println("\033[0;31m" + "u cant pick pots of player 2");
                    else
                        System.out.println("\033[0;31m" + "u cant pick pots of player 1");

                }
            } else {
                System.out.println("\033[0;31m" + "u cant pick MANCALA or empty pot");
            }

            showResult(pots);
        }
        if (playersTurn.gameFinish(pots)) {
            int mancala2 = pots[0];
            int mancala1 = pots[7];

            if (playersTurn.playerOneWon(pots)) {
                pots[7] = mancala1 + pots[1] + pots[2] + pots[3] + pots[4] + pots[5] + pots[6];
                setPotZero(1, 6);
            } else if (playersTurn.playerTwoWon(pots)) {
                pots[0] = mancala2 + pots[8] + pots[9] + pots[10] + pots[11] + pots[12] + pots[13];
                setPotZero(8, 13);
            }
            showResult(pots);
            gameState = States.GAME_OVER.getValue();
        }


        if (gameState == States.GAME_OVER.getValue())
            System.out.println("GAME FINISH");

        else if (gameState == States.GAME_TIE.getValue())
            System.out.println("GAME TIE");
    }

    private static void showResult(int[] pots) {
        System.out.println("\u001B[37m"+"   " + pots[13] + " " + pots[12] + " " + pots[11] + " " + pots[10] + " " + pots[9] + " " + pots[8]);
        System.out.println("\u001B[37m"+pots[0] + "                " + pots[7]);
        System.out.println("\u001B[37m"+"   " + pots[1] + " " + pots[2] + " " + pots[3] + " " + pots[4] + " " + pots[5] + " " + pots[6]);
    }

    private static void setPotZero(int first, int last) {
        for (int i = first; i <= last; i++) {
            pots[i] = 0;
        }
    }

    private static int changeInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean canPickPot(int potNumber, int playerNumber) {

        if ((playersTurn.playerOneTurn(gameState) && (potNumber == 1 || potNumber == 2 || potNumber == 3 || potNumber == 4 || potNumber == 5 || potNumber == 6)) ||
                ((playersTurn.playerTwoTurn(gameState) && (potNumber == 8 || potNumber == 9 || potNumber == 10 || potNumber == 11 || potNumber == 12 || potNumber == 13))))
            return true;
        else
            return
                    false;

    }

    private static void captureEnemyPot(int gameState, int lastindex) {
        if (gameState == States.PLAYER_ONE_TURN.getValue()) {
            if (lastindex == 1) {
                setAttributesOfPots(1, 13);
            }
            if (lastindex == 2) {
                setAttributesOfPots(2, 12);
            }
            if (lastindex == 3) {
                setAttributesOfPots(3, 11);
            }
            if (lastindex == 4) {
                setAttributesOfPots(4, 10);
            }
            if (lastindex == 5) {
                setAttributesOfPots(5, 9);
            }
            if (lastindex == 6) {
                setAttributesOfPots(6, 8);
            }
        } else {
            if (lastindex == 8) {
                setAttributesOfPots(8, 6);
            }
            if (lastindex == 9) {
                setAttributesOfPots(9, 5);
            }
            if (lastindex == 10) {
                setAttributesOfPots(10, 4);
            }
            if (lastindex == 11) {
                setAttributesOfPots(11, 3);
            }
            if (lastindex == 12) {
                setAttributesOfPots(12, 2);
            }
            if (lastindex == 13) {
                setAttributesOfPots(13, 1);
            }

        }
    }

    private static void setAttributesOfPots(int lastIndex, int potIndex) {
        int mancala1Value = pots[States.MANCALA_1.getValue()];
        int mancala2Value = pots[States.MANCALA_2.getValue()];
        int valueOfPot = pots[potIndex];
        pots[potIndex] = 0;
        pots[lastIndex] = 0;
        if (gameState == States.PLAYER_ONE_TURN.getValue())
            pots[States.MANCALA_1.getValue()] = mancala1Value + valueOfPot + 1;
        else
            pots[States.MANCALA_2.getValue()] = mancala2Value + valueOfPot + 1;
    }
}


