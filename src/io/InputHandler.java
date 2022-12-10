package io;

import java.util.Scanner;

public class InputHandler {
    public String[] boardDimensions = {"5", "E"};
    public int nShips = 2;
    public String[] shipsInfo = {"Q 1 1 A1 B2", "P 2 1 D4 C3"};

    // setting number of players to two, can be taken as an input
    public int nPlayers = 2;
    public String[] playersMoves = {"A1 B2 B2 B3 C3", "A1 B2 B3 A1 D1 E1 D4 D4 D5 D5"};

    public void getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter model.board.Board Dimensions:");
        String boardDimensionsInput = input.nextLine();
        boardDimensions = boardDimensionsInput.split(" ");

        System.out.println("Enter Number of Ships:");
        String nShipsInput = input.nextLine();
        nShips = Integer.parseInt(nShipsInput);

        System.out.println("Enter Ships Info:");
        shipsInfo = new String[nShips];

        for(int i = 0; i < nShips; i++) {
            String shipInfo = input.nextLine();
            shipsInfo[i] = shipInfo;
        }

        System.out.println("Enter Players Moves:");
        playersMoves = new String[nPlayers];
        for(int i = 0; i < nPlayers; i++) {
            String moves = input.nextLine();
            playersMoves[i] = moves;
        }
    }
}
