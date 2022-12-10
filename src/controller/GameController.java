package controller;

import exceptions.NoMoreMovesLeftException;
import io.OutputHandler;
import model.game.Game;
import model.player.Player;

import java.util.ArrayList;

public class GameController {
    private OutputHandler outputHandler;
    private PlayerController playerController;

    public GameController() {
        outputHandler = new OutputHandler();
        playerController = new PlayerController();
    }

    public void createGame(Game game, ArrayList<Player> players) {
        game.setPlayers(players);
    }

    public void startGame(Game game) {
        ArrayList<Player> players = game.getPlayers();

        // first player index, this will always return 0
        // FUTURE SCOPE: can be randomized
        int currentPlayerIndex = playerController.firstPlayer(players);

        outputHandler.printMsg("Starting Game!");

        while (getWinner(players) == null) {
            Player currentPlayer = players.get(currentPlayerIndex);
            boolean continueMove = false;

            try {
                continueMove = playerController.makeMove(currentPlayer, players);
            } catch (NoMoreMovesLeftException exception) {
                outputHandler.printNoMovesMsg(currentPlayerIndex);
            }

            // check if we get a winner
            // by checking if any ship is left
            Player winner = getWinner(players);
            if (winner != null) {
                outputHandler.printWinner(winner.getPlayerId());
                break;
            }

            // if currentPlayer hit any ship continue,
            // else get nextPlayer
            if (!continueMove) {
                currentPlayerIndex = playerController.pickNextPlayer(currentPlayerIndex, players);
            }
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
