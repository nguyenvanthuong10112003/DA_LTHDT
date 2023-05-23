package view.screen;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import controller.action;

import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import model.User;
import view.content.PanelContent;
import view.content.left.ScrollPaneTree;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.toolbar.Screen_ToolBar;
import view.toolbar.Panel_Functions;
public class Screen extends JFrame
{
	  private int dd;
	  private int chenhlech;
	  private Screen_MenuBar menubar;  
	  private String iconApp1 = "\\Icon\\screen\\folder-icon1.png";
	  private String iconApp2 = "\\image\\screen\\folder-icon2.png";
      private Font font = new Font("Arial", Font.PLAIN, 14);
      private Screen_ToolBar toolbar;
      private JPanel content;
      private PanelContent content_center;
      private mouse mouseListen = new mouse(this);
      private action actionlistener;
      private User user;
      private String url;
      private String fileIcon = "\\data\\iconfile.txt";
      public Screen(String title)
      {
    	  try {
    		  this.url = System.getProperty("user.dir");
	    	  new model.File(url + fileIcon);
	    	  this.user = new User("", "");
	    	  this.setTitle(title);                             //tieu de         
	    	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);     // tat han khi onclick close
	    	  //this.setUndecorated(true);                      //full màn hình
	    	  this.setMinimumSize(new Dimension(800,450));      // kich thuoc be nhat
	    	  this.setIconImage();                              // set icon app
	    	  //this.setLocationRelativeTo(null);               // Giua man hinh
	    	  this.init(); // 
	    	  this.addObj(); //   	  
	    	  this.addListen();    
	    	  this.setVisible(true);                            
	          this.chenhlech = this.getSize().width - content_center.getSize().width;
	          this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
	          this.menubar.setPanelContent(content_center);
	          this.content_center.getPanelContentLeft().getTreeBar().setPanelContent(content_center);
	          this.update();
	          System.out.println("Tải thành công màn hình");
    	  }
    	  catch (Exception e) {
			// TODO: handle exception
    		  System.out.println("Error màn hình");
		  }
      }  
      
      public void setDD()
      {
    	  this.chenhlech = this.getSize().width - content_center.getSize().width;
    	  this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
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
    	  menubar = new Screen_MenuBar(this, url);
    	  toolbar = new Screen_ToolBar(this, url);
    	  content = new JPanel();
    	  content_center = new PanelContent(this.user.getRoot(), url);
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
	    	  this.setIconImage((new ImageIcon(url + iconApp1)).getImage());
	      } catch (Exception e) {
	    	  System.out.print("error");
	      }
    }
    public Boolean CheckTool()
    {
    	  return toolbar.isVisible();
    }
    public void setToolVisiable()
    {
          toolbar.Show_Hide_Function();
          this.setVisible(true);
          setDD();
          update();
    } 
    public void setUser(User user)
    {
    	  this.user = user;
    	  this.update();
    }
    public User getUser()
    {
    	  return user;
    }
}