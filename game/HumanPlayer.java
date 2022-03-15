package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println("Current position");
        System.out.println(position);
        int r = 1, c = 1;
        while (true) {
            try {
                System.out.println("Enter you move for " + position.getTurn());
                r = in.nextInt(); c = in.nextInt();
                break;
            }
            catch (Exception e) {
                in.nextLine();
            }
        }
        return new Move(r - 1, c - 1, position.getTurn());
    }
}
