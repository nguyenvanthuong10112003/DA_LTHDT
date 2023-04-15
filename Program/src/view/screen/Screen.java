package view.screen;

import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.mouse;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.toolbar.ScreenToolBar;
public class Screen extends JFrame
{
	  private Screen_MenuBar menubar;
	  private String iconApp1 = "folder-icon1.png";
	  private String iconApp2 = "folder-icon2.png";
      private Font font = new Font("Arial", Font.PLAIN, 14);
      private FormRegister register;
      private FormLogin login;
      private ScreenToolBar toolbar;
      private JPanel content;
      private mouse mouseListen = new mouse(this);
      public Screen(String title)
      {
    	  this.setTitle(title);               //tieu de
    	  //this.setLocationRelativeTo(null);         
    	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);     // tat han khi onclick close
    	  this.setExtendedState(this.MAXIMIZED_BOTH);        // kich thuoc to nhat
    	  //this.setUndecorated(true); full
    	  this.setMinimumSize(new Dimension(800,450));   // kich thuoc be nhat
    	  this.setIconImage();  // set icon app
    	  this.init(); // khởi tạo các thuộc tính
    	  this.addObj(); // thêm các thuộc tính
    	  this.setVisible(true);  // hien app
      }         
      private void init()
      {
    	  menubar = new Screen_MenuBar(font, Color.white);
    	  toolbar = new ScreenToolBar(this);
    	  content = new JPanel();
      }
      private void addObj()
      {
    	  content.setLayout(new BorderLayout());
    	  this.setJMenuBar(menubar);
    	  content.add(toolbar, BorderLayout.NORTH);
    	  this.add(content);
      }
      private void setIconImage()
      {
	      try {
	    	  this.setIconImage((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(iconApp1)))).getImage());
	      } catch (Exception e) {
	    	  System.out.print("error");
	      }
      }
      public void onclick_Button(JButton bt)
      {
    	  if(bt.equals(toolbar.btlogin()))
    	  {
    		  if(register != null)
    			  register.setVisible(false);
    		  login = new FormLogin();
    	  }
    	  else if(bt.equals(toolbar.btregister()))
    	  {
    		  if(login != null)
    			  login.setVisible(false);
    		  register = new FormRegister(); 		  
    	  }
    		   
      }
}
