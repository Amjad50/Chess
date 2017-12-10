package Chess.Pieces;

import java.util.Vector;

public class King extends ChessPiece {

	public King(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>(8);
	}


	@Override
	public void refresh() {
		super.refresh();
		addToPlacesToCheck(0, 1);
		addToPlacesToCheck(0, -1);
		addToPlacesToCheck(1, 0);
		addToPlacesToCheck(1, 1);
		addToPlacesToCheck(1, -1);
		addToPlacesToCheck(-1, 0);
		addToPlacesToCheck(-1, 1);
		addToPlacesToCheck(-1, -1);
	}
}
