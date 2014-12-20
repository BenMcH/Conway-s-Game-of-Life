package com.tycoon177.conway.GUI.controlpanels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.utils.Dialog;

public class GenerationSkip extends VBox {

	public GenerationSkip() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		TextField f = new TextField("0");
		Button b = new Button("Skip");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					ConwayGUI.game.skipToGeneration(Integer.parseInt(f
							.getText()));
				} catch (NumberFormatException e1) {
					Dialog.showDialog("Please Enter a Whole Number!", "Error");
				}
			}
		});
		getChildren().add(new Text("Desired Generation:"));
		getChildren().add(f);
		getChildren().add(b);
		//setBorder(new TitledBorder(new LineBorder(Color.BLACK),
			//	"Generation Skip"));
	}
}
