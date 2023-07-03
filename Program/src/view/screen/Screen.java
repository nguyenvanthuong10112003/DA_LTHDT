package view.screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Vector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import define.URL;
import define.table.FILE;
import define.table.FOLDER;
import model.Element;
import model.Folder;
import model.User;
import libary.ConnectSQL;
import view.content.PanelContent;
import view.menubar.Screen_MenuBar;
import view.toolbar.Screen_ToolBar;

public class Screen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dd;
	private int chenhlech;
	private Screen_MenuBar menubar;
	private String iconApp1 = "folder-icon1.png";
	private Screen_ToolBar toolbar;
	private JPanel content;
	private PanelContent content_center;
	private static User user;
	private Folder root;
	private Boolean islogin;

	public Screen(String title, User user, Boolean islogin) {
		try {
			this.islogin = islogin;
			Screen.user = user;
			new model.File(define.URL.url + define.URL.fileIcon);
			this.setRoot();
			this.setTitle(title);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setMinimumSize(new Dimension(1000, 600));
			this.setIconImage();
			this.init();
			this.addObj();
			this.addListen();
			this.setVisible(true);
			this.chenhlech = this.getSize().width - content_center.getSize().width;
			this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
			this.menubar.setPanelContent(content_center);
			this.update();
			System.out.println("Upload success screen");
		} catch (Exception e) {
			System.out.println("Error screen");
		}
	}
	
	private void init() {
		menubar = new Screen_MenuBar(this);
		toolbar = new Screen_ToolBar(this, islogin, root);
		content = new JPanel();
		content_center = new PanelContent(this, this.root, islogin);
	}
	
	public void setDD() {
		this.chenhlech = this.getSize().width - content_center.getSize().width;
		this.dd = this.getSize().height - chenhlech - content_center.getSize().height;
	}

	private void addListen() {
		this.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				update();
			}
		});
	}

	public void update() {
		this.content_center.update(this.getSize().width - chenhlech, this.getSize().height - dd - chenhlech);
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
			this.setIconImage((new ImageIcon(define.URL.url + URL.urlScreen + iconApp1)).getImage());
		} catch (Exception e) {
			System.out.print("error");
		}
	}

	public void setRoot() throws IOException, ClassNotFoundException {
		if (user == null) {
			try {
				Folder.resertMax();
				model.File.resertMax();
				java.io.File read = new java.io.File(define.URL.url + define.URL.urlLuuTru);
				FileReader fr = new FileReader(read);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					Vector<String> arr = tach(line);
					if (arr.get(arr.size() - 1).equals("")) {
						root = new Folder(Integer.parseInt(arr.get(0)), arr.get(1), Element.toDateTime(arr.get(2)), null,
								null);
					} else {
						if ("".equals(arr.get(arr.size() - 2))) {
							Folder folder = (Folder) Folder.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
							folder.getChildrents().add(new Folder(Integer.parseInt(arr.get(0)), // id
									arr.get(1), Element.toDateTime(arr.get(2)), null, folder));
						} else {
							Folder folder = (Folder) Folder.searchFolder(root, Integer.parseInt(arr.get(arr.size() - 1)));
							folder.getChildrents().add(new model.File(Integer.parseInt(arr.get(0)), // id
									arr.get(1), Element.toDateTime(arr.get(2)), Element.toDateTime(arr.get(3)), arr.get(4),
									Double.parseDouble(arr.get(5)), folder));
						}
					}
				}
				System.out.println("Read file data success!");
				fr.close();
				br.close();
			} catch (Exception ex) {
				System.out.println("Loi doc file data: " + ex);
			}
		} else {
			try {
				Connection connect = ConnectSQL.getJDBCConnection();
				Statement sta = connect.createStatement();
				Folder.setIdMax(sta);
				model.File.setIdMax(sta);
				root = user.getRoot();
				this.setRootFromSQL((Folder) this.root, sta);
				sta.close();
				connect.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void setRootFromSQL(Folder e, Statement sta) throws ClassNotFoundException {
		try {
			String sql = "SELECT * FROM " + FOLDER.nametable + " WHERE " + FOLDER.parent + " = " + e.getId();
			ResultSet rs = sta.executeQuery(sql);
			LinkedList<model.Element> els = new LinkedList<model.Element>();
			while (rs.next()) {
				Folder folder = new Folder(rs.getInt(FOLDER.id), rs.getString(FOLDER.nameFolder),
						Element.toDateTimeSQL(rs.getString(FOLDER.create)), null, e);
				els.add((model.Element) folder);
			}
			sql = "SELECT * FROM " + FILE.nametable + " WHERE " + FILE.parent + " = " + e.getId();
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				model.File file = new model.File(rs.getInt(FILE.id), rs.getString(FILE.namefile),
						Element.toDateTimeSQL(rs.getString(FILE.create)), Element.toDateTimeSQL(rs.getString(FILE.modified)),
						rs.getString(FILE.type), rs.getDouble(FILE.size), e);
				els.add((model.Element) file);
			}
			e.setChildrents(els);
			rs.close();
			for (model.Element el : e.getChildrents())
				if (el.getClass().equals(Folder.class))
					setRootFromSQL((Folder) el, sta);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private Vector<String> tach(String text) {
		Vector<String> kq = new Vector<String>();
		int z = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '|') {
				kq.add(text.substring(z, i));
				z = i + 1;
			}
		}
		kq.add(text.substring(z, text.length()));
		return kq;
	}

	public Boolean CheckToolBarVisiable() {
		return toolbar.isVisible();
	}

	public void setToolBarVisiable() {
		toolbar.Show_Hide_Function();
		this.setVisible(true);
		setDD();
		update();
	}

	public void Pin() {
		this.content_center.getCenter().Pin();
	}

	public void Cut() {
		this.content_center.getCenter().Cut();
	}

	public void Copy() {
		this.content_center.getCenter().Copy();
	}

	public void Paste() {
		try {
			this.content_center.getCenter().Paste();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void MoveTo() {
		this.content_center.getCenter().MoveTo();
	}

	public void CopyTo() {
		this.content_center.getCenter().CopyTo();
	}

	public void Delete() {
		try {
			this.content_center.getCenter().deletedRow();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void Rename() {
		this.content_center.ShowPanelRight();
	}

	public void New(Boolean b) {
		try {
			this.content_center.getCenter().newRow(b);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SelectAll() {
		this.content_center.getCenter().selectAll();
	}

	public void NoSelect() {
		this.content_center.getCenter().NoSelected();
	}

	public void runfun(int n) {
		switch (n) {
		case 1:
			Pin();
			break;
		case 2:
			Cut();
			break;
		case 3:
			Copy();
			break;
		case 4:
			Paste();
			break;
		case 5:
			MoveTo();
			break;
		case 6:
			CopyTo();
			break;
		case 7:
			Delete();
			break;
		case 8:
			Rename();
			break;
		case 9:
			New(false);
			break;
		case 10:
			New(true);
			break;
		case 11:
			SelectAll();
			break;
		case 12:
			NoSelect();
			break;
		}
	}

	public void showNew(Boolean show) {
		toolbar.getFunction().setNew(show);
	}

	public void setSelectTable(Boolean select) {
		toolbar.getFunction().setSelected(select);
	}

	public void FunEnablueRoot(Boolean root) {
		toolbar.getFunction().EnableRoot(root);
	}

	public void setBack(Boolean bool) {
		toolbar.getNavi().setEnableBack(bool);
	}

	public void setForward(Boolean bool) {
		toolbar.getNavi().setEnableForward(bool);
	}

	public void setEnPaste(Boolean bool) {
		toolbar.getFunction().setEnPaste(bool);
	}

	public void Back() {
		content_center.getCenter().back();
	}

	public void Forward() {
		content_center.getCenter().forward();
	}

	public void setNowsNavi(model.Element e) {
		toolbar.getNavi().setNows(e);
	}

	public void setNowsCenter(model.Element e) {
		content_center.getCenter().setNows(e);
	}

	public static User getUser() {
		return user;
	}

	public void UpdateTable() {
		content_center.getCenter().Update();
	}
}