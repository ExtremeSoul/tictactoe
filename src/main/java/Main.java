import java.util.Scanner;

public class Main {
    public static void main(String[]  args) {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        System.out.println(game.getBoard());

        while (!game.isEnded()) {
            System.out.println(String.format("%s's turn:", game.getActualPlayerSymbol()));

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            try {
                game.nextMove(x, y);
            }catch (BoardCellNotEmptyException e) {
                System.out.println("WRONG MOVE, CELL NOT EMPTY");
            }catch ()

            System.out.println(game.getBoard());
        }
    }
}
