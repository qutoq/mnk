package game;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final int no1;
    private final int no2;

    public TwoPlayerGame(Board board, Player player1, Player player2, int no1, int no2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.no1 = no1;
        this.no2 = no2;
    }

    public int play(boolean log) {
        while (true) {
            int result1 = -2, result2 = -2;
            while (result1 == -2) {
                result1 = makeMove(player1, no1, log);
            }
            if (result1 != -1) {
                return result1;
            }
            while (result2 == -2) {
                result2 = makeMove(player2, no2, log);
            }
            if (result2 != -1){
                return result2;
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case LOOSE:
                return -4;
            case DRAW:
                return -3;
            case UNKNOWN:
                return -1;
            case ERROR:
                return -2;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
