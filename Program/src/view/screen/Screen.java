package view.screen;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import controller.action;

import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import view.content.PanelContent;
import view.content.left.ScrollPaneTree;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.toolbar.JPanelToolBar;
import view.toolbar.ScreenToolBar;
public class Screen extends JFrame
{
	  private int dd;
	  private int chenhlech;
	  private Screen_MenuBar menubar;  
	  private String iconApp1 = "folder-icon1.png";
	  private String iconApp2 = "folder-icon2.png";
      private Font font = new Font("Arial", Font.PLAIN, 14);
      private JPanelToolBar toolbar;
      private JPanel content;
      private PanelContent content_center;
      private mouse mouseListen = new mouse(this);
      private action actionlistener;
      public Screen(String title)
      {
    	  this.setTitle(title);               //tieu de         
    	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);     // tat han khi onclick close
    	  //this.setUndecorated(true); //full
    	  this.setMinimumSize(new Dimension(800,450));   // kich thuoc be nhat
    	  this.setIconImage();  // set icon app
    	  //this.setLocationRelativeTo(null); // Giua man hinh
    	  this.init(); // 
    	  this.addObj(); //   	  
    	  this.addListen();    
    	  this.setVisible(true);  // hien app
          this.chenhlech = this.getSize().width - content_center.getSize().width;
          this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
          this.menubar.setPanelContent(content_center);
          this.content_center.getPanelContentLeft().getTreeBar().setPanelContent(content_center);
          update();
      }         
      private void addListen()
      {
    	  //this.addMouseListener(mouseListen);
    	  this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
                update();
			}
    		  
    	  });

    }
    public void update()
    {
        this.content_center.update(this.getSize().width - chenhlech, this.getSize().height - dd - chenhlech);
    }
    @Override
    public Dimension getSize()
    {
    	return super.getSize();
    }
	private void init()
      {
    	  menubar = new Screen_MenuBar(font, Color.white);
    	  toolbar = new JPanelToolBar(font);
    	  content = new JPanel();
    	  content_center = new PanelContent();
    	  actionlistener = new action(this);
      }
      private void addObj()
      {
    	  
    	  this.add(content);
    	  content.setLayout(new BorderLayout());
    	  this.setJMenuBar(menubar);
    	  content.add(toolbar, BorderLayout.NORTH);
    	  content.add(content_center, BorderLayout.CENTER);	  
    	  content_center.setSpace(5);

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