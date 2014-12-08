package com.tycoon177.conway;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ConwayGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1233273878556674435L;
	public static ConwayGUI gui;
	public static ConwaysGame game;
	JScrollPane pane;
	public ConwayGUI(ConwaysGame g) {
		super();
		setSize(920, 830);
		setTitle("Conways Game of Life, made by tycoon177 - Stopped");
		//Set the UI to look more like the system.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		pane = new JScrollPane(g);
		add(setupMenu(), BorderLayout.NORTH);
		game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(pane, BorderLayout.CENTER);
		//Make the right panel
		Controls controls = new Controls();
		this.add(controls, BorderLayout.EAST);
		//setResizable(false);
		repaint();
		revalidate();
		gui = this;
	}
	
	public JMenuBar setupMenu(){
		JMenuBar bar = new JMenuBar();
		JMenu edit = new JMenu("Edit");
		JMenu tools = new JMenu("Tools");
		JMenu preferences = new JMenu("Preferences");
		
		bar.add(edit);
		JMenuItem changeSize = new JMenuItem("Change Board Size");
		changeSize.setActionCommand("ChangeSize");
		changeSize.addActionListener(this);
		edit.add(changeSize);
		JMenuItem changeCellSize = new JMenuItem("Change Cell Size");
		changeCellSize.setActionCommand("ChangeCellSize");
		changeCellSize.addActionListener(this);
		edit.add(changeCellSize);
		JMenuItem color = new JMenuItem("Change Cell Color");
		color.setActionCommand("Color");
		color.addActionListener(this);
		
		preferences.add(color);
		edit.add(preferences);
		bar.add(tools);
		return bar;
	}
	

	public static void main(String[] args) {
		ConwaysGame game = new ConwaysGame(100);
		ConwayGUI gui = new ConwayGUI(game);
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("ChangeSize")){
			final JFrame frame = new JFrame("");
			final JPanel pane = new JPanel();
			pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
			pane.add(new JLabel("Size of grid (No. of cells)"));
			final JTextField number = new JTextField();
			number.setOpaque(true);
			pane.add(number);
			JButton set = new JButton("Apply");
			JButton cancel = new JButton("Cancel");
			set.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						setNewBoard(new ConwaysGame(Integer.parseInt(number.getText())));
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Please enter a whole number!");
						return;
					}
						frame.dispose();
				}
			});
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					frame.dispose();
				}
			});
			pane.add(set);
			pane.add(cancel);
			frame.setContentPane(pane);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		if(event.getActionCommand().equals("ChangeCellSize")){
			final JFrame frame = new JFrame("");
			final JPanel pane = new JPanel();
			pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
			pane.add(new JLabel("Size of cells"));
			final JTextField number = new JTextField();
			number.setOpaque(true);
			pane.add(number);
			JButton set = new JButton("Apply");
			JButton cancel = new JButton("Cancel");
			set.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						ConwayGUI.game.changeCellSize(Integer.parseInt(number.getText()));						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Please enter a whole number!");
						return;
					}
					frame.dispose();
				}
			});
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					frame.dispose();
				}
			});
			pane.add(set);
			pane.add(cancel);
			frame.setContentPane(pane);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		if(event.getActionCommand().equals("Color")){
			Color c = JColorChooser.showDialog(null, "Choose a color", Settings.CELL_COLOR);
			Settings.CELL_COLOR = c;
			ConwayGUI.game.repaint();
		}
	}
	
	public void setNewBoard(ConwaysGame g){
		pane.setViewportView(g);
		ConwayGUI.game = g;
		revalidate();
		repaint();
	}

}
