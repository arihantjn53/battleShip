package controller;

import io.InputHandler;
import model.player.Player;

import java.util.ArrayList;

public interface IPlayerController {
    void createPlayer(Player player, int playerId, InputHandler input);
    boolean makeMove(Player currentPlayer, ArrayList<Player> allPlayers);
    boolean anyShipsLeft(Player player);
    Player firstPlayer(ArrayList<Player> allPlayers);
    Player pickNextPlayer(Player currentPlayer, ArrayList<Player> allPlayers);
    boolean anyMovesLeft(ArrayList<Player> allPlayers);
}
