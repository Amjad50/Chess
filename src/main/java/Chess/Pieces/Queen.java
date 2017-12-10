package Chess.Pieces;

import java.util.Vector;

public class Queen extends ChessPiece {
	public Queen(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>();
	}

	@Override
	public void refresh() {
		super.refresh();
		addDiagonally();
		addHorizontallyAndVertically();
	}
}
