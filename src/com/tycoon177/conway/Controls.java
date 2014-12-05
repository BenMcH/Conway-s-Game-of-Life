package com.tycoon177.conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Controls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133961122395083677L;
	Timer timer;
	SpeedChanger speed;
	public Controls() {
		super();
		speed = new SpeedChanger(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton step = new JButton("      Step     ");
		add(step);
		//setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		// Make one step
		step.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.step();
				repaint();
				revalidate();
			}
		});
		// Swing timer to automatically step
		timer = new Timer(1000/20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.step();
				repaint();
				revalidate();
			}
		});
		JButton run = new JButton("Toggle Run");
		// Continuously Step
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
					ConwayGUI.gui
							.setTitle("Conways Game of Life, made by tycoon177 - Stopped");
					speed.setDisabled(true);
				} else {
					timer.start();
					ConwayGUI.gui
							.setTitle("Conways Game of Life, made by tycoon177 - Running");
					speed.setDisabled(false);
				}
			}
		});
		add(run);
		JButton clear = new JButton("     Clear     ");
		JButton random = new JButton("   Random   ");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.clearBoard();
			}
		});
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConwayGUI.game.randomizeBoard();
				repaint();
			}
		});
		add(clear);
		add(random);

		RuleEditor rules = new RuleEditor();
		add(rules);
		this.add(speed);
		PaintBrushEditor p = new PaintBrushEditor();
		p.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		this.add(p);
	
	}

	@Override
	public void repaint() {
		super.repaint();
		if (ConwayGUI.game != null)
			ConwayGUI.game.repaint();

	}

	public void setMaxUpdates(int updates) {

		timer.setDelay(1000/updates);
	
	}

}
