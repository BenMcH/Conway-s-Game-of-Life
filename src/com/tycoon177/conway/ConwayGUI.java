package com.tycoon177.conway;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

public class ConwayGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1233273878556674435L;
	private ConwaysGame game;

	public ConwayGUI(ConwaysGame g) {
		super();
		setSize(920, 830);
		setTitle("Conways Game of Life, made by tycoon177 - Stopped");
		//Set the UI to look more like the system.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(this.game, BorderLayout.CENTER);
		//Make the right panel
		JPanel controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		JButton step = new JButton("      Step     ");
		controls.add(step);
		controls.setBackground(Color.LIGHT_GRAY);
		controls.setOpaque(true);
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
		Timer t = new Timer(100, new ActionListener() {
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
					setTitle("Conways Game of Life, made by tycoon177 - Stopped");
				}
				else{
					t.start();
					setTitle("Conways Game of Life, made by tycoon177 - Running");
				}
			}
		});
		controls.add(run);
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
		controls.add(clear);
		controls.add(random);
		
		RuleEditor rules = new RuleEditor(game);
		controls.add(rules);
		this.add(controls, BorderLayout.EAST);
		setResizable(false);
		repaint();
		revalidate();
	}

	public static void main(String[] args) {
		ConwaysGame game = new ConwaysGame(100);
		ConwayGUI gui = new ConwayGUI(game);
		// game.randomizeBoard();
		gui.setVisible(true);
	}

}
