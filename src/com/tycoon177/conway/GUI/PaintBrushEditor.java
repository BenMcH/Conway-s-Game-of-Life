package com.tycoon177.conway.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
				// System.out.print(paintbrush[1][1]);
			}
		};

		JLabel label = new JLabel("PaintBrush:", SwingConstants.CENTER);

//		c.anchor = GridBagConstraints.CENTER;
		
		add(label,c);
		//c.fill = GridBagConstraints.NONE;
		c.gridheight = 5;
		c.gridy++;
		JPanel p = new JPanel(new GridLayout(SIZE, SIZE));
		c.weightx = 1.0f;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				c.gridx = i;
				c.gridy = j + 1;
				
				buttons[i][j] = new JCheckBox();
//				buttons[i][j].setAlignmentX(RIGHT_ALIGNMENT);
				p.add(buttons[i][j]);
				buttons[i][j].addActionListener(action);
			}
			c.gridwidth = SIZE;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c. gridy = 1;
			add(p,c);
		}
		buttons[(SIZE - 1) / 2][(SIZE - 1) / 2].setSelected(true);
		action.actionPerformed(null);
	}
	

	public static int[][] getPaintBrush() {
		return paintbrush;
	}
	
}
