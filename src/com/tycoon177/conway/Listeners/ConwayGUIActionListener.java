package com.tycoon177.conway.listeners;

import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import com.tycoon177.conway.GUI.GridSizeChanger;
import com.tycoon177.conway.GUI.PreferencesWindow;

public class ConwayGUIActionListener implements EventHandler<javafx.event.ActionEvent> {

	@Override
	public void handle(javafx.event.ActionEvent event) {
		String cas = (((MenuItem)event.getSource()).getText());
		
		switch (cas) {
		case ("Change Board Sizee"):
			GridSizeChanger.showGridSizeChanger();
			break;
		case ("Preferences"):
			PreferencesWindow.showPreferences();
			break;
		}
	}

}
