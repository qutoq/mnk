package game;

import java.util.Arrays;
import java.util.Map;


public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;
    public int n, m, k;

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public void clr(){
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.ERROR;
        }
        else {
            field[move.getRow()][move.getCol()] = move.getValue();
            if (checkWin(move)) {
                return GameResult.WIN;
            }

            if (checkDraw()) {
                return GameResult.DRAW;
            }

            turn = turn == Cell.X ? Cell.O : Cell.X;
            return GameResult.UNKNOWN;
        }
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < this.n; r++) {
            for (int c = 0; c < this.m; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWin(Move move) {
        //row
        int cnt = 1;
        for (int c = 1; c < this.m - move.getCol(); c++) {
            if (field[move.getRow()][move.getCol() + c] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        for (int c = 1; c <= move.getCol(); c++) {
            if (field[move.getRow()][move.getCol() - c] != move.getValue()) {
                break;
            }
            cnt++;
        }
        if (cnt >= this.k) {
            return true;
        }
        //col
        cnt = 1;
        for (int r = 1; r < this.n - move.getRow(); r++) {
            if (field[move.getRow() + r][move.getCol()] != move.getValue()) {
                break;
            }
            cnt++;
        }
        for (int r = 1; r <= move.getRow(); r++) {
            if (field[move.getRow() - r][move.getCol()] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        if (cnt >= this.k) {
            return true;
        }
        //rdiag
        cnt = 1;
        for (int i = 1; i < Math.min(move.getRow() + 1, m - move.getCol()); i++) {
            if (field[move.getRow() - i][move.getCol() + i] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        for (int i = 1; i < Math.min(n - move.getRow(), move.getCol() + 1); i++) {
            if (field[move.getRow() + i][move.getCol() - i] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        if (cnt >= this.k) {
            return true;
        }
        //ldiag
        cnt = 1;
        for (int i = 1; i < Math.min(move.getRow() + 1, move.getCol() + 1); i++) {
            if (field[move.getRow() - i][move.getCol() - i] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        for (int i = 1; i < Math.min(n - move.getRow(), m - move.getCol()); i++) {
            if (field[move.getRow() + i][move.getCol() + i] != move.getValue()) {
                break;
            }
            cnt += 1;
        }
        if (cnt >= this.k) {
            return true;
        }

        return false;
    }


    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < this.n
                && 0 <= move.getCol() && move.getCol() < this.m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("   ");
        for (int c = 0; c < this.m; c++) {
            sb.append(c + 1);
            if (c + 1 < 10) { sb.append(" ");}
            sb.append(" ");
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < this.n; r++) {
            sb.append(r + 1);
            sb.append(" ");
            if (r + 1 < 10) { sb.append(" ");}
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
                sb.append("  ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
