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
//        input.getInput();
        // 5 E - model.board dimensions
        //2 - number of ships
        //Q 1 1 A1 B2 - shiptype, shipWidth, shipLength, player1ShipLocation, player2ShipLocation
        //P 2 1 D4 C3
        //A1 B2 B2 B3 - player1Moves
        //A1 B2 B3 A1 D1 E1 D4 D4 D5 D5 - player2Moves

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

//Player-1 fires a missile with target A1 which got miss
//Player-2 fires a missile with target A1 which got hit
//Player-2 fires a missile with target B2 which got miss
//Player-1 fires a missile with target B2 which got hit
//Player-1 fires a missile with target B2 which got hit
//Player-1 fires a missile with target B3 which got miss
//Player-2 fires a missile with target B3 which got miss
//Player-1 has no more missiles left to launch
//Player-2 fires a missile with target A1 which got hit
//Player-2 fires a missile with target D1 which got miss
//Player-1 has no more missiles left to launch
//Player-2 fires a missile with target E1 which got miss
//Player-1 has no more missiles left to launch
//Player-2 fires a missile with target D4 which got hit
//Player-2 fires a missile with target D4 which got miss
//Player-1 no more missiles left to launch
//Player-2 fires a missile with target D5 which got hit
//Player-2 won the battle