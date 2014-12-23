package com.tycoon177.conway.GUI;

import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.tycoon177.conway.GUI.panels.AttributesPreferences;
import com.tycoon177.conway.GUI.panels.ColorPanel;

public class PreferencesWindow extends Stage {

	private static ListView<String> options;
	private static HashMap<String, Pane> menus;
	private static BorderPane p;

	public PreferencesWindow() {
		super();
		setTitle("Preferences");
		p = new BorderPane();
		instantiateMenus();
		p.setLeft(getOptionsMenu());
		p.setCenter(getMenu("Attributes"));
		Scene s = new Scene(p);
		this.setScene(s);
	}

	private static void instantiateMenus() {
		menus = new HashMap<String, Pane>();
		menus.put("Attributes", new AttributesPreferences());
		 menus.put("Colors", new ColorPanel());
	}

	public static VBox getOptionsMenu() {
		VBox pane = new VBox();
		ObservableList<String> model = FXCollections.observableArrayList();
		model.add("Attributes");
		//model.add("Performance");
		model.add("Colors");
		options = new ListView<String>(model);
		options.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String arg1, String arg2) {

						setOptionsPage(getMenu(arg2));
					}

				});
		// options.addListSelectionListener(this);
		ScrollPane scrollPane = new ScrollPane(options);
		pane.getChildren().add(scrollPane);
		return pane;
	}

	private static Pane getMenu(String key) {
		return menus.get(key);
	}

	public void valueChanged() {

	}

	private static void setOptionsPage(Pane menu) {
		// remove(currentPanel);
		// currentPanel = menu;
		// add(menu, BorderLayout.CENTER);
		// revalidate();
		// repaint();
		p.setCenter(menu);
	}

	public static void showPreferences() {
		Stage s = new Stage();

		p = new BorderPane();
		instantiateMenus();
		p.setLeft(getOptionsMenu());
		p.setCenter(getMenu("Attributes"));
		Scene scene = new Scene(p);
		s.setScene(scene);
		s.show();
	}

}
