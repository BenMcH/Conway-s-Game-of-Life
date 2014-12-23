package com.tycoon177.conway.GUI.panels;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import com.tycoon177.conway.GUI.ConwayGUI;

public class AttributesPreferences extends GridPane {

	public AttributesPreferences() {
		super();
		Text title = new Text("Rule Changer");
		Text stay = new Text("Number of neighbors that keep cells alive:");
		Text come = new Text("Number of neighbors that bring the cell to life:");
		TextField stayAlive = new TextField("2, 3");
		TextField comeAlive = new TextField("3");
		// comeAlive.setAutoscrolls(true);
		Button set = new Button("Set");
		// pane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(title, 0, 0, 2, 1);
		add(stay, 0, 1);
		add(stayAlive, 1, 1);
		add(come, 0, 2);
		add(comeAlive, 1, 2);
		add(set, 0, 3, 1, 1);
		set.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConwayGUI.game.setRules(stayAlive.getText(),
						comeAlive.getText());
			}
		});
	}
}
