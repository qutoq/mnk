package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int n, m, k;
        Board brd;
        System.out.println("Hex?");
        if (in.nextLine().equals("1")) {
            while (true) {
                try {
                    System.out.println("n k :");
                    n = in.nextInt(); k = in.nextInt();
                    m = n;
                    brd = new HexBoard(n, n, k);
                    break;
                }
                catch (Exception e){
                    in.nextLine();
                }
            }
        }
        else{
            while (true) {
                try {
                    System.out.println("n m k :");
                    n = in.nextInt(); m = in.nextInt(); k = in.nextInt();
                    brd = new TicTacToeBoard(n, m, k);
                    break;
                }
                catch (Exception e){
                    in.nextLine();
                }
            }
        }
        System.out.println("Tour?");
        String str = in.nextLine();
        while (str.length() == 0) {
            str = in.nextLine();
        }
        if (str.equals("1")) {
            System.out.println("Count of player's:");
            int round = in.nextInt();
            new Tournament(round, brd, n, m);
        }
        else {
            final int result = new TwoPlayerGame(
                    brd,
                    new RandomPlayer(n, m),
                    new RandomPlayer(n, m),
                    //new SequentialPlayer(n, m),
                    //new HumanPlayer(new Scanner(System.in)),
                    //new HumanPlayer(new Scanner(System.in)),
                    1, 2).play(true);
            switch (result) {
                case 1:
                    System.out.println("1 player won");
                    break;
                case 2:
                    System.out.println("2 player won");
                    break;
                case 0:
                    System.out.println("Draw");
                    break;
                case -2:
                    System.out.println("Error print again");
                default:
                    throw new AssertionError("Unknown result " + result);
            }
        }
    }
}
