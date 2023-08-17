package com.example.calculator.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelButtons extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 696402314229094894L;
	private static final int spacing = 5;

	public PanelButtons(int width, int height, int col, int row, Color color, EmptyBorder border) {
		// set GridLayout
		GridLayout layout4Col = new GridLayout(col, row);
		layout4Col.setHgap(spacing);
		layout4Col.setVgap(spacing);
				
		setPreferredSize(new Dimension(width, height));
		setLayout(layout4Col);
		setBackground(color);
		setBorder(border); // top, left, bottom, right
	}
}
