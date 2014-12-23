package com.tycoon177.conway.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.tycoon177.conway.utils.Dialog;
import com.tycoon177.conway.utils.Settings;

public class GridSizeChanger {

	public static void showGridSizeChanger() {
		System.out.println("Here");
		Stage frame = new Stage();
		GridPane pane = new GridPane();
		pane.add(new Label("Width:"), 0, 0);
		TextField width = new TextField(Settings.GRID_WIDTH + "");
		pane.add(width, 1, 0);
		pane.add(new Label("Height:"), 0, 1);
		TextField height = new TextField(Settings.GRID_HEIGHT + "");
		pane.add(height, 1, 1);
		Button cancel = new Button("Cancel");
		pane.add(cancel, 0, 2);
		Button set = new Button("Ok");
		pane.add(set, 1, 2);
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				frame.close();
			}
		});

		set.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int w = Integer.parseInt(width.getText());
					int h = Integer.parseInt(height.getText());
					ConwayGUI.gui.setNewBoard(w, h);
					frame.close();
				} catch (NumberFormatException e) {
					Dialog.showDialog(
							"There was an error parsing your numbers. Make sure they are whole numbers",
							"Error!");
					return;
				}
			}
		});
		Scene scene = new Scene(pane);
		frame.setScene(scene);
		frame.show();
	}
}
