package Chess.Pieces;

import java.util.Vector;

public class Knight extends ChessPiece {
	public Knight(boolean isWhite) {
		super(isWhite);
		placesToCheck = new Vector<>(8);
	}

	@Override
	public void refresh() {
		super.refresh();
		addToPlacesToCheck(1,2);
		addToPlacesToCheck(-1,2);
		addToPlacesToCheck(-1,-2);
		addToPlacesToCheck(1,-2);
		addToPlacesToCheck(2,1);
		addToPlacesToCheck(2,-1);
		addToPlacesToCheck(-2,-1);
		addToPlacesToCheck(-2, 1);
	}
}
