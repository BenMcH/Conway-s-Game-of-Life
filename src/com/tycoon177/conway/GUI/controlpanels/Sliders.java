package com.tycoon177.conway.GUI.controlpanels;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.utils.Settings;

public class Sliders extends VBox {

	Slider zoom, fps;
	private int min = 2, max = 50;

	public Sliders() {
		super();
		zoom = new Slider(min, max, Settings.CELL_SIZE);
		fps = new Slider(1, 300, 20);
		zoom.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number old, Number newVal) {
				// TODO Auto-generated method stub
				Settings.CELL_SIZE = (int) Math.round(newVal.doubleValue());
				ConwayGUI.game.drawCells();
			}
		});
		fps.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number oldVal, Number newVal) {
				Controls.listen.setMaxUpdates((int) Math.round(newVal
						.doubleValue()));
			}
		});
		getChildren().add(new Text("Zoom"));
		getChildren().add(zoom);
		getChildren().add(new Text("Game Speed"));
		getChildren().add(fps);
	}

}
