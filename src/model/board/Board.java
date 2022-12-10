package model.board;

public class Board {

    private BoardItem[][] board;
    private int width;
    private int height;

    public BoardItem[][] getBoardItems() {
        return board;
    }

    public void setBoardItems(BoardItem[][] board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
