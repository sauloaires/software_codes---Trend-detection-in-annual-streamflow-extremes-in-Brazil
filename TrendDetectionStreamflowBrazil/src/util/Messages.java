package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Messages {
	public static void showMsg(String message){
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		String title = "ANA";
		JOptionPane.showMessageDialog(frame, message, title, 0);
	}
	
	public static void errorMsg(String message){
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		String title = "ANA";
		JOptionPane.showMessageDialog(frame, message, title, 0);
	}
	
	public static void informMsg(String message){
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		String title = "ANA";
		JOptionPane.showMessageDialog(frame, message, title, 1);
	}
	
	public static void warningMsg(String message){
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		String title = "ANA";
		JOptionPane.showMessageDialog(frame, message, title, 2);
	}
}
