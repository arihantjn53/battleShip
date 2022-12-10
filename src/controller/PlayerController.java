package controller;

import exceptions.InvalidInputException;
import exceptions.NoMoreMovesLeftException;
import io.InputHandler;
import io.OutputHandler;
import model.board.Board;
import model.board.BoardItem;
import model.player.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerController {
    private BoardController boardController;

    public PlayerController() {
        this.boardController = new BoardController();
    }

    public void createPlayer(Player player, int playerId, InputHandler input) {
        //TODO: separate into create player and setup player board
        player.setPlayerId(playerId);
        player.setMoves(input.playersMoves[playerId].split(" "));
        player.setMoveNum(0);

        Board board = new Board();
        board = boardController.createBoard(board, input.boardDimensions[0], input.boardDimensions[1]);

        for(int i = 0; i < input.nShips; i++) {
            String[] shipInfo = input.shipsInfo[i].split(" ");
            BoardItem shipType = new BoardItem();
            shipType.setType(shipInfo[0].charAt(0));
            int shipWidth = Integer.parseInt(shipInfo[1]);
            int shipLength = Integer.parseInt(shipInfo[2]);
            int playerIndex = playerId + 3;
            String shipLocation = shipInfo[playerIndex];

            boardController.addShip(board, shipType, shipWidth, shipLength, shipLocation);
        }
        player.setBoard(board);
        boardController.printBoard(board);
    }

    public void makeMove(Player currentPlayer, ArrayList<Player> allPlayers) {
        boolean successfullyHitAnyPlayer = false;
        String[] moves = currentPlayer.getMoves();
        int moveNum = currentPlayer.getMoveNum();
        int playerId = currentPlayer.getPlayerId();

        if (moves.length > moveNum) {
            for (Player targetPlayer: allPlayers) {
                if (targetPlayer.getPlayerId() != playerId) {
                    if (makeHit(targetPlayer, playerId, moves[moveNum])) {
                        successfullyHitAnyPlayer = true;
                        boardController.printBoard(targetPlayer.getBoard());
                    }
                }
            }
            moveNum++;
            currentPlayer.setMoveNum(moveNum);
            if (successfullyHitAnyPlayer) {
                makeMove(currentPlayer, allPlayers);
            }
        }
        else {
            throw new NoMoreMovesLeftException();
        }
    }

    private boolean makeHit(Player targetPlayer, int sourcePlayerId, String move) {
        OutputHandler output = new OutputHandler();
        int X = (int) move.charAt(0) - (int) 'A';
        int Y = move.charAt(1) - '0' - 1;
        if (boardController.makeHit(targetPlayer, X, Y)) {
            output.printHitMsg(sourcePlayerId, targetPlayer.getPlayerId(), move, true);
            return true;
        }
        else {
            output.printHitMsg(sourcePlayerId, targetPlayer.getPlayerId(), move, false);
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

    public Integer firstPlayer(ArrayList<Player> allPlayers) {
        if (allPlayers.size() == 0) {
            throw new InvalidInputException();
        }
        return 0;
    }

    public int pickNextPlayer(int currentPlayerIndex, ArrayList<Player> allPlayers) {
        return (currentPlayerIndex + 1) % allPlayers.size();
    }
}
