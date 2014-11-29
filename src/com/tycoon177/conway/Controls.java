package com.tycoon177.conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Controls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133961122395083677L;

	public Controls(ConwaysGame game) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton step = new JButton("      Step     ");
		add(step);
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		//Make one step
		step.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.step();
				repaint();
				revalidate();
			}
		});
		//Swing timer to automatically step
		Timer t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.step();
				repaint();
				revalidate();
			}
		});
		JButton run = new JButton("Toggle Run");
		//Continuously Step
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (t.isRunning()){
					t.stop();	
					ConwayGUI.gui.setTitle("Conways Game of Life, made by tycoon177 - Stopped");
				}
				else{
					t.start();
					ConwayGUI.gui.setTitle("Conways Game of Life, made by tycoon177 - Running");
				}
			}
		});
		add(run);
		JButton clear = new JButton("     Clear     ");
		JButton random = new JButton("   Random   ");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.clearBoard();
			}
		});
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.randomizeBoard();
				repaint();
			}
		});
		add(clear);
		add(random);

		RuleEditor rules = new RuleEditor(game);
		add(rules);
	}
	
	/*@Override
	public Dimension getPreferredSize(){
		return new Dimension(this.getParent().getWidth(), 200);
	}
*/

}
