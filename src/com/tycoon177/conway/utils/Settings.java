package com.tycoon177.conway.utils;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

public class Settings implements Externalizable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8129820139187690240L;
	public static int GRID_HEIGHT = 100;
	public static int GRID_WIDTH = 100;
	public static int CELL_SIZE = 8;
	public static Color BACKGROUND_COLOR = Color.WHITE;
	public static Color GRID_COLOR = Color.LIGHTGREY;
	public static Color CELL_COLOR = Color.BLACK;
	public static File settingsFile = new File(System.getProperty("user.home")
			+ File.separator + "conway" + File.separator + "settings.dat");

	@Override
	public void readExternal(ObjectInput out) throws IOException,
			ClassNotFoundException {
		GRID_HEIGHT = out.readInt();
		GRID_WIDTH = out.readInt();
		CELL_SIZE = out.readInt();
		BACKGROUND_COLOR = (Color) out.readObject();
		GRID_COLOR = (Color) out.readObject();
		CELL_COLOR = (Color) out.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(GRID_HEIGHT);
		out.writeInt(GRID_WIDTH);
		out.writeInt(CELL_SIZE);
		out.writeObject(BACKGROUND_COLOR);
		out.writeObject(GRID_COLOR);
		out.writeObject(CELL_COLOR);
	}
}
