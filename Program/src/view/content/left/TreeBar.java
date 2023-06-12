package view.content.left;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.*;
import controller.mouse;
import libary.FONT;
import libary.URL;
import model.Element;
import model.Folder;
import view.content.PanelContent;

public class TreeBar extends JTree {
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
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem delete;
	private JMenuItem delete_quick;
	public TreeBar(PanelContent pc, Element root) {
		super();
		this.pc = pc;
		this.root = root;
		this.setListQuick();
		this.setRootTree();
		this.setQuickAccess();
		this.setPopup();
		this.setTree();
		this.setModel(new DefaultTreeModel(tree));
		this.close = new JLabel(new ImageIcon(libary.URL.url + URL.urlContentLeft + iconClose16));
		this.add(close);
		this.mouseListen = new mouse(this);
		this.addMouseListener(mouseListen);
		this.addMouseMotionListener(mouseListen);
		this.close.addMouseListener(mouseListen);
		this.close.addMouseMotionListener(mouseListen);
		TreeRender render = new TreeRender();
		this.setCellRenderer(render);
		this.setEvent();
		this.Edit();
	}
	
	public void setPopup()
	{
		popup = new JPopupMenu();
		open = new JMenuItem("Mở");
		cut = new JMenuItem("Cắt");
		copy = new JMenuItem("Sao chép");
		paste = new JMenuItem("Dán");
		delete = new JMenuItem("Xóa");
		delete_quick = new JMenuItem("Xóa khỏi truy cập nhanh");
		open.setEnabled(false);
		popup.add(open);
		
		setComponentPopupMenu(popup);
	}
	
	public void setEvent()
	{
		this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				if(isSelectionEmpty()) {

				}
				else
				{
					if(getSelectionPaths().length == 1)
						open.setEnabled(true);
					else
						open.setEnabled(false);
				}
			}
		});
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)getSelectionPath().getLastPathComponent();
				Element el = (Element) node.getUserObject();
				pc.getCenter().setNows(el);
			}
		});
	}
	
	public void Edit()
	{
		this.close.setBackground(new Color(102, 153, 204));
		this.setFont(FONT.font_mac_dinh);
		this.setRowHeight(18);
		this.setShowsRootHandles(true);
		this.setRootVisible(false);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.white);
	}

	public void setTree()
	{
		tree = new DefaultMutableTreeNode(new Folder(0));
		tree.add(quickaccess);
		tree.add(rootTree);
	}
	
	public void setListQuick()
	{
		try {
			listquick = new LinkedList<Element>();
			FileReader fr = new FileReader(URL.url + URL.urlQuickAccess);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				listquick.add(((Folder)root).searchFolder((Folder)root, Integer.parseInt(line)));
			}
			System.out.println("Đọc file QuickAccess thành công");
			fr.close();
			br.close();
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Loi doc file: " + ex);
		}
	}
	
	public void setQuickAccess()
	{
		Folder folder = new Folder(0, "Truy cập nhanh");
		folder.setIcon(flash16);
		quickaccess = new DefaultMutableTreeNode(folder);
		for(Element e : listquick)
		{
			DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(e);
			quickaccess.add(treenode);
		}
	}
	
	public void setRootTree() {
		rootTree = addNodeTree(rootTree, root);
	}

	public void Update()
	{
		this.setQuickAccess();
		this.setRootTree();
		this.setTree();
		this.setModel(new DefaultTreeModel(tree));
	}
	
	public DefaultMutableTreeNode addNodeTree(DefaultMutableTreeNode rootTree, Element root)
	{
		rootTree = new DefaultMutableTreeNode(root);
		for(int i = 0; i < root.getChildrents().size(); i++) {
			if(root.getChildrents().get(i).getClass().equals(Folder.class))
			{
				DefaultMutableTreeNode node = new DefaultMutableTreeNode();
				if(root.getChildrents().size() > 0)
					node = addNodeTree(node, root.getChildrents().get(i));
				else
					node = new DefaultMutableTreeNode(root.getChildrents().get(i));
				rootTree.add(node);
			}
		}
		return rootTree;
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
		close.setIcon(new ImageIcon(libary.URL.url + URL.urlContentLeft + iconClose24));
		setIconClose(24);
	}

	public void exitClose() {
		close.setIcon(new ImageIcon(libary.URL.url + URL.urlContentLeft + iconClose16));
		setIconClose(18);
	}

	public void closeClick() {
		pc.ClosePanelLeft();
	}
}