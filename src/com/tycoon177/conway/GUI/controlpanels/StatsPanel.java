package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class StatsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4687534417519357346L;
	private JLabel generation, cellsAlive;

	public StatsPanel() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		generation = new JLabel("0");
		cellsAlive = new JLabel("0");
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		add(new JLabel("Generation: "), c);
		c.gridx = 1;
		add(generation, c);
		c.gridy = 1;
		c.gridx = 0;
		add(new JLabel("Alive Cells: "), c);
		c.gridx = 1;;
		add(cellsAlive, c);
		setBorder(new TitledBorder(new LineBorder(Color.black), "Stats"));
	}

	public void setGeneration(int x) {
		generation.setText("" + x);
	}

	public void setCellsAlive(int x) {
		cellsAlive.setText("" + x);
	}

}
