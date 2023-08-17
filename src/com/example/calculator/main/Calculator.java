package com.example.calculator.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.example.calculator.ui.Label;
import com.example.calculator.ui.Panel;
import com.example.calculator.ui.PanelButtons;
import com.example.calculator.utils.ButtonLoop;
import com.example.calculator.utils.ButtonType;

public class Calculator extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel label;
	private static JButton bNum[];
	private static JButton bTop[];
	private static JButton bBottom[];
	private static JButton bRight[];
	
	private static boolean activeTest = false;
	private static String oldNumVal = "0";
	private static String oldType = "";
	
	private static final String[] sTop = {"C","","","x"};
	private static final String[] sBottom = {"0",".","+","="};
	private static final String[] sRight = {"÷","*","-"};
	
	Calculator() {
		setTitle("Calculator"); // frame title
		setResizable(false); // set resize
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close application
		setLayout(new BorderLayout()); 
		setSize(300, 400); // set frame size
		setLocationRelativeTo(null); // center align application
		
		////////////////////
		// Frame
		////////////////////
		
		// JPanel
		JPanel jpLabel = new Panel(100, 100, Color.decode("#242220")); // width, height, color
		JPanel jpButtons = new Panel(100, this.getHeight()-jpLabel.getHeight(), 0); // width, height, layout
		
		// add panel to frame
		add(jpLabel, BorderLayout.NORTH);
		add(jpButtons, BorderLayout.CENTER);
		setVisible(true);
		
		
		////////////////////
		// Text
		////////////////////
		label = new Label(oldNumVal, jpLabel.getWidth(), jpLabel.getHeight()); // text value, width, height 		
		jpLabel.add(label);	// add label to frame
		
		
		////////////////////
		// Buttons
		////////////////////
		
		// set Button Layouts width and height
		int jpButtWidth = jpButtons.getWidth() / 4;
		int jpButtHeight = jpButtons.getHeight() / 5;
		
		// Button Layouts		
		JPanel jpButtTop = new PanelButtons(100, jpButtHeight+2, 0, 4, Color.decode("#242220"), new EmptyBorder(5,5,5,5)); // width, height, column, row, Color color, EmptyBorder
		JPanel jpButtNum = new PanelButtons(jpButtWidth*3, jpButtHeight*3, 0, 3, Color.decode("#242220"), new EmptyBorder(0,6,0,5));
		JPanel jpButtRight = new PanelButtons(jpButtWidth, jpButtHeight*3, 0, 1, Color.decode("#242220"), new EmptyBorder(0,0,0,7));
		JPanel jpButtBottom = new PanelButtons(100, jpButtHeight+2, 0, 4, Color.decode("#242220"), new EmptyBorder(5,5,5,5));
		
		// add panel buttons to frame
		jpButtons.add(jpButtTop, BorderLayout.NORTH);
		jpButtons.add(jpButtNum, BorderLayout.WEST);
		jpButtons.add(jpButtRight, BorderLayout.EAST);
		jpButtons.add(jpButtBottom, BorderLayout.SOUTH);
		
		////////////////////		
		
		ButtonLoop buttonsTop = new ButtonLoop(sTop);
		buttonsTop.setButtons(this, jpButtTop, bTop, ButtonType.TOP);
		bTop = buttonsTop.getButtons();
				
		ButtonLoop buttonsNum = new ButtonLoop();
		buttonsNum.setButtonNum(this, jpButtNum, bNum, ButtonType.NUM);
		bNum = buttonsNum.getButtons();
		
		ButtonLoop buttonsBottom = new ButtonLoop(sBottom, Color.white, Color.black);
		buttonsBottom.setButtons(this, jpButtBottom, bBottom, ButtonType.BOTTOM);
		bBottom = buttonsBottom.getButtons();
		
		ButtonLoop buttonsRight = new ButtonLoop(sRight);
		buttonsRight.setButtons(this, jpButtRight, bRight, ButtonType.RIGHT);
		bRight = buttonsRight.getButtons();
		
		////////////////////
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String val = "";
		
		// Number buttons [1-9]		
		for(int i=0; i<bNum.length; i++) {
			if(e.getSource() == bNum[i]) {
				val = getCalcValue(label, bNum[i]);
				label.setText(val);
			}
		}
		
		// Top buttons [C, x]		
		for(int i=0; i<bTop.length; i++) {
			if(e.getSource() == bTop[i] && i== 0) {
				label.setText("0");
				activeTest = false;
				oldNumVal = "0";
			}
			else if(e.getSource() == bTop[i] && i== 3) {
				String cNum = label.getText();
				cNum = cNum.substring(0, cNum.length()-1);
				
				if(cNum == "") {
					cNum = "0";
				}
				
				label.setText(cNum);
			}
		}
		
		// buttons [0,.,+,=]	
		for(int i=0; i<bBottom.length; i++) {
			if(e.getSource() == bBottom[i] && i == 0) { // do on click 0
				val = getCalcValue(label, bBottom[0]);
				label.setText(val);
			}
			else if(e.getSource() == bBottom[i] && i == 1) { // do on click dot '.'
				val = getNumDecimal(label.getText());
				label.setText(val);
			}
			else if(e.getSource() == bBottom[i] && i == 2) { // do on click plus '+'
				if(!activeTest) {
					if(oldNumVal != "0" && oldNumVal != "") {
						String tt = calculateNums(oldNumVal, label.getText());
						label.setText(tt);
						oldNumVal = tt;
					}
					else {
						oldNumVal = label.getText();
					}
					oldType = "+";
				}
				
				activeTest = !activeTest;
			}
			else if(e.getSource() == bBottom[i] && i == 3) { // do on click equal '='
				String tt = calculateNums(oldNumVal, label.getText());
				label.setText(tt);
				
				activeTest = false;
				oldNumVal = "0";
				oldType = "";
			}
		}
		
		// buttons [1-9]	
		for(int i=0; i<bRight.length; i++) {
			if(e.getSource() == bRight[i]) {
				if(!activeTest) {
					if(oldNumVal != "0" && oldNumVal != "") {
						String tt = calculateNums(oldNumVal, label.getText());
						label.setText(tt);
						oldNumVal = tt;
					}
					else {
						oldNumVal = label.getText();
					}
					
					oldType = bRight[i].getText();
				}
				
				activeTest = !activeTest;
			}
		}
	}
	
	public String getCalcValue(JLabel o, JButton n) {
		// reset label
		if(activeTest) {
			o.setText("");
			activeTest = false;
		}
		
		String oldText = o.getText();
		String newText = n.getText();
		
		// remove leading 0
		if(oldText == "0") {
			return newText;
		}
		
		return oldText+newText;
	}
	
	public String getNumDecimal(String s) {
		if(s.contains(".")) {
			return s;
		}
		
		return s+".";
	}
	
	public String calculateNums(String oldNum, String newNum) {
		double num1 = Double.parseDouble(oldNum);
		double num2 = Double.parseDouble(newNum);
		double t = 0.0;
		
		switch(oldType) {
			case "-": 
				t = num1-num2; break;
			case "÷": 
				t = num1/num2; break;
			case "*": 
				t = num1*num2; break;
			default:
				t = num1+num2;
		}
		
		if(oldNum.contains(".") || newNum.contains(".")) {
			return String.valueOf(t);
		}
		
		return String.valueOf((int) t);
	}
}
