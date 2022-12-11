package controller;

import exceptions.InvalidInputException;
import exceptions.NoMoreMovesLeftException;
import io.InputHandler;
import io.OutputHandler;
import model.board.Board;
import model.board.BoardItem;
import model.player.Player;
import utils.Constants;

import java.util.ArrayList;

public class PlayerController {
    private BoardController boardController;
    private OutputHandler outputHandler;

    public PlayerController() {
        this.boardController = new BoardController();
        this.outputHandler = new OutputHandler();
    }

    public void createPlayer(Player player, int playerId, InputHandler input) {
        // set playerId, player moves and move num
        player.setPlayerId(playerId);
        player.setMoves(input.playersMoves[playerId].split(" "));
        player.setMoveNum(0);

        // create new board and add ships to it
        Board board = new Board();
        board = boardController.createBoard(board, input.boardDimensions[0], input.boardDimensions[1]);
        board = boardController.setupPlayerBoard(board, playerId, input.shipsInfo);
        player.setBoard(board);
    }

    public boolean makeMove(Player currentPlayer, ArrayList<Player> allPlayers) {
        boolean successfullyHitAnyPlayer = false;
        String nextMove = getNextMove(currentPlayer);

        // hit all players except self
        for (Player targetPlayer : allPlayers) {
            if (targetPlayer.getPlayerId() != currentPlayer.getPlayerId()) {
                if (takeHit(targetPlayer, currentPlayer, nextMove)) {
                    successfullyHitAnyPlayer = true;
                }
            }
        }
        return successfullyHitAnyPlayer;
    }

    private String getNextMove(Player currentPlayer) {
        String[] moves = currentPlayer.getMoves();
        int moveNum = currentPlayer.getMoveNum();
        String nextMove;

        if (moves.length > moveNum) {
            nextMove = moves[moveNum];
            moveNum++;
            currentPlayer.setMoveNum(moveNum);
        } else {
            throw new NoMoreMovesLeftException();
        }
        return nextMove;
    }

    private boolean takeHit(Player targetPlayer, Player sourcePlayer, String move) {
        int X = (int) move.charAt(0) - (int) 'A';
        int Y = move.charAt(1) - '0' - 1;
        try {
            if (boardController.makeHit(targetPlayer, X, Y)) {
                outputHandler.printHitMsg(sourcePlayer.getPlayerId(), targetPlayer.getPlayerId(), move, true);
                return true;
            } else {
                outputHandler.printHitMsg(sourcePlayer.getPlayerId(), targetPlayer.getPlayerId(), move, false);
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            outputHandler.printInvalidInputMsg(sourcePlayer.getPlayerId(), move);
            return false;
        }
    }

    public boolean anyShipsLeft(Player player) {
        Board board = player.getBoard();
        BoardItem[][] boardItems = board.getBoardItems();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (boardItems[i][j].getType() == Constants.BOARD_ITEM_SHIP_P.label
                        || boardItems[i][j].getType() == Constants.BOARD_ITEM_SHIP_Q.label) {
                    // Future Scope: keep array of type of ships
                    return true;
                }
            }
        }
        return false;
    }

    public Player firstPlayer(ArrayList<Player> allPlayers) {
        if (allPlayers.size() == 0) {
            throw new InvalidInputException();
        }
        return allPlayers.get(0);
    }

    public Player pickNextPlayer(Player currentPlayer, ArrayList<Player> allPlayers) {
        int nextPlayerId = (currentPlayer.getPlayerId() + 1) % allPlayers.size();
        return allPlayers.get(nextPlayerId);
    }

    public boolean anyMovesLeft(ArrayList<Player> allPlayers) {
        for (Player player : allPlayers) {
            String[] moves = player.getMoves();
            int moveNum = player.getMoveNum();

            if (moves.length > moveNum) return true;
        }
        return false;
    }
}
