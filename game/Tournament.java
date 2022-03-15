package game;

import java.util.Scanner;

public class Tournament {
    public Tournament(int round, Board brd, int n, int m) {
        final Scanner in = new Scanner(System.in);
        int[] res_count;
        res_count = new int[round];
        int[] pl;
        pl = new int[100];
        pl[1] = 1;

        for(int i = 0; i < round; i ++) {
            for (int j = 0; j < round; j++) {
                if (i != j) {
                    final StringBuilder sb = new StringBuilder("Match of ");
                    sb.append(i + 1);
                    sb.append(" and ");
                    sb.append(j + 1);
                    System.out.println(sb);
                    brd.clr();
                    Player pl1 = new HumanPlayer(new Scanner(System.in)), pl2 = new HumanPlayer(new Scanner(System.in));
                    if (pl[i] == 1) {
                        pl1 = new RandomPlayer(n, m);
                    }
                    if (pl[j] == 1) {
                        pl2 = new RandomPlayer(n, m);
                    }
                    final int result = new TwoPlayerGame(
                            brd,
                            pl1,
                            pl2,
                            i + 1, j + 1).play(true);

                    switch (result) {

                    case -3:
                        System.out.println("Draw");
                        res_count[i] += 1;
                        res_count[j] += 1;
                        continue;
                    default:
                        if ((result - 1 == i) || (result - 1 == j)) {
                            System.out.print(result);
                            System.out.println(" player won");
                            res_count[result - 1] += 3;
                        }
                        else {
                            System.out.println("wtf");
                        }
                    }
                }
            }
        }
        for (int i = 0; i < round; i++) {
            System.out.print(res_count[i] + " ");
        }
    }
}
