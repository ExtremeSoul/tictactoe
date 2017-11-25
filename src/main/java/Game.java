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
        putSymbol(x, y, Symbol.X);
    }

    public void putO(int x, int y) {
        putSymbol(x, y, Symbol.O);
    }

    private void putSymbol(int x, int y, Symbol symbol) {

//        if(symbol == Symbol.EMPTY) {
//            int i = (y - 1) * BOARD_SIZE + (x - 1);
//            board[i] = symbol;
//        }else{
//            throw new IndexOutOfBoundsException("POLE JEST ZAJETE");
//        }
        if (x < 1 || x > BOARD_SIZE || y < 1 || y > BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
        int i = (y - 1) * BOARD_SIZE + (x - 1);
        if (board[i] != Symbol.EMPTY) {
            throw new BoardCellNotEmptyException();
        }
        board[i] = symbol;
    }

    public boolean isEnded() {
        for (Symbol symbol : board) {
            if (symbol == Symbol.EMPTY) {
                return false;
            }
        }

        return true;
    }

    public Symbol getActualPlayerSymbol() {
        return isXTurn ? Symbol.X : Symbol.O;
    }
}
