package com.tycoon177.conway.GUI.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.utils.Settings;

public class ColorPanel extends GridPane {

	ColorPicker backgroundColor = new ColorPicker(Settings.BACKGROUND_COLOR);
	ColorPicker cellColor = new ColorPicker(Settings.CELL_COLOR);
	ColorPicker gridColor = new ColorPicker(Settings.GRID_COLOR);

	public ColorPanel() {
		super();
		backgroundColor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Settings.BACKGROUND_COLOR = backgroundColor.getValue();
				ConwayGUI.game.drawCells();
			}
		});
		cellColor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Settings.CELL_COLOR = cellColor.getValue();
				ConwayGUI.game.drawCells();
			}
		});
		gridColor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Settings.GRID_COLOR = gridColor.getValue();
				ConwayGUI.game.drawCells();
			}
		});
		add(new Text("Background: "), 0, 0);
		add(backgroundColor, 1, 0);
		add(new Text("Cells: "), 0, 1);
		add(cellColor, 1, 1);
		add(new Text("Grid: "), 0, 2);
		add(gridColor, 1, 2);
	}
}
