package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PaintBrushEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571809177616025341L;
	public static final int SIZE = 5;
	public static int[][] paintbrush = new int[SIZE][SIZE];

	public PaintBrushEditor() {
		super();
		setOpaque(true);
		// setBackground(Color.red);
		GridBagLayout g = new GridBagLayout();
		setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		JCheckBox[][] buttons = new JCheckBox[SIZE][SIZE];
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < SIZE; i++) {
					for (int j = 0; j < SIZE; j++) {
						if (buttons[i][j].isSelected())
							paintbrush[i][j] = 1;
						else
							paintbrush[i][j] = 0;
					}
				}
			}
		};

		// JLabel label = new JLabel("PaintBrush:", SwingConstants.CENTER);
		// add(label, c);
		c.gridheight = 5;
		c.gridy++;
		JPanel p = new JPanel(new GridLayout(SIZE, SIZE));
		c.weightx = 1.0f;
		for (int i = 0; i < SIZE; i++) {
			c.gridy = i;
			for (int j = 0; j < SIZE; j++) {
				c.gridx = j;

				buttons[i][j] = new JCheckBox();
				buttons[i][j].setActionCommand(i + "," + j);
				p.add(buttons[i][j], c);
				buttons[i][j].addActionListener(action);
			}
			c.gridy++;
		}
		c.gridwidth = SIZE;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(p, c);
		buttons[(SIZE - 1) / 2][(SIZE - 1) / 2].setSelected(true);
		action.actionPerformed(null);
		setBorder(new TitledBorder(new LineBorder(Color.black), "Stamp"));
	}

	public static int[][] getPaintBrush() {
		return paintbrush;
	}

}
