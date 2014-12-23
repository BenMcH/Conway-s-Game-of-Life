package com.tycoon177.conway.GUI;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;



import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.listeners.ConwaysBoardMouseListeners;
import com.tycoon177.conway.utils.ColorConverter;
import com.tycoon177.conway.utils.Dialog;
import com.tycoon177.conway.utils.Settings;

public class ConwaysGame extends Canvas {
	public volatile Cell[][] board;
	public byte[][] stamp;
	private boolean showGrid = true;
	private int generation = 0;
	private Rectangle selectionRect;
	private long numberDead = 0;
	private double position;
	@SuppressWarnings("serial")
	private ArrayList<Integer> stayAlive = new ArrayList<Integer>() {
		{
			add(2);
			add(3);
		}
	};
	@SuppressWarnings("serial")
	private ArrayList<Integer> comeAlive = new ArrayList<Integer>() {
		{
			add(3);
		}
	};

	public ConwaysGame() {
		super(10, 10);
		firstSetup();
		board = new Cell[10][10];
		randomizeBoard();
		init();
	}

	public ConwaysGame(Cell[][] b) {
		super(b.length, b[0].length);
		firstSetup();
		board = b;
		init();
	}

	public ConwaysGame(int size) {
		super(size, size);
		firstSetup();
		board = new Cell[size][size];
		init();
	}

	public ConwaysGame(int width, int height) {
		super(width, height);
		firstSetup();
		board = new Cell[width][height];
		init();
	}

	private void firstSetup() {
		this.setWidth(this.getWidth() * Settings.CELL_SIZE);
		this.setHeight(this.getHeight() * Settings.CELL_SIZE);

		ConwaysBoardMouseListeners listen = new ConwaysBoardMouseListeners();

		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				listen.mousePressed(arg0);
			}
		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				listen.mouseDragged(arg0);
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				listen.mouseReleased(arg0);
			}
		});
	}

	public void init() {
		stamp = new byte[board.length][board[0].length];
		// this.setBorder(new LineBorder(Color.BLACK));
		numberDead = 0;
		Settings.GRID_HEIGHT = board.length;
		Settings.GRID_WIDTH = board[0].length;
		// this.getChildren().removeAll();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Cell();
			}
		}
		System.out.println("Done with init");
	}

	public void reinit(int width, int height) {
		board = new Cell[width][height];
		init();
		Controls.listen.stop();
		Controls.listen.setRunning(false);
	}

	public void randomizeBoard() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setAlive(new Random().nextBoolean());
				board[i][j].setVisited(false);
			}
		step();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setVisited(false);
			}
		generation = 0;
		numberDead = 0;
		updateStats();
		drawCells();
	}

	public int getNeighbors(int i, int j) {
		int neighbors = 0;
		neighbors = 0;
		// minus
		if (i - 1 >= 0) {
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1].isAlive())
					neighbors++;

				if (board[i][j - 1].isAlive())
					neighbors++;
			}
			if (board[i - 1][j].isAlive())
				neighbors++;
		} else if (j - 1 >= 0) {
			if (board[i][j - 1].isAlive())
				neighbors++;
		}

		// plus
		if (i + 1 < board.length) {
			if (j + 1 < board[0].length) {
				if (board[i + 1][j + 1].isAlive())
					neighbors++;
				if (board[i][j + 1].isAlive())
					neighbors++;
			}
			if (board[i + 1][j].isAlive())
				neighbors++;
		} else if (j + 1 < board[0].length) {
			if (board[i][j + 1].isAlive())
				neighbors++;
		}

		// plus minus
		if (i + 1 < board.length) {
			if (j - 1 >= 0) {
				if (board[i + 1][j - 1].isAlive())
					neighbors++;
			}
		}
		if (i - 1 >= 0) {
			if (j + 1 < board[0].length) {
				if (board[i - 1][j + 1].isAlive())
					neighbors++;
			}

		}
		return neighbors;
	}

	public void step() {
		resetBoard();
		boolean[][] newBoard = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isAlive())
					board[i][j].setVisited(true);
				int neighbors = getNeighbors(i, j);
				if (board[i][j].isAlive()) {
					if (stayAlive.contains(neighbors))
						newBoard[i][j] = true;
					else {
						newBoard[i][j] = false;
						numberDead++;
					}

				} else if (comeAlive.contains(neighbors)) {
					newBoard[i][j] = true;
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setAlive(newBoard[i][j]);
			}
		}
		generation++;
		updateStats();
		drawCells();
	}

	public Cell[][] getBoard() {
		return board;
	}

	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintBackground(g2);
		paintGrid(g2);
		if (selectionRect != null) {
			g2.draw(selectionRect);
		}
	}

	private void paintBackground(Graphics2D g2) {
		g2.setColor(ColorConverter.fxToAwt(Settings.BACKGROUND_COLOR));
		g2.fillRect(0, 0, this.getPreferredSize().width,
				this.getPreferredSize().height);
	}

	private void paintGrid(Graphics2D g2) {
		g2.setColor(ColorConverter.fxToAwt(Settings.GRID_COLOR));
		g2.setStroke(new BasicStroke());
		if (showGrid) {
			g2.drawRect(0, 0, board.length * Settings.CELL_SIZE,
					board[0].length * Settings.CELL_SIZE);
			for (int i = 0; i < board.length * Settings.CELL_SIZE; i += Settings.CELL_SIZE) {
				g2.drawRect(i, 0, 0, board[0].length * Settings.CELL_SIZE);
			}
			for (int i = 0; i < board[0].length * Settings.CELL_SIZE; i += Settings.CELL_SIZE)
				g2.drawRect(0, i, board.length * Settings.CELL_SIZE, 0);
		}
	}

	public void clearBoard() {
		init();
		generation = 0;
		updateStats();
		drawCells();
	}

	public void setStayAlive(int... num) {
		stayAlive.removeAll(stayAlive);
		for (int num1 : num)
			stayAlive.add(num1);
	}

	public void setComeAlive(int... num) {
		comeAlive.removeAll(comeAlive);
		for (int num1 : num) {
			comeAlive.add(num1);
		}
	}

	/**
	 * Parse numbers in a csv style string into the numbers themselves
	 * 
	 * @param stayAlive
	 * @param comeAlive
	 */
	public void setRules(String stayAlive, String comeAlive) {
		String[] rules = stayAlive.split(",");
		String[] comeRules = comeAlive.split(",");
		int[] stayNums = new int[rules.length];
		int[] comeNums = new int[comeRules.length];
		for (int i = 0; i < stayNums.length; i++) {
			try {
				if (rules[i].trim().equals("")) {
					stayNums[i] = -1;
				} else
					stayNums[i] = Integer.parseInt(rules[i].trim());
			} catch (NumberFormatException e) {
			Dialog.showDialog("Error changing rules. Make sure to enter whole numbers separated by commas.", "Rule Error!");
				return;
			}
		}
		for (int i = 0; i < comeNums.length; i++) {
			try {
				if (comeRules[i].trim().equals(""))
					comeNums[i] = -1;
				else
					comeNums[i] = Integer.parseInt(comeRules[i]);
			} catch (NumberFormatException e) {
				Dialog.showDialog("Error changing rules. Make sure to enter whole numbers separated by commas.", "Rule Error!");
				return;
			}
		}
		setStayAlive(stayNums);
		setComeAlive(comeNums);
	}

	public void changeCellSize(int size) {
		Settings.CELL_SIZE = size;
	}

	public Dimension getPreferredSize() {
		return new Dimension(board.length * Settings.CELL_SIZE, board[0].length
				* Settings.CELL_SIZE);
	}

	public int getGeneraion() {
		return generation;
	}

	public int getAlive() {
		int alive = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != null)
					if (board[i][j].isAlive())
						alive++;
			}
		}
		return alive;
	}

	public void updateStats() {
		Controls.stats.setGeneration(getGeneraion());
		Controls.stats.setCellsAlive(getAlive());
		Controls.stats.setCellsDead(getDead());
	}

	public void drawSelectionRect(Rectangle r) {
		selectionRect = r;
	}

	public void stopDrawingSelection() {
		selectionRect = null;
	}

	public void resetBoard() {
		stamp = new byte[stamp.length][stamp[0].length];
	}

	public void skipToGeneration(int generation) {
		if (this.generation >= generation)
			Dialog.showDialog(
					"The current generation has already reached this generation",
					"Generation");
		while (this.generation < generation) {
			step();
		}
	}

	public long getDead() {
		return numberDead;
	}

	public void dispose() {
		this.board = null;
		this.selectionRect = null;
		this.stamp = null;
	}

	public void drawCells() {
		ConwayGUI.game.setWidth(Settings.GRID_WIDTH * Settings.CELL_SIZE);
		ConwayGUI.game.setHeight(Settings.GRID_HEIGHT * Settings.CELL_SIZE);

		double round = 2.3;
		GraphicsContext g = getGraphicsContext2D();
		g.clearRect(0, 0, getWidth(), getHeight());
		int width = Settings.CELL_SIZE * Settings.GRID_WIDTH / 2;
		g.setFill(Settings.BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		int x = (int) (getWidth() / 2) - width;
		x *= width;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				g.setLineWidth(.75);
				g.setFill(Settings.GRID_COLOR);
				g.setStroke(Settings.GRID_COLOR);
				g.strokeRoundRect(x + Settings.CELL_SIZE * j,
						Settings.CELL_SIZE * i, Settings.CELL_SIZE,
						Settings.CELL_SIZE, round, round);
				if (board[i][j].isAlive()) {
					g.setFill(Settings.CELL_COLOR);
					g.fillRoundRect(x + Settings.CELL_SIZE * j,
							Settings.CELL_SIZE * i, Settings.CELL_SIZE,
							Settings.CELL_SIZE, round, round);
				}
			}
		}
	}

	public void setPos(double height) {
		position = height;
	}

	public double getPos() {
		return position;
	}

}