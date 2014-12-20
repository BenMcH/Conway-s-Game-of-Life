package com.tycoon177.conway.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.JScrollPane;

import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.GUI.controlpanels.PaintBrushEditor;

public class ConwayGUI extends Application {
	public static ConwayGUI gui;
	public static ConwaysGame game;
	public static Controls controls;
	JScrollPane pane;

	public static void main(String[] args) {
		launch(args);
	}

	public void setNewBoard(int width, int height) {
		game.reinit(width, height);
		//game.repaint();
	}

	@Override
	public void start(Stage stage) throws Exception {
		new PaintBrushEditor();
		controls = new Controls();
		game = new ConwaysGame(100);
		BorderPane root = new BorderPane();
		game.drawCells();
		game.setPos(controls.getHeight());
		ScrollPane gamePane = new ScrollPane(game);
		root.setTop(controls);
		root.setCenter(gamePane);
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
		
	}

	public static int getPos() {
		return (int)controls.getHeight();
	}
	

}
