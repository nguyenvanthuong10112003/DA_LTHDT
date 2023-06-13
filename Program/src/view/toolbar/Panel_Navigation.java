package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.LinkedList;

import libary.ColorList;
import libary.FONT;
import libary.URL;
import model.Element;
import model.Folder;
import view.screen.Screen;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controller.mouse;
import javax.swing.event.EventListenerList;
public class Panel_Navigation extends JPanel {
	private JLabel iconback;
	private JLabel iconforward;
	private String back = "\\Icon\\toolbar\\navi\\back16.png";
	private String forward = "\\Icon\\toolbar\\navi\\forward16.png";
	private String folder = "\\Icon\\toolbar\\navi\\folder.png";
	private mouse mouselisten;
	private JPanel address;
	private JComboBox input_show;
	private JComboBox search;
	private JLabel icon;
	private Boolean in_input = false;
	private Screen screen;
    private Element root;
    private Element nows;
    //private LinkedList<String>
	public Panel_Navigation(Screen screen, Element root) {
		super();
		this.screen = screen;
		this.root = root;
		this.nows = null;
		this.setBorder(new LineBorder(Color.black));
		this.init();
		this.EditObj();
		this.setFonts();
		this.addEvent();
		this.addObj();
		//AutoCompleteDecorator.decorate(input_show);
	}
 
	private void init() {
		try {
			mouselisten = new mouse(this);
			iconback = new JLabel(new ImageIcon(libary.URL.url + back));
			iconback.setToolTipText("back");
			iconforward = new JLabel(new ImageIcon(libary.URL.url + forward));
			iconforward.setToolTipText("forward");
			address = new JPanel();
			input_show = new JComboBox();
			input_show.addItem("/");
			input_show.addItem("/This pc");
			search = new JComboBox();
			search.addItem("Tìm kiếm");
			icon = new JLabel();
			System.out.println("Tải thành công thanh tìm kiếm của ToolBar");
		} catch (Exception e) {
			System.out.println("Error tìm kiếm của ToolBar");
		}
	}

	private void setComboBoxShow(Element e)
	{
		String location = "";
		if(e != null) {
		    location = e.getLocation() + e.getName();
					
		}
		else {
			location = "/";
		}
		input_show.addItem(location);
		if(e.getClass().equals(model.File.class))
			return;
		for(Element el : e.getChildrents())
			setComboBoxShow(el);
	}
	
	private void EditObj() {
		iconback.setOpaque(true);
		iconforward.setOpaque(true);
		iconback.setBackground(ColorList.Back_Ground);
		iconforward.setBackground(ColorList.Back_Ground);

		address.setOpaque(true);
		address.setBackground(ColorList.Back_Ground);
		address.setBorder(new LineBorder(ColorList.Fore_Ground));
		input_show.setEditable(true);
		input_show.setBorder(new LineBorder(ColorList.Back_Ground));
		search.setOpaque(true);
		search.setBorder(new LineBorder(Color.BLACK));
		search.setEditable(true);
		
		iconback.setEnabled(false);
		iconforward.setEnabled(false);
	}

	private void setFonts() {
		input_show.setFont(FONT.font_mac_dinh);
		search.setFont(FONT.font_mac_dinh);
	}

	private void addEvent() {
		iconback.addMouseListener(mouselisten);
		iconback.addMouseMotionListener(mouselisten);
		iconforward.addMouseListener(mouselisten);
		iconforward.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		input_show.addMouseListener(mouselisten);
		search.addMouseListener(mouselisten);
		input_show.getComponents()[0].setVisible(false);
		input_show.getComponents()[2].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				((JTextField)input_show.getComponents()[2]).setText("");
			}
		});
		input_show.getComponents()[2].addKeyListener(new KeyListener() {
			private Element cn;
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(input_show.isPopupVisible())
					if(e.getKeyCode() == 40 || e.getKeyCode() == 38)
					{
						if(e.getKeyCode() == 40)
						{

						}
						return;
					}
				String text = ((JTextField)input_show.getComponents()[2]).getText();
				String old = text;
				if(text.equals(""))
					return;
				input_show.removeAllItems();
				if(old.length() == 0)
					old = "";
				if(text.length() > 0)
					while(old.charAt(old.length() - 1) != '/') {
						if(old.length() > 1)
							old = old.substring(0, old.length() - 1);
						else
							break;
					}
				if(old.length() == 0)
					old = "";
				if(old.length() > 0)
					if(old.charAt(old.length() - 1) != '/')
						old = "";
				LinkedList<String> kq = null;
				if(!old.equals("") && old.charAt(0) == '/') {
					kq = checkLocation(text);
					cn = root.searchElement(1, kq.size() - 2, kq);
					if(cn != null)
					{
						if(!kq.getLast().equals(cn.getName()))
						{
							if(cn.getClass().equals(Folder.class)) {
								if(cn.getChildrents().size() > 0)
									for(Element child : cn.getChildrents())
									{
										if(!child.getName().equals(kq.getLast())) {
											if(child.getName().contains(kq.getLast()))
												input_show.addItem(old + child.getName());
										}
										else 
											input_show.addItem(old + child.getName());
									}
								else
									input_show.addItem(old);
							}
						}
						else
						{
							input_show.addItem(old + root.getName());
						}
					}
					else if(kq.size() == 2)
					{
						if(root.getName().contains(kq.getLast()))
							input_show.addItem(old + root.getName());
					}
					input_show.setPopupVisible(false);
					if(input_show.getItemCount() > 0)
						input_show.setPopupVisible(true);
					input_show.setSelectedIndex(-1);
				}
				((JTextField)input_show.getComponents()[2]).setText(text);
				if(e.getKeyCode() == 10)
				{
					if(text.charAt(text.length() - 1) == '/')
					{
						if(input_show.getItemCount() == 0) {
							JOptionPane.showConfirmDialog(screen, "Folder này không tồn tại!", "Thông báo", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
							setNows(nows);
						}
						else
						{
							if(cn != null) {
								setNows(cn);
								screen.setNowsCenter(cn);
							} else
							{
								setNows(null);
								screen.setNowsCenter(null);
							}
							
						}
					}
					else
					{
						if(input_show.getItemCount() == 0) {
							JOptionPane.showConfirmDialog(screen, "Folder/File này không tồn tại!", "Thông báo", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
							setNows(nows);
						}
						else
						{
							System.out.println(cn.getName());
							if(cn != null) {
								if(cn.getName().equals(kq.getLast())) {
									setNows(cn);
									screen.setNowsCenter(cn);
								}
								else
									for(Element child : cn.getChildrents())
										if(text.equals(old + child.getName()))
											if(child.getClass().equals(Folder.class))
											{
												setNows(child);
												screen.setNowsCenter(child);
											}
											else {
												JOptionPane.showConfirmDialog(screen, "Chức năng chưa phát triển.\nKhông thể mở File.", "Thông báo", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
												setNows(nows);
											}
							}
							else
							{
								setNows(null);
								screen.setNowsCenter(null);
							}
						}
					}
					input_show.setPopupVisible(false);
					return;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	public LinkedList<String>checkLocation(String text)
	{
		LinkedList<String> kq = new LinkedList<>();
		if(text.equals(""))
			return kq;
		if(text.charAt(0) != '/')
			return kq;
		int z = 0;
		for(int i = 0; i < text.length(); i++)
			if(text.charAt(i) == '/')
			{
				kq.add(text.substring(z, i));
				z = i + 1;
			}
		kq.add(text.substring(z, text.length()));
		return kq;
	}
	
	public void mouseOnclick(int hash) {
		if (hash == input_show.hashCode()) {
			if (!in_input) {
				in_input = true;
			}
		} else if (hash == search.hashCode()) {
			if (input_show.isEditable()) {
				in_input = false;
			}
		}
	}

	private void addObj() {
		this.setLayout(new BorderLayout());

		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEFT));
		pn.add(iconback, BorderLayout.WEST);
		pn.add(iconforward);
		pn.setOpaque(true);
		pn.setBackground(ColorList.Back_Ground);
		this.add(pn, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 4, 2, 4));
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		panel.setOpaque(true);
		panel.setBackground(ColorList.Back_Ground);
		address.setLayout(new BorderLayout());
		address.add(icon, BorderLayout.WEST);
		address.add(input_show);
		panel.add(address);
		panel.add(search);

		this.add(panel, BorderLayout.CENTER);
	}

	private void EditSize() {

	}

	public void setColorIcon(int hash, Color color) {
		if (iconback.hashCode() == hash) {
			if (iconback.isEnabled())
				iconback.setBackground(color);
		} else if (iconforward.hashCode() == hash) {
			if (iconforward.isEnabled())
				iconforward.setBackground(color);
		}
	}
	
	public void setEnableBack(Boolean bool)
	{
		if(bool == false)
			iconback.setBackground(ColorList.Back_Ground);
		iconback.setEnabled(bool);
	}
	
	public void setEnableForward(Boolean bool)
	{
		if(bool == false)
			iconforward.setBackground(ColorList.Back_Ground);
		iconforward.setEnabled(bool);
	}
	
	public void Onclick(int hash)
	{
		if(hash == iconback.hashCode() && iconback.isEnabled())
		{
			back();
		}
		else if(hash == iconforward.hashCode() && iconforward.isEnabled())
		{
			forward();
		}
	}
	
	public void back()
	{
		screen.Back();
	}
	
	public void forward()
	{
		screen.Forward();
	}
	
	public void setNows(Element e)
	{
		nows = e;
		String location = "";
		if(e != null) {
			icon.setIcon(new ImageIcon(URL.url + URL.urlContentLeftFolder + e.getIcon() + "16px.png"));
			if(e.getChildrents().size() > 0)
				location = e.getChildrents().get(0).getLocation();
			else
				location = e.getLocation() + e.getName() + "/";
		}
		else {
			icon.setIcon(null);
			location = "/";
		}
		input_show.setSelectedItem(location);
	}
}
