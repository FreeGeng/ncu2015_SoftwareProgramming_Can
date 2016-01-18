package cot.ko.uim;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import cot.qin.sre.SceneRenderEngine;

public class MainPanel extends JPanel {
	private GameScreenPanel gsp;
	private UnitPanel up;
	private TowerPanel tp;
	private UnitStatePanel usp;

	private SceneRenderEngine sre = SceneRenderEngine.getInstance();

	private boolean isUnitUpgrade = true;

	public MainPanel(MainFrame mf, String heroname) {
		Dimension size = new Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setSize(size);
		this.setLayout(null);

		gsp = GameScreenPanel.getInstance(mf, this);
		up = new UnitPanel(heroname);
		tp = new TowerPanel();
		usp = UnitStatePanel.getInstance();

		gsp.setBounds(0, 0, 1000, 450);
		up.setBounds(0, 450, 1000, 150);
		tp.setBounds(0, 450, 1000, 150);
		usp.setBounds(300, 100, 400, 268);

		this.add(usp);
		this.add(gsp);
		this.add(up);
		this.add(tp);
		
		setGSPListener();

	}

	public void ChangeUpgradePanel(String upPanel) {
		if (upPanel.equals("UnitUp")) {
			up.setVisible(true);
			tp.setVisible(false);
		} else {
			up.setVisible(false);
			tp.setVisible(true);
		}
	}

	public void setState(boolean isUnitUpgrade) {
		this.isUnitUpgrade = isUnitUpgrade;
	}

	public boolean getState() {
		return isUnitUpgrade;
	}
	
	private void setGSPListener() {

		gsp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		gsp.getActionMap().put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sre.renderScene(2);
				gsp.repaint();
			}
		});
		gsp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "do");
		gsp.getActionMap().put("do", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sre.renderScene(1);
				gsp.repaint();
			}
		});
		gsp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "le");
		gsp.getActionMap().put("le", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sre.renderScene(4);
				gsp.repaint();
			}
		});
		gsp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "ri");
		gsp.getActionMap().put("ri", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sre.renderScene(3);
				gsp.repaint();
			}
		});
	}
}
