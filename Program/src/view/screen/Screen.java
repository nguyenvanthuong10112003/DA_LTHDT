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
import java.util.LinkedList;
import java.util.Vector;
import java.awt.*;
import controller.action;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
	private String fileIcon;
	private int maxId;
	private Connection connect;
	private Boolean islogin;
	public Screen(String title, String fileIcon, User user, Connection conn, Boolean islogin) {
		try {
			this.fileIcon = fileIcon;
			this.connect = conn;
			this.islogin = islogin;
			this.user = user;
			new model.File(libary.URL.url + fileIcon);
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
			    try {
			    	java.io.File read = new java.io.File(libary.URL.url + libary.URL.urlLuuTru);
				    FileReader fr = new FileReader(read);
				    BufferedReader br = new BufferedReader(fr);
				    String line;
				    while ((line = br.readLine()) != null){
				       Vector <String>arr = tach(line);
				       if(Integer.parseInt(arr.get(0)) > maxId)
		    		       maxId = Integer.parseInt(arr.get(0));
				       if(arr.get(arr.size() - 1).equals(""))
				       {
				    	   root = new Folder(Integer.parseInt(arr.get(0)), arr.get(1),toDate(arr.get(2)), null, null); 
				       }
				       else
				       {
				    	   if(arr.get(arr.size() - 2) == "")
				    	   { 
				    		   Folder folder = (Folder)root.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
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
				    		   Folder folder = (Folder)root.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
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
					System.out.println("Loi doc file data: "+ ex);
				}
		}
		else
		{
			root = user.getRoot();
			this.setRoot((Folder)this.root);
		}
	}
	
	private void setRoot(Folder e)
	{
		try {
			String sql = "SELECT * FROM _Folder WHERE id_parent = " + e.getId();
			Statement sta = connect.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			LinkedList<model.Element> els = new LinkedList<model.Element>();
			while(rs.next())
			{
			    Folder folder = new Folder(
			    		rs.getInt("id"),
			    		rs.getString("Fullname"), 
			    		toDateTime(rs.getString("date_create")),
			    		null,
			    		e
			    		);	
			    els.add((model.Element) folder);
			}
			sql = "SELECT * FROM _File WHERE id_parent = " + e.getId();
			rs = sta.executeQuery(sql);
			while(rs.next())
			{
				/*
				        id int primary key,
						Fullname ntext,
						date_create datetime,
						date_modifield datetime,
						size decimal,
						exType char(10),
						id_parent int
				 */
				//int id, String name, Date create, Date modifield, String ex, 
	        	//double size, Folder parent
				model.File file = new model.File(
						rs.getInt("id"),
						rs.getString("Fullname"),
					    toDateTime(rs.getString("date_create")),
					    toDateTime(rs.getString("date_modifield")),
					    rs.getString("exType"),
					    rs.getDouble("size"),
					    e
						);
				els.add((model.Element) file);
			}
			e.setChildrents(els);
			sta.close();
			rs.close();
			for(model.Element el : e.getChildrents())
				if(el.getClass().equals(Folder.class))
					setRoot((Folder)el);		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					connect.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
	
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
		menubar = new Screen_MenuBar(this);
		toolbar = new Screen_ToolBar(this, connect, islogin, root);
		content = new JPanel();
		content_center = new PanelContent(this, this.root, this.maxId, islogin, connect);
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
			this.setIconImage((new ImageIcon(libary.URL.url + iconApp1)).getImage());
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
	
	
	private java.util.Date toDateTime(String d)
	{
		return new Date(
			Integer.parseInt(d.substring(0, 4)), 
			Integer.parseInt(d.substring(5, 7)), 
			Integer.parseInt(d.substring(8, 10)),
			Integer.parseInt(d.substring(11, 13)),
			Integer.parseInt(d.substring(14, 16)));
	}
	
	public User getUser() {
		return user;
	}

	public void Pin()
	{
		this.content_center.getCenter().Pin();
	}
	
	public void Cut()
	{
		this.content_center.getCenter().Cut();
	}

	public void Copy()
	{
		this.content_center.getCenter().Copy();
	}
	
	public void Paste()
	{
		this.content_center.getCenter().Paste();
	}
	
	public void MoveTo()
	{
		this.content_center.getCenter().MoveTo();
	}
	
	public void CopyTo()
	{
		this.content_center.getCenter().CopyTo();
	}
	
	public void Delete()
	{
	    this.content_center.getCenter().deletedRow();	
	}
	
	public void Rename() {
		this.content_center.ShowPanelRight();
	}
	
	public void New(Boolean b) throws IOException
	{
		this.content_center.getCenter().newRow(b);
	}
	
	// table chọn tất cả các dòng
	public void SelectAll()
	{
	     this.content_center.getCenter().selectAll();	
	}
	// table bỏ chọn tất cả các dòng
	public void NoSelect()
	{
		 this.content_center.getCenter().NoSelected();
	}
	
	public void runfun(int n) throws IOException
	{
		switch(n)
		{
		    case 1: Pin(); break;
		    case 2: Cut(); break;
		    case 3: Copy(); break;
		    case 4: Paste(); break;
		    case 5: MoveTo(); break;
		    case 6: CopyTo(); break;
	        case 7: Delete(); break;
		    case 8: Rename(); break;
		    case 9: New(false); break;
		    case 10: New(true); break;
		    case 11: SelectAll(); break;
		    case 12: NoSelect(); break;
		}
	}
	
	public Connection getConnect()
	{
		return connect;
	}
	
	public String imageIcon()
	{
		return fileIcon;
    }
	
	// hiển thị nút new folder, file
	public void showNew()
	{
		toolbar.getFunction().setNew();
	}
	
	public void setSelectTable(Boolean select)
	{
		toolbar.getFunction().SETselected(select);
	}
	
	public void FunEnablueRoot(Boolean root)
	{
		toolbar.getFunction().EnableRoot(root);
	}
	
	public void setBack(Boolean bool)
	{
		toolbar.getNavi().setEnableBack(bool);
	}
	
	public void setForward(Boolean bool)
	{
		toolbar.getNavi().setEnableForward(bool);
	}
	
	public void setEnPaste(Boolean bool)
	{
		toolbar.getFunction().setEnPaste(bool);
	}
	
	public void Back()
	{
		content_center.getCenter().back();
	}
	
	public void Forward()
	{
		content_center.getCenter().forward();
	}
	
	public void setNows(model.Element e)
	{
		toolbar.getNavi().setNows(e);
	}
}