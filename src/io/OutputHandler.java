package io;

import model.board.Board;
import model.board.BoardItem;

public class OutputHandler {
    public void printMsg(String msg) {
        System.out.println(msg);
    }

    public void printHitMsg(int sourcePlayerId, int targetPlayerId, String move, boolean b) {
        System.out.println("Player " + (sourcePlayerId + 1) + " fires a missile on Player "+ (targetPlayerId + 1) + " with target " + move + " which got " + (b ? "hit." : "miss."));
    }

    public void printNoMovesMsg(int currentPlayerIndex) {
        System.out.println("Player "+ (currentPlayerIndex + 1) + " has no more missiles left to launch.");
    }

    public void printWinner(int winnerId) {
        System.out.print("Player " + (winnerId + 1) + " won the battle.");
    }

    public void printInvalidInputMsg(int playerId, String location) {
        System.out.println("Invalid input " + location + " for Player " + (playerId + 1));
    }

    public void printBoard(int playerId, Board board) {
        System.out.println("Player " + (playerId+1) + " Initial Board:");
        BoardItem[][] boardItems = board.getBoardItems();

        System.out.print("  ");
        for(int i = 0; i < board.getWidth(); i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();

        for(int i = 0; i < board.getHeight(); i++) {
            System.out.print((char)(i + 'A') + " ");
            for(int j = 0; j < board.getWidth(); j++) {
                System.out.print(boardItems[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
