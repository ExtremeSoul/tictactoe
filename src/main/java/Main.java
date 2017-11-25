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
            } catch (BoardCellNotEmptyException e) {
                System.out.println("Wrong move, cell not empty!");
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect move!");
            }

            System.out.println(game.getBoard());
        }
    }
}
