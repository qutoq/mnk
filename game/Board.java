package game;

public interface Board {
    Position getPosition();
    void clr();
    GameResult makeMove(Move move);
}
