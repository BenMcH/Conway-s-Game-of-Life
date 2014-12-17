package com.tycoon177.conway.GUI;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.listeners.ConwaysBoardMouseListeners;
import com.tycoon177.conway.utils.Settings;

public class ConwaysGame extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548436324523302187L;
	public volatile Cell[][] board;
	public byte[][] stamp;
	private boolean showGrid = true;
	private int generation = 0;
	private Rectangle selectionRect;
	private long numberDead = 0;

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
		firstSetup();
		board = new Cell[10][10];
		randomizeBoard();
		init();
	}

	public ConwaysGame(Cell[][] b) {
		firstSetup();
		board = b;
		init();
	}

	public ConwaysGame(int size) {
		firstSetup();
		board = new Cell[size][size];
		init();
	}

	public ConwaysGame(int width, int height) {
		firstSetup();
		board = new Cell[width][height];
		init();
	}
	
	private void firstSetup(){
		ConwaysBoardMouseListeners listen = new ConwaysBoardMouseListeners();
		this.addMouseMotionListener(listen);
		this.addMouseListener(listen);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				requestFocus();
			}
			
		});

	}

	public void init() {
		stamp = new byte[board.length][board[0].length];
		// this.setBorder(new LineBorder(Color.BLACK));
		numberDead = 0;
		Settings.GRID_HEIGHT = board.length;
		Settings.GRID_WIDTH = board[0].length;
		this.removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		for (int i = 0; i < board.length; i++) {
			c.gridx++;
			c.gridy = 0;
			c.weightx = .01;
			c.weighty = .01;
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Cell();
				add(board[i][j], c);
				c.gridy++;
			}
		}
		c.weightx = Integer.MAX_VALUE;
		c.weighty = Integer.MAX_VALUE;
		c.gridy++;
		c.gridx++;
		add(new JPanel(), c);
		System.out.println("Done with init");
	}
	
	public void reinit(int width, int height){
		Controls.listen.runTimer(false);
		System.out.println("Tim");
		
		board = new Cell[width][height];
		System.out.println("Tim");
		init();
		System.out.println("Tim");
		//repaint();
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
	}

	public Cell[][] getBoard() {
		return board;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintBackground(g2);
		paintGrid(g2);
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

	public void clearBoard() {
		init();
		repaint();
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
			JOptionPane
					.showMessageDialog(
							null,
							"The current generation has already reached this generation",
							"Generation", JOptionPane.PLAIN_MESSAGE);
		while (this.generation < generation) {
			step();
		}
		repaint();
	}

	public long getDead() {
		return numberDead;
	}

	public void dispose() {
		this.board = null;
		this.selectionRect = null;
		this.stamp = null;
	}

}