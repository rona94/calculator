package com.example.calculator.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1083230130065872099L;

	public Panel(int width, int height, int layoutType)  {
		setPreferredSize(new Dimension(width, height));
		setPanelLayout(layoutType);		
	}

	public Panel(int width, int height, Color bgColor) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor);
	}
	
	private void setPanelLayout(int layoutType) {
		switch (layoutType) {
		case 0:
			setLayout(new BorderLayout());
			break;
		default:
			break;
		}
	}
}
