package domain;

public interface Piece {
    boolean isValidMove(int[] start, int[] end);
    Colour getColour();
}
