package com.tycoon177.conway.listeners;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.Controls;

public class ControlsListener extends AnimationTimer implements
		EventHandler<ActionEvent> {
	private boolean running = false;
	private long delay = 1000 / 20, lastUpdate = System.currentTimeMillis();

	public ControlsListener(Controls c) {
	}

	@SuppressWarnings("unused")
	private ControlsListener() {
	}

	public void setMaxUpdates(int updates) {

		delay = (1000 / updates);

	}

	@Override
	public void handle(ActionEvent arg0) {
		Button b = (Button) arg0.getSource();
		switch (b.getText().toLowerCase()) {
		case "step":
			ConwayGUI.game.step();
			// controls.revalidate();
			break;
		case "toggle run":
			if (isRunning()) {
				stop();
				running = false;
			} else {
				start();
				running = true;
			}
			break;
		case "clear":
			ConwayGUI.game.clearBoard();
			ConwayGUI.game.updateStats();
			break;
		case "random":
			ConwayGUI.game.randomizeBoard();
			// ConwayGUI.game.repaint();
		}
	}

	private boolean isRunning() {
		return running;
	}

	@Override
	public void handle(long arg0) {
		if (System.currentTimeMillis() - lastUpdate > delay) {
			ConwayGUI.game.step();
			lastUpdate = System.currentTimeMillis();
		}
	}
}
