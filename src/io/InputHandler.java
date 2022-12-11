package io;

import java.util.Scanner;

public class InputHandler {
    public String[] boardDimensions;
    public int nShips;
    public String[] shipsInfo;
    // setting number of players to two, can be taken as an input
    public int nPlayers = 2;
    public String[] playersMoves;

    public void getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Board Dimensions:");
        String boardDimensionsInput = input.nextLine();
        boardDimensions = boardDimensionsInput.split(" ");

        System.out.println("Enter Number of Ships:");
        String nShipsInput = input.nextLine();
        nShips = Integer.parseInt(nShipsInput);

        System.out.println("Enter " + nShips + " Ships Info:");
        shipsInfo = new String[nShips];

        for (int i = 0; i < nShips; i++) {
            String shipInfo = input.nextLine();
            shipsInfo[i] = shipInfo;
        }

        System.out.println("Enter " + nPlayers + " Players Moves:");
        playersMoves = new String[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            String moves = input.nextLine();
            playersMoves[i] = moves;
        }
    }
}
