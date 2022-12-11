package controller;

import exceptions.NoMoreMovesLeftException;
import io.OutputHandler;
import model.game.Game;
import model.player.Player;

import java.util.ArrayList;

public class GameController {
    private PlayerController playerController;

    public GameController() {
        playerController = new PlayerController();
    }

    public void createGame(Game game, ArrayList<Player> players) {
        game.setPlayers(players);
    }

    public void startGame(Game game) {
        ArrayList<Player> players = game.getPlayers();

        // first player index, this will always return 0
        // FUTURE SCOPE: can be randomized
        Player currentPlayer = playerController.firstPlayer(players);
        Player winner = null;
        OutputHandler.printMsg("Starting Game!");

        while (playerController.anyMovesLeft(players)) {
            boolean continueMove = false;
            // if current player hit any ship of any player
            // continue move with same player
            try {
                continueMove = playerController.makeMove(currentPlayer, players);
            } catch (NoMoreMovesLeftException exception) {
                OutputHandler.printNoMovesMsg(currentPlayer.getPlayerId());
            }

            // check if we get a winner
            // by checking if any ship is left
            winner = getWinner(players);
            if (winner != null) {
                OutputHandler.printWinner(winner.getPlayerId());
                break;
            }

            // if currentPlayer hit any ship continue,
            // else get nextPlayer
            if (!continueMove) {
                currentPlayer = playerController.pickNextPlayer(currentPlayer, players);
            }
        }

        // if no player has any moves left and still no winner found
        if (!playerController.anyMovesLeft(players) && winner == null) {
            OutputHandler.printMsg("No player won the battle.");
        }
    }

    private Player getWinner(ArrayList<Player> allPlayers) {
        ArrayList<Player> playersLeft = new ArrayList<>();
        for (Player player : allPlayers) {
            if (playerController.anyShipsLeft(player)) {
                playersLeft.add(player);
            }
        }
        if (playersLeft.size() == 1) {
            return playersLeft.get(0);
        }
        return null;
    }
}
