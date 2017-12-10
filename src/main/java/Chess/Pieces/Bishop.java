package Chess.Pieces;

import java.util.Vector;

public class Bishop extends ChessPiece {
	public Bishop(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>();
	}

	@Override
	public void refresh() {
		super.refresh();
		addDiagonally();
	}
}
