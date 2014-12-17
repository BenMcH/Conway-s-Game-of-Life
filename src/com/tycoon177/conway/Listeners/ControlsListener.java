package com.tycoon177.conway.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.Controls;

public class ControlsListener implements ActionListener {
	private Controls controls;
	private Timer timer;

	public ControlsListener(Controls c) {
		this.controls = c;
		timer = new Timer(1000 / 20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.step();
				ConwayGUI.game.repaint();
			}
		});
	}

	@SuppressWarnings("unused")
	private ControlsListener() {
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
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
				Controls.slider.setFPSDisabled(true);
			} else {
				timer.start();
				ConwayGUI.gui
						.setTitle("Conways Game of Life, made by tycoon177 - Running");
				Controls.slider.setFPSDisabled(false);
			}
			break;
		case "clear":
			ConwayGUI.game.clearBoard();
			ConwayGUI.game.updateStats();
			break;
		case "random":
			ConwayGUI.game.randomizeBoard();
			ConwayGUI.game.repaint();
		}
	}

	public void setMaxUpdates(int updates) {

		timer.setDelay(1000 / updates);

	}
	
	public void runTimer(boolean run){
		if(timer.isRunning()){
			if(!run) timer.stop();
		}else
			if(run) timer.start();
		
	}

}
