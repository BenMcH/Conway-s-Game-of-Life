package com.tycoon177.conway.GUI.controlpanels;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class Tools extends VBox {

	private RadioButton draw, select, paste;
	public static final int DRAW = 0, SELECT = 1, PASTE = 2;
	ToggleGroup group;

	public Tools() {
		super();
		group = new ToggleGroup();
		draw = new RadioButton("Draw");
		// draw.setActionCommand("draw");
		select = new RadioButton("Select");
		// select.setActionCommand("select");
		paste = new RadioButton("Paste");
		// paste.setActionCommand("paste");
		// setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Tools"));
		draw.setSelected(true);
		draw.setToggleGroup(group);
		select.setToggleGroup(group);
		paste.setToggleGroup(group);
		getChildren().add(draw);
		getChildren().add(select);
		getChildren().add(paste);
	}

	public String getTool() {
		RadioButton b = (RadioButton) group.getSelectedToggle();
		return b.getText();
	}

}
