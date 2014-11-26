package com.tycoon177.conway;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RuleEditor extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8320309158451775180L;
	private ConwaysGame game;
	public RuleEditor(ConwaysGame game) {
		super();
		this.game = game;
		init();
	}
	
	public void init(){
		JLabel title = new JLabel("Rule Changer");
		JLabel stay = new JLabel("Stay Alive #s");
		JLabel come = new JLabel("Come to life #s");
		JTextField stayAlive = new JTextField("2, 3");
		stayAlive.setToolTipText("Enter numbers separated by commas that will keep the cells alive.");
		JTextField comeAlive = new JTextField("3");
		comeAlive.setToolTipText("Enter numbers separated by commas that will bring the cells to life.");
		JButton set = new JButton("     Set   " );
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(title);
		this.add(stay);
		this.add(stayAlive);
		this.add(come);
		this.add(comeAlive);
		this.add(set);
		set.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.setRules(stayAlive.getText(), comeAlive.getText());
			}
		});
	}

	@Override 
	public Dimension getMaximumSize(){
		Dimension d = new Dimension(this.getParent().getWidth(), 110);
		return d;
	}

}
