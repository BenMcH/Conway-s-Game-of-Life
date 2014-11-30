package com.tycoon177.conway;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PaintBrushEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571809177616025341L;
	public static int[][] paintbrush = new int[3][3];
	public PaintBrushEditor(){
		super();
		setOpaque(true);
	//	setBackground(Color.red);	
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JToggleButton[][] buttons = new JToggleButton[3][3];
		ActionListener action = new ActionListener(){



			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 3; i ++){
					for(int j = 0; j < 3; j++){
						if(buttons[i][j].isSelected())
							paintbrush[i][j] = 1;
						else paintbrush[i][j] = 0;
					}
				}
				//System.out.print(paintbrush[1][1]);
			}
		};
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = i+1;
				c.gridy = j+1;
				c.weightx = .5;
				//c.weighty = .5;
				buttons[i][j] = new JToggleButton(" . ");
				buttons[i][j].setAlignmentX(RIGHT_ALIGNMENT);
				add(buttons[i][j],c);
				buttons[i][j].addActionListener(action);
			}
			add(Box.createVerticalGlue());
		}
		buttons[1][1].setSelected(true);
		action.actionPerformed(null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(100, 100);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(100, 100);
	}
	
	public static int[][] getPaintBrush(){
		return paintbrush;
	}
}
