package controller;

import model.board.Board;
import model.board.BoardItem;
import model.player.Player;
import io.OutputHandler;
import utils.Constants;

public class BoardController {
    OutputHandler outputHandler;

    public BoardController() {
        outputHandler = new OutputHandler();
    }

    public Board createBoard(Board board, String row, String column) {
        int width = Integer.parseInt(row);
        int height = (int) column.charAt(0) - (int) 'A' + 1;

        BoardItem[][] boardItems = new BoardItem[height][width];
        board.setWidth(width);
        board.setHeight(height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BoardItem water = new BoardItem();
                water.setType(Constants.BOARD_ITEM_WATER.label);
                boardItems[i][j] = water;
            }
        }
        board.setBoardItems(boardItems);
        return board;
    }

    public Board setupPlayerBoard(Board board, int playerId, String[] shipsInfo) {
        for (String s : shipsInfo) {
            String[] shipInfo = s.split(" ");
            char shipType = shipInfo[0].charAt(0);
            int shipWidth = Integer.parseInt(shipInfo[1]);
            int shipLength = Integer.parseInt(shipInfo[2]);
            int playerIndex = playerId + 3;
            String shipLocation = shipInfo[playerIndex];

            try {
                addShip(board, shipType, shipWidth, shipLength, shipLocation);
            } catch (ArrayIndexOutOfBoundsException exception) {
                outputHandler.printInvalidInputMsg(playerId, shipLocation);
            }
        }
        outputHandler.printBoard(playerId, board);
        return board;
    }

    private void addShip(Board board, char shipType, int shipWidth, int shipLength, String shipLocation) {
        int startX = (int) shipLocation.charAt(0) - (int) 'A';
        int startY = shipLocation.charAt(1) - '0' - 1;

        BoardItem[][] boardItems = board.getBoardItems();

        for (int i = startX; i < startX + shipLength; i++) {
            for (int j = startY; j < startY + shipWidth; j++) {
                boardItems[i][j].setType(shipType);
            }
        }
        board.setBoardItems(boardItems);
    }

    public boolean makeHit(Player targetPlayer, int x, int y) {
        BoardItem[][] boardItems = targetPlayer.getBoard().getBoardItems();
        if (boardItems[x][y].getType() == Constants.BOARD_ITEM_SHIP_Q.label) {
            boardItems[x][y].setType(Constants.BOARD_ITEM_SHIP_P.label);
            targetPlayer.getBoard().setBoardItems(boardItems);
            return true;
        } else if (boardItems[x][y].getType() == Constants.BOARD_ITEM_SHIP_P.label) {
            boardItems[x][y].setType(Constants.BOARD_ITEM_X.label);
            targetPlayer.getBoard().setBoardItems(boardItems);
            return true;
        } else return false;
    }
}
