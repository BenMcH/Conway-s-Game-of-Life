package com.tycoon177.conway.GUI;

import javafx.scene.control.Control;
import javafx.scene.control.Skinnable;

import com.tycoon177.conway.GUI.skins.CellSkin;

public class Cell extends Control implements Skinnable {

	private boolean selected;
	private boolean highlighted;
	private boolean visited;

	public Cell(){
		super();
		setSkin(new CellSkin(this));
		setMaxHeight(Double.MAX_VALUE);
		setMaxWidth(Double.MAX_VALUE);
	}
	
	public boolean hasBeenVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public boolean isAlive() {
		return selected;
	}

	public void setAlive(boolean selected) {
		this.selected = selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

}
