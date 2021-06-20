package domain;

import lombok.Data;

@Data
public class Board {
    private final Piece[][] boardStatus;
    private Colour currentTurnColour;

    private Board() {
        boardStatus = new Piece[8][8];
        populatePieces(Colour.WHITE);
        populatePieces(Colour.BLACK);
        currentTurnColour = Colour.WHITE;
    }

    public static Board getInstance() {
        return BoardInitializer.INSTANCE;
    }

    public boolean updateBoard(int[] startPos, int[] endPos) {
        if (isValidBoardMove(startPos, endPos)) {
            Piece piece = getPieceAtPosition(startPos);
            if (piece.isValidMove(startPos, endPos)) {
                boardStatus[endPos[0]][endPos[1]] = piece;
                boardStatus[startPos[0]][startPos[1]] = null;
                flipCurrentTurnColour();
                return true;
            }
        }

        return false;
    }

    private void flipCurrentTurnColour() {
        if (currentTurnColour.equals(Colour.WHITE))
            currentTurnColour = Colour.BLACK;
        else
            currentTurnColour = Colour.WHITE;
    }

    protected Piece getPieceAtPosition(int[] pos) {
        return boardStatus[pos[0]][pos[1]];
    }

    private boolean isValidBoardMove(int[] start, int[] end) {
        if (start[0] < 0 || start[0] >= 8 || end[1] < 0 || end[1] >= 8 || (end[0] == start[0] && end[1] == start[1]))
            return false;

        Piece pieceAtStartPos = getPieceAtPosition(start);
        if (pieceAtStartPos == null || !pieceAtStartPos.getColour().equals(currentTurnColour))
            return false;

        Piece pieceAtEndPos = getPieceAtPosition(end);
        return pieceAtEndPos == null || !pieceAtEndPos.getColour().equals(pieceAtStartPos.getColour());
    }

    private void populatePieces(Colour colour) {
        int pawnRow = 1, otherRow = 0;
        if (colour.equals(Colour.BLACK)) {
            pawnRow = 6;
            otherRow = 7;
        }

        boardStatus[otherRow][0] = new Rook(colour);
        boardStatus[otherRow][1] = new Knight(colour);
        boardStatus[otherRow][2] = new Bishop(colour);
        boardStatus[otherRow][3] = new Queen(colour);
        boardStatus[otherRow][4] = new King(colour);
        boardStatus[otherRow][5] = new Bishop(colour);
        boardStatus[otherRow][6] = new Knight(colour);
        boardStatus[otherRow][7] = new Rook(colour);

        for (int i = 0; i < 8; i++) {
            boardStatus[pawnRow][i] = new Pawn(colour);
        }
    }

    private static class BoardInitializer {
        private static final Board INSTANCE = new Board();
    }
}
