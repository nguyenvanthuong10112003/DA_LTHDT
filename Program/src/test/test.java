package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import define.JTextFieldPassWord;
public class test {
     public static void main(String []args)
     {
    	 JFrame frame = new JFrame();
    	 JTextFieldPassWord input = new JTextFieldPassWord();
    	 frame.add(input);
    	 frame.setVisible(true);
     }
}
