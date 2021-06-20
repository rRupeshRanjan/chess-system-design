package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bishop implements Piece {
    private Colour colour;

    @Override
    public boolean isValidMove(int[] start, int[] end) {
        int xMovement = Math.abs(end[0] - start[0]);
        int yMovement = Math.abs(end[1] - start[1]);

        return xMovement == yMovement;
    }
}
