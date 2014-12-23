package com.tycoon177.conway.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.JScrollPane;

import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.utils.Settings;

public class ConwayGUI extends Application {
	public static ConwayGUI gui;
	public static ConwaysGame game;
	public static Controls controls;
	JScrollPane pane;
	static ConwayMenuBar menuBar;
	static ScrollPane gamePane;
	public static void main(String[] args) {
		launch(args);
	}

	public void setNewBoard(int width, int height) {
		game.reinit(height, width);
		Settings.CELL_SIZE++;
		Settings.CELL_SIZE--;
		game.drawCells();
		// game.repaint();
	}

	@Override
	public void start(Stage stage) throws Exception {
		gui = new ConwayGUI();
		menuBar = new ConwayMenuBar();
		controls = new Controls();
		game = new ConwaysGame(100);
		BorderPane root = new BorderPane();
		game.drawCells();
		gamePane = new ScrollPane(game);
		//game.setPos(controls.getHeight());
		root.setTop(controls);
		root.setCenter(gamePane);
		VBox box = new VBox();
		box.getChildren().addAll(menuBar, root);
		Scene scene = new Scene(box);

		stage.setScene(scene);
		stage.show();

	}
}
