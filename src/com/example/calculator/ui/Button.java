package com.example.calculator.ui;
import java.awt.Color;

import javax.swing.JButton;

import com.example.calculator.utils.ButtonType;

public class Button extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7196363953902143443L;

	public Button(String value, ButtonType type, Color fgColor, Color bgColor) {
		setText(value);
		setFocusPainted(false);

		if(value == "") {
			setBackground(Color.gray);
			setEnabled(false);
		} 
		else {
			setBackground(bgColor);
			setForeground(fgColor);
		}
	}
}
