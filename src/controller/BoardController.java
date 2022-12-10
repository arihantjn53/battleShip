package controller;
import model.board.Board;
import model.board.BoardItem;
import model.player.Player;

public class BoardController {
    public BoardController() {
    }

    public Board createBoard(Board board, String row, String column) {
        int width = Integer.parseInt(row);
        int height = (int) column.charAt(0) - (int) 'A' + 1;

        BoardItem[][] boardItems = new BoardItem[width][height];
        board.setWidth(width);
        board.setHeight(height);

//        System.out.println(board.getWidth() + " " + board.getHeight());

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                BoardItem water = new BoardItem();
                water.setType('-');
                boardItems[i][j] = water;
            }
        }
        board.setBoardItems(boardItems);
        return board;
    }

    public void addShip(Board board, BoardItem shipType, int shipWidth, int shipLength, String shipLocation) {
        int startX = (int) shipLocation.charAt(0) - (int) 'A';
        int startY = shipLocation.charAt(1) - '0' - 1;

        BoardItem[][] boardItems = board.getBoardItems();

        for(int i = startX; i < startX + shipWidth; i++) {
            for(int j = startY; j < startY + shipLength; j++) {
                boardItems[i][j] = shipType;
            }
        }
    }

    public void printBoard(Board board) {
        BoardItem[][] boardItems = board.getBoardItems();

        System.out.print("  ");
        for(int i = 0; i < board.getWidth(); i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();

        for(int i = 0; i < board.getWidth(); i++) {
            System.out.print((char)(i + 'A') + " ");
            for(int j = 0; j < board.getHeight(); j++) {
                System.out.print(boardItems[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean makeHit(Player targetPlayer, int x, int y) {
        BoardItem[][] boardItems = targetPlayer.getBoard().getBoardItems();
        if (boardItems[x][y].getType() == 'Q') {
            boardItems[x][y].setType('P');
            return true;
        } else if (boardItems[x][y].getType() == 'P') {
            boardItems[x][y].setType('X');
            return true;
        } else return false;
    }
}
