package com.example.calculator.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Label extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3634325570489055113L;
	private String value;
	
	public Label(String value, int width, int height) {
		this.value = value;
		
		setText(value);
		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(SwingConstants.RIGHT);
		setVerticalAlignment(SwingConstants.BOTTOM);
		setBorder(new EmptyBorder(0, 0, 15, 15));
		setFont(new Font("Calibri", Font.PLAIN, 25)); // font name, font style, font size
		setForeground(Color.white);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
