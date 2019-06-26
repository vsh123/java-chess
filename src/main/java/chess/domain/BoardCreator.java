package chess.domain;

import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardCreator {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 8;

    private BoardCreator() {

    }

    public static Board create() {
        Map<Point, Piece> board = new HashMap<>();
        IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
                .forEach(i ->
                        IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
                                .forEach(j -> board.put(Point.get(i, j), new Empty())));
        putWhitePiece(board);
        putBlackPiece(board);
        return new Board(board);
    }

    private static void putWhitePiece(Map<Point, Piece> board) {
        ChessTeam team = ChessTeam.WHITE;
        putPawn(board, team, 2);
        putPieces(board, team, 1);
    }

    private static void putBlackPiece(Map<Point, Piece> board) {
        ChessTeam team = ChessTeam.BLACK;
        putPawn(board, team, 7);
        putPieces(board, team, 8);
    }

    private static void putPawn(Map<Point, Piece> board, ChessTeam team, int position) {
        for (int i = MIN_RANGE; i <= MAX_RANGE; i++) {
            board.put(Point.get(i, position), new Pawn(team));
        }
    }

    private static void putPieces(Map<Point, Piece> board, ChessTeam team, int position) {
        board.put(Point.get(1, position), new Rook(team));
        board.put(Point.get(2, position), new Knight(team));
        board.put(Point.get(3, position), new Bishop(team));
        board.put(Point.get(4, position), new Queen(team));
        board.put(Point.get(5, position), new King(team));
        board.put(Point.get(6, position), new Bishop(team));
        board.put(Point.get(7, position), new Knight(team));
        board.put(Point.get(8, position), new Rook(team));
    }
}
