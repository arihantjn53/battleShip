package controller;

import exceptions.InvalidInputException;
import exceptions.NoMoreMovesLeftException;
import io.InputHandler;
import io.OutputHandler;
import model.board.Board;
import model.board.BoardItem;
import model.player.Player;

import java.util.ArrayList;
public class PlayerController {
    private BoardController boardController;

    public PlayerController() {
        this.boardController = new BoardController();
    }

    public void createPlayer(Player player, int playerId, InputHandler input) {
        player.setPlayerId(playerId);
        player.setMoves(input.playersMoves[playerId].split(" "));
        player.setMoveNum(0);

        Board board = new Board();
        board = boardController.createBoard(board, input.boardDimensions[0], input.boardDimensions[1]);
        board = boardController.setupPlayerBoard(board, playerId, input.shipsInfo);
        player.setBoard(board);
    }

    public boolean makeMove(Player currentPlayer, ArrayList<Player> allPlayers) {
        boolean successfullyHitAnyPlayer = false;
        String nextMove = getNextMove(currentPlayer);

        // hit all players except self
        for (Player targetPlayer: allPlayers) {
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

        if(moves.length > moveNum) {
            nextMove = moves[moveNum];
            moveNum++;
            currentPlayer.setMoveNum(moveNum);
        }
        else {
            throw new NoMoreMovesLeftException();
        }
        return nextMove;
    }

    private boolean takeHit(Player targetPlayer, Player sourcePlayer, String move) {
        OutputHandler output = new OutputHandler();
        int X = (int) move.charAt(0) - (int) 'A';
        int Y = move.charAt(1) - '0' - 1;
        if (boardController.makeHit(targetPlayer, X, Y)) {
            output.printHitMsg(sourcePlayer.getPlayerId(), targetPlayer.getPlayerId(), move, true);
            return true;
        }
        else {
            output.printHitMsg(sourcePlayer.getPlayerId(), targetPlayer.getPlayerId(), move, false);
            return false;
        }
    }

    public boolean anyShipsLeft(Player player) {
        Board board = player.getBoard();
        BoardItem[][] boardItems = board.getBoardItems();

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (boardItems[i][j].getType() == 'P' || boardItems[i][j].getType() == 'Q') {
                    // Future: keep array of type of ships
                    return true;
                }
            }
        }
        return false;
    }

    public int firstPlayer(ArrayList<Player> allPlayers) {
        if (allPlayers.size() == 0) {
            throw new InvalidInputException();
        }
        return 0;
    }

    public int pickNextPlayer(int currentPlayerIndex, ArrayList<Player> allPlayers) {
        return (currentPlayerIndex + 1) % allPlayers.size();
    }
}
