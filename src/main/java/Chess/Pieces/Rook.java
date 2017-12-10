package Chess.Pieces;

import java.util.Vector;

public class Rook extends ChessPiece {
	public Rook(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>();
	}

	@Override
	public void refresh() {
		super.refresh();
		addHorizontallyAndVertically();
	}
}
