package com.tycoon177.conway.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dialog extends Stage {

	public static void showDialog(String message, String title) {
		Dialog d = new Dialog();

		Text text = new Text(message);
		
		BorderPane p = new BorderPane();
		p.setPadding(new Insets(5));
		Scene s = new Scene(p);
		p.setCenter(text);
		p.setBottom(getButton(d));
		d.setScene(s);
		d.setTitle(title);
		d.show();
	}

	private static BorderPane getButton(Stage stage) {
		BorderPane p = new BorderPane();
		Button b = new Button("Ok");

		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
		});
		p.setCenter(b);
		p.setPadding(new Insets(2));
		return p;
	}

	private Dialog() {
		super();
	}

}
