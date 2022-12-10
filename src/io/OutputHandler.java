package io;

public class OutputHandler {
    public void printMsg(String msg) {
        System.out.println(msg);
    }

    public void printBoard() {
        //TODO
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
}
