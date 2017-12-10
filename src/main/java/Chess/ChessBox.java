package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.King;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static Chess.ChessBoard.*;

public class ChessBox extends Rectangle {

	private static int globalId = 0;
	private int localId;
	private ChessPiece piece;
	private boolean selected;
	private ChessBoard board;

	public ChessBox() {
		localId = globalId++;
		init();
	}

	public ChessBox(double width, double height, Paint fill, ChessBoard board) {
		super(width, height, fill);
		localId = globalId++;
		this.board = board;
		init();
	}


	private void init() {
		selected = false;
		setStrokeWidth(7.5);
		setStrokeType(StrokeType.INSIDE);

		this.setOnMouseClicked((MouseEvent event) -> {
			if (ChessController.isSinglePlayer) {
				singlePlayer();
				return;
			}
			multiPlayer(ChessController.isHost);
			System.err.println(ChessBoard.isWhitePlaying);
		});
	}

	private void multiPlayer(boolean isHost) {
		if (ChessController.canPlay) {
			if (ChessBoard.isAnyBoxSelected) {
				if (selected) {
					if (piece != null) {
						if (piece instanceof King) {
							board.disableBoard();
						}
						System.out.println(piece + " down!");
						board.delete(piece);
						piece = null;
					}
					playingBox.piece.moveTo(this.getLayoutX(), this.getLayoutY());
					playingBox.piece.setCurrent(this.localId);
					board.getController().send((playingBox.localId * 100) + this.localId);
					piece = playingBox.piece;
					boxes[playingBox.localId].piece = null;
					playingBox = null;
					ChessController.canPlay = false;
					board.getController().startReceivingNext();
					ChessBoard.cleanSelectedBoxes();
				}else{
					ChessBoard.cleanSelectedBoxes();
					cc();
				}
			} else {
				cc();
			}

		}
	}

	private void singlePlayer() {
		if (ChessBoard.isAnyBoxSelected) {
			if (selected) {
				if (piece != null) {
					if (piece instanceof King) {
						board.disableBoard();
					}
					System.out.println(piece + " down!");
					board.delete(piece);
					piece = null;
				}
				playingBox.piece.moveTo(this.getLayoutX(), this.getLayoutY());
				playingBox.piece.setCurrent(this.localId);
				piece = playingBox.piece;
				boxes[playingBox.localId].piece = null;
				playingBox = null;
				isWhitePlaying = !isWhitePlaying;
				ChessBoard.cleanSelectedBoxes();
			} else {
				ChessBoard.cleanSelectedBoxes();
				cc();
			}
		} else {
			cc();
		}
	}


	private void cc() {
		if (piece != null) {
			if (piece.isWhite() && ChessBoard.isWhitePlaying) {
				piece.refresh();
				for (int id : piece.placesToMove()) boxes[id].select();
				ChessBoard.isAnyBoxSelected = true;
				playingBox = this;
			} else if (!piece.isWhite() && !ChessBoard.isWhitePlaying) {
				piece.refresh();
				for (int id : piece.placesToMove()) boxes[id].select();
				ChessBoard.isAnyBoxSelected = true;
				playingBox = this;
			}
		}
	}

	private void select() {
		this.selected = true;
		setStroke(Color.valueOf("#7FFF00"));
	}

	void deselect() {
		this.selected = false;
		setStroke(Color.TRANSPARENT);
	}

	//Getters and setters start::
	boolean isSelected() {
		return this.selected;
	}

	public ChessPiece getPiece() {
		return piece;
	}

	void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
	//Getters and setters end::
}
