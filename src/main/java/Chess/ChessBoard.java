package Chess;

import Chess.Pieces.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Objects;

public class ChessBoard {
	private ChessPiece startingGrid[] = {
			new Rook(false), new Knight(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Knight(false), new Rook(false),
			new Pawn(false), new Pawn(false),   new Pawn(false),   new Pawn(false),  new Pawn(false), new Pawn(false),   new Pawn(false),   new Pawn(false),
			null,            null,              null,              null,             null,            null,              null,              null,
			null,            null,              null,              null,             null,            null,              null,              null,
			null,            null,              null,              null,             null,            null,              null,              null,
			null,            null,              null,              null,             null,            null,              null,              null,
			new Pawn(true),  new Pawn(true),    new Pawn(true),    new Pawn(true),   new Pawn(true),  new Pawn(true),    new Pawn(true),    new Pawn(true),
			new Rook(true),  new Knight(true),  new Bishop(true),  new Queen(true),  new King(true),  new Bishop(true),  new Knight(true),  new Rook(true)
	};

	private ChessController controller;

	//static variable start:: ******************************************
	static boolean isAnyBoxSelected;
	public static ChessBox[] boxes;
	static boolean isWhitePlaying;
	static ChessBox playingBox;
	//static variable end:: ******************************************


	{
		fillGrid();
		setUpStartingGrid();
		addPiecesToBoxes();
	}

	ChessBoard(ChessController controller) {
		this.controller = controller;
	}

	private void addPiecesToBoxes() {
		for (ChessPiece piece : startingGrid) {
			boxes[piece.getCurrent()].setPiece(piece);
		}
	}

	private void setUpStartingGrid() {
		for (int i = 0; i < startingGrid.length; i++) {
			if (startingGrid[i] != null) {
				startingGrid[i].setPos(getXonBoard(i), getYonBoard(i));
				startingGrid[i].setCurrent(i);
			}
		}
		startingGrid = Arrays.stream(startingGrid).filter(Objects::nonNull).toArray(ChessPiece[]::new);
	}
	//static initializer and methods start ::
	static {
		isAnyBoxSelected = false;
		isWhitePlaying = true;
		playingBox = null;
	}


	void delete(Node node){
		controller.getPiecesPlaceholder().getChildren().remove(node);

	}

	private void fillGrid() {
		boxes = new ChessBox[64];
		for (int i = 0; i < 64; i++) {
			boxes[i] = new ChessBox(100, 100, Color.TRANSPARENT, this);
		}
	}

	static void cleanSelectedBoxes(){
		for (ChessBox box:boxes){
			if (box.isSelected()){
				box.deselect();
			}
		}
		isAnyBoxSelected = false;
	}

	void move(int from, int to){
		System.err.println("moving from  " + from + " to  " + to);
		ChessPiece piece = boxes[to].getPiece();
		ChessBox playingBoxh = boxes[from];
		if (piece != null) {
			if (piece instanceof King) {
				disableBoard();
			}
			System.out.println(piece + " down!");
			delete(piece);
		}
		playingBoxh.getPiece().moveTo(getXonBoard(to), getYonBoard(to));
		playingBoxh.getPiece().setCurrent(to);
		boxes[to].setPiece(playingBoxh.getPiece());
		playingBoxh.setPiece(null);
		playingBoxh = null;
		ChessBoard.cleanSelectedBoxes();
	}


	void disableBoard() {
		FlowPane p = new FlowPane();
		Text l = new Text("YOU WIN!!!!");
		p.setPrefSize(800, 800);
		p.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
		l.setTextAlignment(TextAlignment.CENTER);
		l.setFont(Font.font("Tempus Sans ITC", FontWeight.BOLD, 40));
		System.out.println(Font.getFamilies());
		l.resize(200,200);
		p.setAlignment(Pos.CENTER);
		p.getChildren().add(l);
		for (ChessBox box : boxes) box.setOnMouseClicked(null);
		controller.getRoot().getChildren().add(p);
	}
	//static initializer and methods end ::

	//Getters and setters start::
	ChessPiece[] getStartingGrid() {
		return startingGrid;
	}

	private static int getXonBoard(int id) {
		return (id % 8) * 100;
	}

	private static int getYonBoard(int id) {
		return (id / 8) * 100;
	}

	public static int getIdOnBoard(int x, int y) {
		return (y * 8) + x;
	}

	public ChessController getController() {
		return controller;
	}
	//Getters and setters end::
}

