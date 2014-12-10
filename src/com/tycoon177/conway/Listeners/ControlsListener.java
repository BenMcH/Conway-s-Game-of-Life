package com.tycoon177.conway.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.Controls;

public class ControlsListener implements ActionListener {
	Controls controls;
	Timer timer;
	
	public ControlsListener(Controls c) {
		this.controls = c;
		timer = new Timer(1000/20, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.step();
				controls.repaint();
				controls.revalidate();
			}
		});
	}
	
	@SuppressWarnings("unused")
	private ControlsListener() {}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
		case "step":
			ConwayGUI.game.step();
			controls.repaint();
			controls.revalidate();
			break;
		case "run":
			if (timer.isRunning()) {
				timer.stop();
				ConwayGUI.gui
						.setTitle("Conways Game of Life, made by tycoon177 - Stopped");
			} else {
				timer.start();
				ConwayGUI.gui
						.setTitle("Conways Game of Life, made by tycoon177 - Running");
			}
			break;
		case "clear":
			ConwayGUI.game.clearBoard();
			break;
		case "random":
			ConwayGUI.game.randomizeBoard();
			ConwayGUI.game.repaint();
		}
	}
	
	public void setMaxUpdates(int updates) {

		timer.setDelay(1000/updates);
	
	}

}
