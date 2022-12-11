package controller;

import model.board.Board;
import model.player.Player;

public interface IBoardController {
    Board createBoard(Board board, String row, String column);
    Board setupPlayerBoard(Board board, int playerId, String[] shipsInfo);
    boolean makeHit(Player targetPlayer, int x, int y);
}
