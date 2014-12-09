package com.tycoon177.conway.GUI;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tycoon177.conway.GUI.panels.AttributesPreferences;
import com.tycoon177.conway.GUI.panels.ColorPanel;
import com.tycoon177.conway.GUI.panels.PerformancePanel;

public class PreferencesWindow extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989631411837162083L;
	private JList<String> options;
	private HashMap<String, JPanel> menus;
	private JPanel currentPanel;

	public PreferencesWindow() {
		super();
		setLayout(new BorderLayout(5, 5));
		setTitle("Preferences");
		setSize(800, 600);
		instantiateMenus();
		add(getOptionsMenu(), BorderLayout.WEST);
		add(getMenu("Attributes"), BorderLayout.CENTER);
		currentPanel = getMenu("Attributes");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// this.pack();
	}

	private void instantiateMenus() {
		menus = new HashMap<String, JPanel>();
		menus.put("Attributes", new AttributesPreferences());

		menus.put("Performance", new PerformancePanel());
		menus.put("Colors", new ColorPanel());
	}

	public JPanel getOptionsMenu() {
		JPanel pane = new JPanel(new BorderLayout(), true);
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("Attributes");
		model.addElement("Performance");
		model.addElement("Colors");
		options = new JList<String>(model);
		options.setSelectedIndex(0);
		options.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(options);
		pane.add(scrollPane, BorderLayout.CENTER);
		return pane;
	}

	private JPanel getMenu(String key) {
		return menus.get(key);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		setOptionsPage(getMenu(options.getSelectedValue()));
	}

	private void setOptionsPage(JPanel menu) {
		remove(currentPanel);
		currentPanel = menu;
		add(menu, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

}
