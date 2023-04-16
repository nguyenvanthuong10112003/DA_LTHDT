package view.screen;

import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.panelcontent.JPanelContent;
import view.toolbar.ScreenToolBar;
import view.treebar.ScrollPaneTree;
public class Screen extends JFrame
{
	  private Screen_MenuBar menubar;
	  private String iconApp1 = "folder-icon1.png";
	  private String iconApp2 = "folder-icon2.png";
      private Font font = new Font("Arial", Font.PLAIN, 14);
      private ScreenToolBar toolbar;
      private JPanel content;
      private JPanel content_center;
      private ScrollPaneTree scroll;
      private mouse mouseListen = new mouse(this);
      private JPanelContent panelcontent;
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
    	  toolbar = new ScreenToolBar(font);
    	  content = new JPanel();
    	  content_center = new JPanel();
    	  panelcontent = new JPanelContent();
    	  scroll = new ScrollPaneTree(new DefaultMutableTreeNode("This pc"), panelcontent);
      }
      private void addObj()
      {
    	  content.setLayout(new BorderLayout());
    	  this.setJMenuBar(menubar);
    	  content.add(toolbar, BorderLayout.NORTH);
    	  
    	  
    	  content_center.setLayout(null);
    	  content_center.add(scroll);
    	  
    	  content_center.add(panelcontent);
    	  panelcontent.setOpaque(true);
    	  panelcontent.setBackground(Color.WHITE);
    	  panelcontent.setBounds(10 + scroll.getSize().width, 5, Toolkit.getDefaultToolkit().getScreenSize().width - 15 - scroll.getSize().width, 100);
    	  content.add(content_center, BorderLayout.CENTER);
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
      
}