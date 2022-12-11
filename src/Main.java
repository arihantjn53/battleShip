import controller.GameController;
import controller.PlayerController;
import io.InputHandler;
import model.game.Game;
import model.player.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        PlayerController playerController = new PlayerController();
        GameController gameController = new GameController();
        inputHandler.getInput();

        // create players and setup their board
        ArrayList<Player> players = new ArrayList<>();
        for (int playerId = 0; playerId < inputHandler.nPlayers; playerId++) {
            Player player = new Player();
            playerController.createPlayer(player, playerId, inputHandler);
            players.add(player);
        }

        // create game with created players and their board
        // then start the game
        Game game = new Game();
        gameController.createGame(game, players);
        gameController.startGame(game);
    }
}