package Chess.Pieces;

import Chess.ChessBoard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Vector;

import static Chess.ChessBoard.boxes;
import static Chess.ChessBoard.getIdOnBoard;

public abstract class ChessPiece extends Group {

	boolean isWhite;
	protected int current;  //where it is placed now
	int xOnBoard;
	int yOnBoard;
	Vector<Integer> placesToCheck;
	private int[] pos;

	{
		pos = new int[2];
	}

	ChessPiece(boolean isWhite) {
		this.isWhite = isWhite;
		try {
			Node graphic;
			if (isWhite)
				graphic = FXMLLoader.load(getClass().getResource("/Chess/pieces/" + getClass().getSimpleName().toLowerCase() + "/" + getClass().getSimpleName().toLowerCase() + "-white.fxml"));
			else
				graphic = FXMLLoader.load(getClass().getResource("/Chess/pieces/" + getClass().getSimpleName().toLowerCase() + "/" + getClass().getSimpleName().toLowerCase() + "-black.fxml"));
			getChildren().add(graphic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void refresh() {
		placesToCheck.clear();
	}

	private boolean checkValidation(int id) {
		if (id > 63 || id < 0) return false;
		ChessPiece piece = boxes[id].getPiece();
		return piece == null || (!piece.isWhite() || !this.isWhite) && (piece.isWhite() || this.isWhite);
	}

	public Vector<Integer> placesToMove() {
		Vector<Integer> result = new Vector<>();
		for (int id : placesToCheck) {
			if (this.checkValidation(id)) {
				result.add(id);
			}
		}
		return result;
	}

	void addToPlacesToCheck(int otherXAddition, int otherYAddition) {
		int newX = xOnBoard + otherXAddition;
		int newY = yOnBoard + otherYAddition;
		if (newY < 8 && newY >= 0 && newX < 8 && newX >= 0)
			placesToCheck.add(ChessBoard.getIdOnBoard(newX, newY));
	}

	public void moveTo(double otherX, double otherY) {
		setLayoutX(otherX);
		setLayoutY(otherY);
	}

	//Getters and setters start::***************************************************
	public boolean isWhite() {
		return isWhite;
	}

	public void setX(int x) {
		this.setLayoutX(x);
	}

	public void setY(int y) {
		this.setLayoutY(y);
	}

	public void setPos(int x, int y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}

	public void setCurrent(int current) {
		this.current = current;
		this.xOnBoard = current % 8;
		this.yOnBoard = current / 8;
	}

//	public int getxOnBoard() {
//		return xOnBoard;
//	}
//
//	public int getyOnBoard() {
//		return yOnBoard;
//	}

	public int getCurrent() {
		return current;
	}

	//Getters and setters end::*******************************************************

	//for Bishop, Rook and Queen start::**********************************************
	private void resetPos() {
		pos[0] = xOnBoard;
		pos[1] = yOnBoard;
	}

	private boolean add() {
		try {
			if (boxes[getIdOnBoard(pos[0], pos[1])].getPiece() != null) {
				placesToCheck.add(getIdOnBoard(pos[0], pos[1]));
				return true;
			} else {
				placesToCheck.add(getIdOnBoard(pos[0], pos[1]));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		return false;
	}

	void addDiagonally() {

		resetPos();
		while (++pos[0] < 8 && ++pos[1] < 8) if (add()) break;

		resetPos();
		while (++pos[0] < 8 && --pos[1] >= 0) if (add()) break;

		resetPos();
		while (--pos[0] >= 0 && --pos[1] >= 0) if (add()) break;

		resetPos();
		while (--pos[0] >= 0 && ++pos[1] < 8) if (add()) break;
	}

	void addHorizontallyAndVertically() {

		resetPos();
		while (++pos[0] < 8) if (add()) break;

		resetPos();
		while (--pos[0] >= 0) if (add()) break;

		resetPos();
		while (++pos[1] < 8) if (add()) break;

		resetPos();
		while (--pos[1] >= 0) if (add()) break;

	}
	//for Bishop, Rook and Queen end::********************************************************
}
