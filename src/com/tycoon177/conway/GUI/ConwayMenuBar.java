package com.tycoon177.conway.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import com.tycoon177.conway.listeners.ConwayGUIActionListener;

public class ConwayMenuBar extends MenuBar {

	public ConwayMenuBar() {
		super();
		Menu edit = new Menu("Edit");
		ConwayGUIActionListener actionListener = new ConwayGUIActionListener();
		// add(edit);
		MenuItem changeSize = new MenuItem("Change Board Size");
		changeSize.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GridSizeChanger.showGridSizeChanger();
			}
		});
		// edit.add(changeSize);
		MenuItem preferences = new MenuItem("Preferences");
		preferences.setOnAction(actionListener);
		// edit.add(preferences);
		edit.getItems().addAll(changeSize, preferences);
		this.getMenus().add(edit);
	}

}
