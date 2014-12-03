package com.tycoon177.conway;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class PaintBrushEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571809177616025341L;
	public static final int SIZE = 5;
	public static int[][] paintbrush = new int[SIZE][SIZE];
	public PaintBrushEditor(){
		super();
		setOpaque(true);
	//	setBackground(Color.red);	
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JCheckBox[][] buttons = new JCheckBox[SIZE][SIZE];
		ActionListener action = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < SIZE; i ++){
					for(int j = 0; j < SIZE; j++){
						if(buttons[i][j].isSelected())
							paintbrush[i][j] = 1;
						else paintbrush[i][j] = 0;
					}
				}
				//System.out.print(paintbrush[1][1]);
			}
		};
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = i+1;
				c.gridy = j+1;
				c.weightx = .5;
				//c.weighty = .SIZE;
				buttons[i][j] = new JCheckBox();
				buttons[i][j].setAlignmentX(RIGHT_ALIGNMENT);
				add(buttons[i][j],c);
				buttons[i][j].addActionListener(action);
			}
			add(Box.createVerticalGlue());
		}
		buttons[(SIZE-1)/2][(SIZE-1)/2].setSelected(true);
		action.actionPerformed(null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(105, 100);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(120, 150);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(100, 100);
	}
	
	public static int[][] getPaintBrush(){
		return paintbrush;
	}
}
