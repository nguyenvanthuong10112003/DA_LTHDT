package view.screen;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.awt.*;
import controller.action;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import controller.mouse;
import model.Folder;
import model.User;
import view.content.PanelContent;
import view.content.left.ScrollPaneTree;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.toolbar.Screen_ToolBar;
import view.toolbar.Panel_Functions;

public class Screen extends JFrame {
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
	private Folder root;
	private String urlUser;
	private String urlElements;
	private String urlLuuTru;
	private String urlQuyen;
	private String url;
	private String fileIcon;
	public Screen(String title, String urlUser, String urlElements, String urlLuuTru, String urlQuyen,
			String fileIcon) {
		try {
			this.url = System.getProperty("user.dir");
			this.urlUser = urlUser;
			this.urlElements = urlElements;
			this.urlLuuTru = urlLuuTru;
			this.urlQuyen = urlQuyen;
			this.fileIcon = fileIcon;
			new model.File(url + fileIcon);
			this.setRoot();
			this.setTitle(title); // tieu de
			this.setDefaultCloseOperation(EXIT_ON_CLOSE); // tat han khi onclick close
			// this.setUndecorated(true); //full màn hình
			this.setMinimumSize(new Dimension(800, 450)); // kich thuoc be nhat
			this.setIconImage(); // set icon app
			// this.setLocationRelativeTo(null); // Giua man hinh
			this.init(); //
			this.addObj(); //
			this.addListen();
			this.setVisible(true);
			this.chenhlech = this.getSize().width - content_center.getSize().width;
			this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
			this.menubar.setPanelContent(content_center);
			// this.content_center.getPanelContentLeft().getTreeBar().setPanelContent(content_center);
			this.update();
			System.out.println("Tải thành công màn hình");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error màn hình");
		}
	}

	public void setRoot() throws IOException
	{
		if(user == null)
		{
			if(urlLuuTru != null)
			{
			    try {
			    	
			    	java.io.File read = new java.io.File(url + urlLuuTru);
				    FileReader fr = new FileReader(read);
				    BufferedReader br = new BufferedReader(fr);
				    String line;
				    while ((line = br.readLine()) != null){
				       Vector <String>arr = tach(line);
				       if(arr.get(arr.size() - 1).equals(""))
				       {
				    	   root = new Folder(Integer.parseInt(arr.get(0)), arr.get(1),toDate(arr.get(2)), null, null); 
				       }
				       else
				       {
				    	   if(arr.get(arr.size() - 2) == "")
				    	   { 
				    		   Folder folder = root.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
				    		   folder.getChildrents().add(
				    				   new Folder(
					    				       Integer.parseInt(arr.get(0)),   // id
						    				   arr.get(1),                     // name
						    				   toDate(arr.get(2)),             // date create
						    				   null,                           // size
					    		               folder                          // cha
					    		       )
				    		    );                                                            
				    	   }
				    	   else
				    	   {
				    		   Folder folder = root.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
				    		   folder.getChildrents().add(
				    				   new model.File(
				    				       Integer.parseInt(arr.get(0)),    // id
					    				   arr.get(1),                      // name
					    				   toDate(arr.get(2)),              // date create
					    				   toDate(arr.get(3)),              // date modifield
					    				   arr.get(4),                      // typeEx
					    				   Double.parseDouble(arr.get(5)),  // size
					    		           folder                           // cha
				    		           )
				    			   );  
				    	   }
				       }
				       
				    }
				    System.out.println("Đọc file lưu trữ thành công");
				    fr.close();
				    br.close();
				    //System.out.println(root.getChildrents().get(1).getChildrents().get(0));
				    //show(root);
			    }
				catch (Exception ex) {
					// TODO: handle exception
					System.out.println("Loi doc file: "+ ex);
				}
			}
		}
	}
	public void show(model.Element folder)
	{
		System.out.println(folder.getName());
		
		for(model.Element el : folder.getChildrents())
		{
			show(el);
		}
	}
	public java.util.Date toDate(String so)
	{
		return new java.util.Date(Integer.parseInt(so.substring(6, 10)), 
				        Integer.parseInt(so.substring(3, 5)), 
				        Integer.parseInt(so.substring(0, 2)), 
				        Integer.parseInt(so.substring(11, 13)), 
				        Integer.parseInt(so.substring(14, 16)));
	}
    private Vector<String> tach(String text)
    {
    	Vector<String> kq = new Vector<String>();
    	int j = 0;
    	int z = 0;
    	for(int i = 0; i < text.length(); i++)
    	{
    		if(text.charAt(i) == '|')
    		{
    			kq.add(text.substring(z, i));
    			j++;
    			z = i + 1;
    		}
    	}
    	kq.add(text.substring(z, text.length()));
    	return kq;
    }
    
	public void setDD() {
		this.chenhlech = this.getSize().width - content_center.getSize().width;
		this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
	}

	private void addListen() {
		// this.addMouseListener(mouseListen);
		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				update();
			}

		});
	}

	public void update() {
		this.content_center.update(this.getSize().width - chenhlech, this.getSize().height - dd - chenhlech);
	}

	@Override
	public Dimension getSize() {
		return super.getSize();
	}

	private void init() {
		menubar = new Screen_MenuBar(this, url);
		toolbar = new Screen_ToolBar(this, url);
		content = new JPanel();
		content_center = new PanelContent(this.root, url);
		actionlistener = new action(this);
	}

	private void addObj() {

		this.add(content);
		content.setLayout(new BorderLayout());
		this.setJMenuBar(menubar);
		content.add(toolbar, BorderLayout.NORTH);
		content.add(content_center, BorderLayout.CENTER);
		content_center.setSpace(5);
	}

	private void setIconImage() {
		try {
			this.setIconImage((new ImageIcon(url + iconApp1)).getImage());
		} catch (Exception e) {
			System.out.print("error");
		}
	}

	public Boolean CheckTool() {
		return toolbar.isVisible();
	}

	public void setToolVisiable() {
		toolbar.Show_Hide_Function();
		this.setVisible(true);
		setDD();
		update();
	}

	public void setUser(User user) {
		this.user = user;
		this.update();
	}

	public User getUser() {
		return user;
	}

	public void Rename() {
		this.content_center.getCenter().Rename();
	}
	
	public void New(Boolean b)
	{
		this.content_center.getCenter().newRow(b);
	}
	
	public void runfun(int n)
	{
		switch(n)
		{
		    case 8: Rename(); break;
		    case 9: New(false); break;
		    case 10: New(true); break;
		}
	}
}