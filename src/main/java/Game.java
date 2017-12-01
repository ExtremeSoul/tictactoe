import java.util.Arrays;

public class Game {
    private static final int BOARD_SIZE = 3;
    private static final String BOARD_TEMPLATE = "   1 2 3 \n" +
                                                 "  ┌─┬─┬─┐\n" +
                                                 "1 │%s│%s│%s│\n" +
                                                 "  ├─┼─┼─┤\n" +
                                                 "2 │%s│%s│%s│\n" +
                                                 "  ├─┼─┼─┤\n" +
                                                 "3 │%s│%s│%s│\n" +
                                                 "  └─┴─┴─┘\n";

    private Symbol[] board = new Symbol[BOARD_SIZE * BOARD_SIZE];
    private boolean isXTurn = true;

    public Game() {
        Arrays.fill(board, Symbol.EMPTY);
    }

    public String getBoard() {
        return String.format(BOARD_TEMPLATE, (Object[]) board);
    }

    public void nextMove(int x, int y) {
        if (isXTurn) {
            putX(x, y);
        } else {
            putO(x, y);
        }

        isXTurn = !isXTurn;
    }

    public void putX(int x, int y) {
        putSymbol(x - 1, y - 1, Symbol.X);
    }

    public void putO(int x, int y) {
        putSymbol(x - 1, y - 1, Symbol.O);
    }

    private void putSymbol(int x, int y, Symbol symbol) {

//        if(symbol == Symbol.EMPTY) {
//            int i = (y - 1) * BOARD_SIZE + (x - 1);
//            board[i] = symbol;
//        }else{
//            throw new IndexOutOfBoundsException("POLE JEST ZAJETE");
//        }

        if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            throw new IllegalArgumentException();
        }

        int i = y * BOARD_SIZE + x;

        if(board[i] != Symbol.EMPTY) {
            throw new BoardCellNotEmptyException();
        }
        board[i] = symbol;
    }

    private Symbol getSymbol(int x, int y) {
        int i = y * BOARD_SIZE + x;
        return board[i];
    }

    public boolean isEnded() {
        if (isWin()) {
            return true;
        }

        return isDraw();
    }

    public boolean isWin(){
        return checkColumn(0) || checkColumn(1) || checkColumn(2) ||
                checkRow(0) || checkRow(1) || checkRow(2) ||
                checkLeftCross() || checkRightCross();
    }

    public boolean isDraw() {
        for (Symbol symbol : board) {
            if (symbol == Symbol.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public Symbol getWinner() {
        return !isXTurn ? Symbol.X : Symbol.O;
    }

    private boolean checkColumn(int column) {
        Symbol first = getSymbol(column, 0);
        if(first == Symbol.EMPTY) {
            return false;
        }

        return getSymbol(column, 1) == first && getSymbol(column, 2) == first;
    }

    private boolean checkRow(int row) {
        Symbol first = getSymbol(0, row);
        if(first == Symbol.EMPTY) {
            return false;
        }

        return getSymbol(1, row) == first && getSymbol(2, row) == first;
    }

    private boolean checkLeftCross() {
        Symbol first = getSymbol(0, 0);
        if(first == Symbol.EMPTY) {
            return false;
        }

        return getSymbol(1, 1) == first && getSymbol(2, 2) == first;
    }

    private boolean checkRightCross() {
        Symbol first = getSymbol(2, 0);
        if(first == Symbol.EMPTY) {
            return false;
        }

        return getSymbol(1, 1) == first && getSymbol(0, 2) == first;
    }

    public Symbol getActualPlayerSymbol() {
        return isXTurn ? Symbol.X : Symbol.O; // Skrócony if. pierwszy jest warunek, później co jest zwrocone przy true, poxniej co przy false
    }
}
