package com.tycoon177.conway.GUI.controlpanels;

import java.awt.GridBagConstraints;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardControls extends GridPane {

	public BoardControls() {
		super();
		GridBagConstraints c = new GridBagConstraints();
		// Continuously Step
		Button run = new Button("Toggle Run");
		Button clear = new Button("Clear");
		Button step = new Button("Step");
		Button random = new Button("Random");
		run.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		clear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		step.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		random.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		run.setOnAction(Controls.listen);
		clear.setOnAction(Controls.listen);
		random.setOnAction(Controls.listen);
		step.setOnAction(Controls.listen);
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = .01;
		c.weighty = .01;

		c.fill = GridBagConstraints.HORIZONTAL;
		add(step, 0, 0);
		add(run, 0, 1);
		add(clear, 1, 0);
		add(random, 1, 1);

	}

}
