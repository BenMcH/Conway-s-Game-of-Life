package com.tycoon177.conway.GUI.controlpanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.listeners.ControlsListener;

public class Controls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133961122395083677L;
	Timer timer;
	protected int x;
	protected int y;
	public static ControlsListener listen;
	public static StatsPanel stats;
	public static Sliders slider;
	public static Tools tools;

	public Controls() {
		super();
		slider = new Sliders();
		stats = new StatsPanel();
		listen = new ControlsListener(this);
		setLayout(new GridBagLayout());
		JPanel boardControls = new BoardControls();
		GridBagConstraints c = new GridBagConstraints();
		setOpaque(true);
		new PaintBrushEditor();
		c.anchor = GridBagConstraints.NORTHWEST;
		// c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .01;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(boardControls, c);
		c.gridy = 1;
		add(slider, c);
		c.gridy = 0;
		c.gridx = 2;
		c.weightx = 0.01;
		c.gridheight = 1;
		// add(p, c);
		c.gridx++;
		add((tools = new Tools()), c);
		c.gridy = 1;
		c.weighty = .001;
		add(new GenerationSkip(),c);
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.weightx = .01;
		c.gridx++;
		c.gridy = 0;
		add(new JPanel(), c);
		c.gridx++;
		c.weightx = .01;
		add(stats, c);
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
