package view.toolbar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.mouse;
import view.screen.Screen;

public class ScreenToolBar extends JToolBar{
	  private Screen sc;
	  private JPanel content;
	  private JButton login;
	  private JButton register;
      public ScreenToolBar(Screen sc)
      {
    	  super();
    	  this.sc = sc;
    	  init();
    	  setText();
    	  addObj();
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
    	  mouse mlisten = new mouse(sc);
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
}
