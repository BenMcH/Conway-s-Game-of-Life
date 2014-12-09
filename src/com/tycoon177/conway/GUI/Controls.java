package com.tycoon177.conway.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.tycoon177.conway.Listeners.ControlsListener;

public class Controls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133961122395083677L;
	Timer timer;
	protected int x;
	protected int y;
	public static ControlsListener listen;

	public Controls() {
		super();
		listen = new ControlsListener(this);
		setLayout(new GridBagLayout());
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton step = new JButton("Step");
		// setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		// Make one step
		step.setActionCommand("step");
		step.addActionListener(listen);
		JButton run = new JButton("Toggle Run");
		run.setActionCommand("run");
		// Continuously Step
		run.addActionListener(listen);
		JButton clear = new JButton("Clear");
		clear.setActionCommand("clear");
		JButton random = new JButton("Random");
		random.setActionCommand("random");
		clear.addActionListener(listen);
		random.addActionListener(listen);

		PaintBrushEditor p = new PaintBrushEditor();
		// handleDrag(p);
		p.setAlignmentX(JComponent.LEFT_ALIGNMENT);

		c.anchor = GridBagConstraints.NORTH;
		c.weightx = .01;
		c.weighty = .01;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(step, c);
		c.gridy = 2;
		c.gridx = 0;
		pane.add(run, c);
		c.gridy = 0;
		c.gridx = 1;
		pane.add(clear, c);
		c.gridy = 2;
		c.gridx = 1;
		pane.add(random, c);
		c.gridx = 0;
		pane.setBorder(new TitledBorder(new LineBorder(Color.black),
				"Board Control"));
		add(pane, c);
		c.gridx++;
		c.weightx = 0.01;
		c.gridheight = 1;
		add(p, c);
		c.weightx = 1;
		c.gridx++;
		add(new JPanel(), c);
	}

	@Override
	public void repaint() {
		super.repaint();
		if (ConwayGUI.game != null)
			ConwayGUI.game.repaint();

	}

	public void handleDrag(final JPanel panel) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				x = me.getX();
				y = me.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent me) {
				me.translatePoint(me.getComponent().getLocation().x - x, me
						.getComponent().getLocation().y - y);
				panel.setLocation(me.getX(), me.getY());
			}
		});
	}

}
