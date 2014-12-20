package com.tycoon177.conway.GUI.controlpanels;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.Timer;

import com.tycoon177.conway.listeners.ControlsListener;

public class Controls extends HBox {

	Timer timer;
	protected int x;
	protected int y;
	public static ControlsListener listen;
	public static StatsPanel stats;
	public static Sliders slider;
	public static Tools tools;

	public Controls() {
		super();
		Insets padding = new Insets(10);
		slider = new Sliders();
		stats = new StatsPanel();
		listen = new ControlsListener(this);
		tools = new Tools();
		GenerationSkip g = new GenerationSkip();
		Pane boardControls = new BoardControls();
		boardControls.setPadding(padding);
		slider.setPadding(padding);
		stats.setPadding(padding);
		tools.setPadding(padding);
		g.setPadding(padding);
		new PaintBrushEditor();
		getChildren().add(boardControls);
		getChildren().add(slider);
		// getChildren().add(p);
		 getChildren().add(tools);
		 getChildren().add(g);
		 getChildren().add(stats);
	}

}
