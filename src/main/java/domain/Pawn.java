package domain;

import lombok.Data;

@Data
public class Pawn implements Piece {
    private Colour colour;
    private boolean isFirstMove;

    protected Pawn(Colour colour) {
        this.colour = colour;
        this.isFirstMove = true;
    }

    @Override
    public boolean isValidMove(int[] start, int[] end) {
        Board board = Board.getInstance();

        int xMovement = end[0] - start[0];
        int yMovement = end[1] - start[1];
        boolean result = false;

        if (xMovement == 1) {
            result = (board.getPieceAtPosition(end) == null) ? (yMovement == 0) : (yMovement == 1);
            if (isFirstMove && result)
                isFirstMove = false;
        } else if (xMovement == 2 && isFirstMove) {
            result = (yMovement == 0);
            if(result)
                isFirstMove = false;
        }

        return result;
    }

}
