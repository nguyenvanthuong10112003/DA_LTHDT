package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTree;

import controller.mouse;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.screen.Screen;

public class ScreenToolBar extends JToolBar{
	  private JPanel content;
	  private JButton login;
	  private JButton register;
	  private Color black = Color.BLACK;
	  private Color white = Color.WHITE;
	  private Font font;
	  private FormLogin Flogin;
	  private FormRegister Fregister;
      public ScreenToolBar(Font font)
      {
    	  super();
    	  this.font = new Font(font.getFamily(), font.BOLD, font.getSize());
    	  init();
    	  setFont();
    	  setText();
    	  setColor();
    	  addObj();
      }
      private void setFont()
      {
          login.setFont(font);
          register.setFont(font);
      }
      private void setColor()
      {
    	  login.setBackground(black);
    	  login.setForeground(white);
    	  register.setBackground(black);
    	  register.setForeground(white);
      }
      private void init()
      {
    	  content = new JPanel();
    	  login = new JButton();
    	  register = new JButton();
      }
      private void setText()
      {
    	  login.setText("Đăng nhập");
    	  register.setText("Đăng ký");
      }
      private void addObj()
      {
    	  mouse mlisten = new mouse(this);
    	  this.setLayout(new BorderLayout());
    	  login.addMouseListener(mlisten);
    	  register.addMouseListener(mlisten);
    	  content.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	  content.add(login);
    	  content.add(register);
    	  this.add(content);
      }
      public JButton btlogin()
      {
    	  return login;
      }
      public JButton btregister()
      {
    	  return register;
      }
      public void onclick_Button(JButton bt)
      {
    	  if(bt.equals(login))
    	  {
    		  if(Fregister != null)
    		  {
    			  Fregister.setVisible(false);
    		  }
    		  if(Flogin != null)
    			  if(Flogin.isVisible())
    				  Flogin.setVisible(false);
    		  Flogin = new FormLogin();
    	  }
    	  else if(bt.equals(register))
    	  {
    		  if(Flogin != null)
    		  {
    			  Flogin.setVisible(false);
    		  }
    		  if(Fregister != null)
    			  if(Fregister.isVisible())
    				  Fregister.setVisible(false);
    		  Fregister = new FormRegister(); 		  
    	  }
    		   
      }
}
