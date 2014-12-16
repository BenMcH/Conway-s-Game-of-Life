package com.tycoon177.conway.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.listeners.ConwaysBoardMouseListeners;

public class ConwaysGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548436324523302187L;
	public int[][] board;
	private int[][] visited;
	private boolean showGrid = true;
	private int generation = 0;
	private Rectangle selectionRect;

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
		board = new int[10][10];
		randomizeBoard();
		init();
	}

	public ConwaysGame(int[][] b) {
		board = b;
		init();
	}

	public ConwaysGame(int size) {
		board = new int[size][size];
		init();
	}

	public ConwaysGame(int width, int height) {
		board = new int[width][height];
		init();
	}

	public void init() {
		ConwaysBoardMouseListeners listen = new ConwaysBoardMouseListeners();
		this.addMouseMotionListener(listen);
		this.addMouseListener(listen);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		visited = new int[board.length][board[0].length];
		// this.setBorder(new LineBorder(Color.BLACK));
		this.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				requestFocus();
			}
			
		});
	}

	public void randomizeBoard() {

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Random().nextInt(2);
			}
		generation = 0;
		updateStats();

	}

	public int getNeighbors(int i, int j) {
		int neighbors = 0;
		neighbors = 0;
		// minus
		if (i - 1 >= 0) {
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1] > 0)
					neighbors++;

				if (board[i][j - 1] > 0)
					neighbors++;
			}
			if (board[i - 1][j] > 0)
				neighbors++;
		} else if (j - 1 >= 0) {
			if (board[i][j - 1] > 0)
				neighbors++;
		}

		// plus
		if (i + 1 < board.length) {
			if (j + 1 < board[0].length) {
				if (board[i + 1][j + 1] > 0)
					neighbors++;
				if (board[i][j + 1] > 0)
					neighbors++;
			}
			if (board[i + 1][j] > 0)
				neighbors++;
		} else if (j + 1 < board[0].length) {
			if (board[i][j + 1] > 0)
				neighbors++;
		}

		// plus minus
		if (i + 1 < board.length) {
			if (j - 1 >= 0) {
				if (board[i + 1][j - 1] > 0)
					neighbors++;
			}
		}
		if (i - 1 >= 0) {
			if (j + 1 < board[0].length) {
				if (board[i - 1][j + 1] > 0)
					neighbors++;
			}

		}
		return neighbors;
	}

	public void step() {

		int[][] newBoard = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 1)
					visited[i][j] = 1;
				int neighbors = getNeighbors(i, j);
				if (board[i][j] > 0) {
					if (stayAlive.contains(neighbors))
						newBoard[i][j] = 1;
					else
						newBoard[i][j] = 0;
				} else if (board[i][j] == 0)
					if (comeAlive.contains(neighbors)) {
						newBoard[i][j] = 1;
					}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = newBoard[i][j];
			}
		}
		generation++;
		updateStats();
	}

	public int[][] getBoard() {
		return board;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintBackground(g2);
		Stroke stroke = g2.getStroke();
		paintGrid(g2);
		g2.setStroke(stroke);
		paintCells(g2);
		if (selectionRect != null) {
			g2.draw(selectionRect);
		}
	}

	private void paintBackground(Graphics2D g2) {
		g2.setColor(Settings.BACKGROUND_COLOR);
		g2.fillRect(0, 0, this.getPreferredSize().width,
				this.getPreferredSize().height);
	}

	private void paintGrid(Graphics2D g2) {
		g2.setColor(Settings.GRID_COLOR);
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

	private void paintCells(Graphics2D g2) {
		g2.setColor(Settings.CELL_COLOR);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (selectionRect != null) {
					Point p = new Point(i * Settings.CELL_SIZE, j
							* Settings.CELL_SIZE);
					if (selectionRect.contains(p))
						g2.setColor(Color.BLUE);
				}
				if (board[i][j] > 0) {
					if (board[i][j] > 1)
						g2.setColor(Color.LIGHT_GRAY);
					g2.fillRect(Settings.CELL_SIZE * i, Settings.CELL_SIZE * j,
							Settings.CELL_SIZE, Settings.CELL_SIZE);
				} else {
					if (visited[i][j] > 0) {
						g2.setColor(new Color(165, 253, 193));
						g2.fillRect(Settings.CELL_SIZE * i, Settings.CELL_SIZE
								* j, Settings.CELL_SIZE, Settings.CELL_SIZE);
					}
				}
				g2.setColor(Settings.CELL_COLOR);
			}
		}
	}

	public void clearBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
				repaint();
			}
		}
		generation = 0;
		updateStats();
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
				JOptionPane
						.showMessageDialog(null,
								"Error changing rules. Make sure to enter whole numbers separated by commas.");
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
				JOptionPane
						.showMessageDialog(null,
								"Error changing rules. Make sure to enter whole numbers separated by commas.");
				return;
			}
		}
		setStayAlive(stayNums);
		setComeAlive(comeNums);
	}

	public void changeCellSize(int size) {
		Settings.CELL_SIZE = size;
		repaint();
	}

	@Override
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
				if (board[i][j] > 0)
					alive++;
			}
		}
		return alive;
	}

	public void updateStats() {
		Controls.stats.setGeneration(getGeneraion());
		Controls.stats.setCellsAlive(getAlive());
	}

	public void drawSelectionRect(Rectangle r) {
		selectionRect = r;
	}

	public void stopDrawingSelection() {
		selectionRect = null;
	}

	public void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] > 1)
					board[i][j] -= 2;
			}
		}
	}
	
	
}