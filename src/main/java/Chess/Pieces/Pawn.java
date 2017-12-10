package Chess.Pieces;

import Chess.ChessBoard;

import java.util.Vector;

public class Pawn extends ChessPiece {

	public Pawn(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>(4);
	}

	private void CheckPlacesToKill(int i, int i1) {
		int newX = xOnBoard + i;
		int newY = yOnBoard + i1;
		if (newY < 8 && newY >= 0 && newX < 8 && newX >= 0) {
			ChessPiece piece = ChessBoard.boxes[ChessBoard.getIdOnBoard(newX, newY)].getPiece();
			if (piece != null) {
				if ((piece.isWhite && !this.isWhite) || (!piece.isWhite && this.isWhite)) {
					placesToCheck.add(ChessBoard.getIdOnBoard(newX, newY));
				}
			}
		}
	}


	@Override
	public void refresh() {
		super.refresh();
		if (isWhite) {
			if (ChessBoard.boxes[ChessBoard.getIdOnBoard(xOnBoard, yOnBoard - 1)].getPiece() == null) {
				addToPlacesToCheck(0, -1);
				if (yOnBoard == 6 && ChessBoard.boxes[ChessBoard.getIdOnBoard(xOnBoard, yOnBoard - 2)].getPiece() == null)
					addToPlacesToCheck(0, -2);
			}
			CheckPlacesToKill(1, -1);
			CheckPlacesToKill(-1, -1);


		} else {
			if (ChessBoard.boxes[ChessBoard.getIdOnBoard(xOnBoard, yOnBoard + 1)].getPiece() == null) {
				addToPlacesToCheck(0, 1);
				if (yOnBoard == 1 && ChessBoard.boxes[ChessBoard.getIdOnBoard(xOnBoard, yOnBoard + 2)].getPiece() == null)
					addToPlacesToCheck(0, +2);
			}
			CheckPlacesToKill(1, 1);
			CheckPlacesToKill(-1, 1);
		}
	}
}