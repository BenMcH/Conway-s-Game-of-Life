package com.tycoon177.conway.Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.PreferencesWindow;
import com.tycoon177.conway.utils.ConwaysGame;
import com.tycoon177.conway.utils.Settings;

public class ConwayGUIActionListener implements ActionListener {
	private JFrame frame;
	private JPanel pane;
	private JTextField number;
	private JButton set;
	private JButton cancel;
	
	public ConwayGUIActionListener() {
		frame = new JFrame("");
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(new JLabel("Size of grid (No. of cells)"));
		number = new JTextField();
		number.setOpaque(true);
		pane.add(number);
		set = new JButton("Apply");
		cancel = new JButton("Cancel");
		pane.add(set);
		pane.add(cancel);
		frame.setContentPane(pane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
		case ("ChangeSize"):
			set.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						ConwayGUI.gui.setNewBoard(new ConwaysGame(Integer.parseInt(number.getText())));
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
			frame.setVisible(true);
			break;
		case ("ChangeCellSize"):
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
			frame.setVisible(true);
			break;
		case ("Color"):
			Color c = JColorChooser.showDialog(null, "Choose a color", Settings.CELL_COLOR);
			Settings.CELL_COLOR = c;
			ConwayGUI.game.repaint();
			break;
		case ("pref"):
			PreferencesWindow win = new PreferencesWindow();
			win.setVisible(true);
			break;
		}
	}

}
