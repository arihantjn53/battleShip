import controller.GameController;
import controller.PlayerController;
import io.InputHandler;
import model.game.Game;
import model.player.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputHandler input = new InputHandler();
        PlayerController playerController = new PlayerController();
        GameController gameController = new GameController();
        // TODO - remove comment
//        input.getInput();

        // create players
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < input.nPlayers; i++) {
            Player player = new Player();
            playerController.createPlayer(player, i, input);
            players.add(player);
        }

        // create game with created players and their board and start game
        Game game = new Game();
        gameController.createGame(game, players);
        gameController.startGame(game);
    }
}