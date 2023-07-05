package view.content.left;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.*;
import controller.mouse;
import define.FONT;
import define.URL;
import define.table.FOLDER;
import define.table.QUICKACCESS;
import define.table.USER;
import model.Element;
import model.Folder;
import libary.ConnectSQL;
import view.content.PanelContent;
import view.screen.Screen;
import controller.*;

public class TreeBar extends JTree {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelContent pc;
	private JLabel close;
	private mouse mouseListen;
	private String iconClose16 = "close16.png";
	private String iconClose24 = "close24.png";
	private String flash16 = "flash";
	private DefaultMutableTreeNode rootTree;
	private DefaultMutableTreeNode quickaccess;
	private DefaultMutableTreeNode tree;
	private LinkedList<Element> listquick;
	private Element root;
	private JPopupMenu popup;
	private JMenuItem open;
	private JMenuItem delete_quick;
	private action ActionListen;
	private treeselection SelectListen;

	public TreeBar(PanelContent pc, Element root) {
		super();
		try {
			this.pc = pc;
			this.root = root;
			this.init();
			this.setListQuick();
			this.setRootTree();
			this.setQuickAccess();
			this.setPopup();
			this.setTree();
			this.setEvent();
			this.Edit();
			this.addObj();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}
	
	public void addObj()
	{
		this.add(close);
	}
	
	public void init()
	{
		this.mouseListen = new mouse(this);
		TreeRender render = new TreeRender();
		this.setCellRenderer(render);
		this.close = new JLabel(new ImageIcon(define.URL.url + URL.urlContentLeft + iconClose16));
	}

	public void setPopup() {
		popup = new JPopupMenu();
		open = new JMenuItem("Mở");
		delete_quick = new JMenuItem("Xóa khỏi truy cập nhanh");
		open.setEnabled(false);
		popup.add(open);
		setComponentPopupMenu(popup);
	}

	public void setEvent() {
		this.addMouseListener(mouseListen);
		this.addMouseMotionListener(mouseListen);
		this.close.addMouseListener(mouseListen);
		this.close.addMouseMotionListener(mouseListen);
		ActionListen = new action(this);
		SelectListen = new treeselection(this);
		this.addTreeSelectionListener(SelectListen);
		open.addActionListener(ActionListen);
		delete_quick.addActionListener(ActionListen);
	}

	public void treeSelection(TreePath e) {
		if (isSelectionEmpty()) {

		} else {
			if (getSelectionPaths().length == 1)
				open.setEnabled(true);
			else
				open.setEnabled(false);
			popup.remove(delete_quick);
			if (e.getPath().length > 2)
				if (e.getPathComponent(1).equals(quickaccess)) {
					popup.add(delete_quick);
				}
		}
	}

	public void Edit() {
		this.close.setBackground(new Color(102, 153, 204));
		this.setFont(FONT.font_mac_dinh);
		this.setRowHeight(18);
		this.setShowsRootHandles(true);
		this.setRootVisible(false);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.white);
		this.setModel(new DefaultTreeModel(tree));
	}

	public void setTree() {
		tree = new DefaultMutableTreeNode(new Folder(0));
		tree.add(quickaccess);
		tree.add(rootTree);
	}

	@SuppressWarnings("resource")
	public void setListQuick() throws ClassNotFoundException {
		listquick = new LinkedList<Element>();
		if (!pc.isLogin()) {
			try {
				FileReader fr = new FileReader(URL.url + URL.urlQuickAccess);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					if(line.equals(""))
						return;
					if (Folder.searchFolder((Folder) root, Integer.parseInt(line)) != null)
						listquick.add(Folder.searchFolder((Folder) root, Integer.parseInt(line)));
				}
				System.out.println("Read file QuickAccess success");
				fr.close();
				br.close();
			} catch (Exception ex) {
				System.out.println("Loi doc file: " + ex);
			}
		} else {
			String sql = "SELECT * FROM " + QUICKACCESS.nametable + " WHERE " + QUICKACCESS.user + " = '"
					+ Screen.getUser().getTenDangNhap() + "'";
			Connection connect = ConnectSQL.getJDBCConnection();
			if (connect == null) {
				System.out.println("Connect database error");
				return;
			} else {
				System.out.println("Connect database success!");
			}
			try {
				Statement sta = connect.createStatement();
				ResultSet rs = sta.executeQuery(sql);
				while (rs.next()) {
					listquick.add(Folder.searchFolder((Folder) root, rs.getInt(QUICKACCESS.folder)));
				}
				rs.close();
				sta.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setQuickAccess() {
		Folder folder = new Folder(-1, "Truy cập nhanh");
		folder.setIcon(flash16);
		quickaccess = new DefaultMutableTreeNode(folder);
		if(listquick.size() > 0)
			for (Element e : listquick) {
				folder.getChildrents().add(e);
				DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(e);
				quickaccess.add(treenode);
			}
	}

	public void setRootTree() {
		rootTree = addNodeTree(rootTree, root);
	}

	public void Update() {
		this.setQuickAccess();
		this.setRootTree();
		this.setTree();
		this.setModel(new DefaultTreeModel(tree));
	}

	public DefaultMutableTreeNode addNodeTree(DefaultMutableTreeNode rootTree, Element root) {
		rootTree = new DefaultMutableTreeNode(root);
		for (int i = 0; i < root.getChildrents().size(); i++) {
			if (root.getChildrents().get(i).getClass().equals(Folder.class)) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode();
				if (root.getChildrents().size() > 0)
					node = addNodeTree(node, root.getChildrents().get(i));
				else
					node = new DefaultMutableTreeNode(root.getChildrents().get(i));
				rootTree.add(node);
			}
		}
		return rootTree;
	}

	public Boolean addQADB(Element e) throws ClassNotFoundException {
		Boolean check = false;
		Connection connect = ConnectSQL.getJDBCConnection();
		try {
			Statement sta;
			sta = connect.createStatement();
			check = ((Folder) e).addToQuickAccessSQL(sta, Screen.getUser());
			sta.close();
			connect.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return check;
	}

	public void addPin(LinkedList<Element> list) throws IOException {
		if (listquick.size() > 0) {
			for (Element e : list) {
				for (int i = 0; i < listquick.size(); i++) {
					if (e.equals(listquick.get(i)))
						break;
					else if (i == listquick.size() - 1) {
						if (pc.isLogin()) {
							try {
								addQADB(e);
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
						}
						listquick.add(e);
						break;
					}
				}
			}
		} else {
			for (Element e : list) {
				if(pc.isLogin())
					try {
						addQADB(e);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				listquick.add(e);
			}
		}
		if (!pc.isLogin()) {
			try {
				FileWriter out = new FileWriter(define.URL.url + define.URL.urlQuickAccess);
				System.out.println("Mo file du lieu thanh cong");
				for (Element i : listquick) {
					out.write(i.getId() + "\n");
				}
				System.out.println("Ghi file thanh cong");
				out.close();
			} catch (Exception e) {
				for (int i = 0; i < list.size(); i++)
					listquick.remove(listquick.size() - 1 - i);
				return;
			}
		}
		Update();
	}

	public void ghiFile() throws IOException
	{
		FileWriter out = new FileWriter(define.URL.url + define.URL.urlQuickAccess);
		System.out.println("Mo file du lieu thanh cong");
		for (Element i : listquick) {
			out.write(i.getId() + "\n");
		}
		System.out.println("Ghi file thanh cong");
		out.close();
	}
	
	public void removePin(Element e) throws IOException {
		Boolean check = listquick.remove(e);
		if (check) {
			if (!pc.isLogin()) {
				ghiFile();
			} else {
				try {
					String sql = "DELETE " + QUICKACCESS.nametable + " WHERE " + USER.username + " = '"
							+ Screen.getUser().getTenDangNhap() + "' and " + FOLDER.id + " = " + e.getId();
					Connection connect = ConnectSQL.getJDBCConnection();
					if (connect != null) {
						System.out.println("Ket noi database thanh cong");
					} else {
						System.out.println("Ket noi database that bai");
						return;
					}
					Statement sta = connect.createStatement();
					int check1 = sta.executeUpdate(sql);
					if (check1 > 0) {
						System.out.println("Delete to quickaccess success!");
					}
					sta.close();
					connect.close();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			Update();
		}
	}

	public void eventaction(int hash) {
		if (hash == open.hashCode()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) getSelectionPath().getLastPathComponent();
			Element el = (Element) node.getUserObject();
			pc.getCenter().setNows(el);
		} else if (hash == delete_quick.hashCode()) {
			try {
				removePin(
						(Element) ((DefaultMutableTreeNode) getSelectionPath().getLastPathComponent()).getUserObject());
				System.out.print("remove to quick access success!");
			} catch (IOException e1) {
				System.out.print("error remove to quick access!");
				e1.printStackTrace();
			}
		}
	}

	public void setPanelContent(PanelContent pc) {
		this.pc = pc;
	}

	public void setIconClose(int size) {
		this.setVisible(false);
		close.setBounds(this.getSize().width - size, 0, size, size);
		this.setVisible(true);
	}

	public JLabel getIconClose() {
		return close;
	}

	public void hoverClose() {
		close.setIcon(new ImageIcon(define.URL.url + URL.urlContentLeft + iconClose24));
		setIconClose(24);
	}

	public void exitClose() {
		close.setIcon(new ImageIcon(define.URL.url + URL.urlContentLeft + iconClose16));
		setIconClose(18);
	}

	public void closeClick() {
		pc.ClosePanelLeft();
	}
	
	public LinkedList<Element> getListquick()
	{
		return listquick;
	}
}