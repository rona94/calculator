package com.example.calculator.utils;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.example.calculator.main.Calculator;
import com.example.calculator.ui.Button;

public class ButtonLoop {	
	private JButton[] buttons;
	private String[] sArray;
	private Color fgColor = Color.black;
	private Color bgColor = Color.decode("#cf7e0c");
	
	public ButtonLoop() {	
	}

	public ButtonLoop(String[] sArray) {
		this.sArray = sArray;
	}
	
	public ButtonLoop(String[] sArray, Color fgColor, Color bgColor) {
		this.sArray = sArray;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	public void setButtonNum(Calculator frame, JPanel panel, JButton[] butt, ButtonType type) {
		buttons = butt;
		
		buttons = new JButton[9];
		int div = 3;
		
		for(int i=buttons.length-1; i>=0; i--) {
			int a = buttons.length - (i%div);
			int b =  ((div-1) - (i % buttons.length/div)) * div;
			int val = a-b;
			
			buttons[i] = new Button(String.valueOf(val), type, Color.white, Color.black);
			buttons[i].addActionListener(frame);
			panel.add(buttons[i]);
		}
	}
	
	public void setButtons(Calculator frame, JPanel panel, JButton[] butt, ButtonType type) {
		buttons = butt;
		
		buttons = new JButton[sArray.length];
		
		for(int i=0; i<buttons.length; i++) {
			// temp
			if(sArray[i] == "=") {
				buttons[i] = new Button(sArray[i], type, Color.black, Color.decode("#cf7e0c"));
			}
			else {
				buttons[i] = new Button(sArray[i], type, fgColor, bgColor);
			}
			buttons[i].addActionListener(frame);
			panel.add(buttons[i]);
		}
	}

	public JButton[] getButtons() {
		return buttons;
	}
}
