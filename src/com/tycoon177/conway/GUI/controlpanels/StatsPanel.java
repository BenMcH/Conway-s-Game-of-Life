package com.tycoon177.conway.GUI.controlpanels;

import java.awt.GridBagConstraints;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StatsPanel extends GridPane {

	private Text generation, cellsAlive, cellsDead;

	public StatsPanel() {
		super();
		GridBagConstraints c = new GridBagConstraints();
		generation = new Text("0");
		cellsAlive = new Text("0");
		cellsDead = new Text("0");
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new Text("Generation: "), 0, 0);
		c.gridx = 1;
		add(generation, 1, 0);
		c.gridy = 1;
		c.gridx = 0;
		add(new Text("Alive Cells: "), 0, 1);
		c.gridx = 1;
		add(cellsAlive, 1, 1);
		c.gridy = 2;
		c.gridx = 0;
		add(new Text("Dead Cells: "), 0, 2);
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(cellsDead, 1, 2);
		// setBorder(new TitledBorder(new LineBorder(Color.black), "Stats"));

	}

	public void setGeneration(int x) {
		generation.setText("" + x);
	}

	public void setCellsAlive(int x) {
		cellsAlive.setText("" + x);
	}

	public void setCellsDead(long l) {
		cellsDead.setText("" + l);
	}

}
