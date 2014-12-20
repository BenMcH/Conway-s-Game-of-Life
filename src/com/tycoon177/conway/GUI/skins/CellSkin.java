package com.tycoon177.conway.GUI.skins;

import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.tycoon177.conway.GUI.Cell;

public class CellSkin implements Skin<Cell> {
	private Cell skinnable;
	private StackPane root;
	private Rectangle r;
	private long creationTime;

	public CellSkin(Cell c) {
		skinnable = c;
		root = new StackPane();
		r = new Rectangle();
		r.setFill(Color.BLACK);
		root.getChildren().add(r);
		creationTime = System.nanoTime();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getNode() {
		double width = skinnable.getWidth();
		double height = skinnable.getHeight();
		r.setWidth(width);
		r.setHeight(height);
		r.setArcHeight(2);
		r.setArcWidth(2);
		return root;
	}

	@Override
	public  Cell getSkinnable() {
		return skinnable;
	}

	@Override
	public String toString() {
		return "Cell - Created at: " + creationTime;
	}

}
