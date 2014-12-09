package com.tycoon177.conway.utils;

import java.awt.Color;
import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Settings implements Externalizable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8129820139187690240L;
	public static int GRID_SIZE = 100;
	public static int CELL_SIZE = 8;
	public static Color BACKGROUND_COLOR = Color.WHITE;
	public static Color GRID_COLOR = Color.LIGHT_GRAY;
	public static Color CELL_COLOR = Color.BLACK;
	public static File settingsFile = new File(System.getProperty("user.home")
			+ File.separator + "conway" + File.separator + "settings.dat");

	@Override
	public void readExternal(ObjectInput out) throws IOException,
			ClassNotFoundException {
		GRID_SIZE = out.readInt();
		CELL_SIZE = out.readInt();
		BACKGROUND_COLOR = (Color)out.readObject();
		GRID_COLOR = (Color)out.readObject();
		CELL_COLOR = (Color)out.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(GRID_SIZE);
		out.writeInt(CELL_SIZE);
		out.writeObject(BACKGROUND_COLOR);
		out.writeObject(GRID_COLOR);
		out.writeObject(CELL_COLOR);
	}
}
