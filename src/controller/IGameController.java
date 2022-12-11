package controller;

import model.game.Game;
import model.player.Player;

import java.util.ArrayList;

public interface IGameController {
    void createGame(Game game, ArrayList<Player> players);
    void startGame(Game game);

}
