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
import java.util.Set;
import java.util.TreeSet;
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

import org.w3c.dom.html.HTMLLabelElement;

import controller.Key;
import controller.mouse;
import define.ColorList;
import define.FONT;
import define.URL;

import javax.swing.event.EventListenerList;
import java.util.HashSet;
import java.util.Set;

public class Panel_Navigation extends JPanel {
	private JLabel iconback;
	private JLabel iconforward;
	private String back = "\\Icon\\toolbar\\navi\\back16.png";
	private String forward = "\\Icon\\toolbar\\navi\\forward16.png";
	private String folder = "\\Icon\\toolbar\\navi\\folder.png";
	private mouse mouselisten;
	private JPanel address;
	private JPanel search;
	private JComboBox input_location;
	private JComboBox input_search;
	private JLabel icon;
	private JLabel iconsearch;
	private Boolean in_input = false;
	private Screen screen;
	private Element root;
	private Element nows;
	private Element cn;
	private Key keyListen;
	private int location_down_up[] = { 0, 0 };
	private int search_down_up[] = { 0, 0 };
	private String iconsr = "search";
	private Element ele;
	
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
	}

	private void init() {
		try {
			mouselisten = new mouse(this);
			iconback = new JLabel(new ImageIcon(define.URL.url + back));
			iconback.setToolTipText("back");
			iconforward = new JLabel(new ImageIcon(define.URL.url + forward));
			iconforward.setToolTipText("forward");
			address = new JPanel();
			search = new JPanel();
			input_location = new JComboBox();
			input_search = new JComboBox();
			icon = new JLabel();
			iconsearch = new JLabel();
			keyListen = new Key(this);
			System.out.println("Upload success search");
		} catch (Exception e) {
			System.out.println("Error search");
		}
	}

	private void setComboBoxShow(Element e) {
		String location = "";
		if (e != null) {
			location = e.getLocation() + e.getName();
		} else {
			location = "/";
		}
		input_location.addItem(location);
		if (e.getClass().equals(model.File.class))
			return;
		for (Element el : e.getChildrents())
			setComboBoxShow(el);
	}

	private void EditObj() {
		iconback.setEnabled(false);
		iconback.setOpaque(true);
		iconback.setBackground(ColorList.Back_Ground);

		iconforward.setEnabled(false);
		iconforward.setOpaque(true);
		iconforward.setBackground(ColorList.Back_Ground);

		address.setOpaque(true);
		address.setBackground(ColorList.Back_Ground);
		address.setBorder(new LineBorder(ColorList.Fore_Ground));

		input_location.setEditable(true);
		input_location.setBorder(new LineBorder(ColorList.Back_Ground));
		input_location.getComponents()[0].setVisible(false);
		input_location.addItem("/");
		input_location.addItem("/This pc");

		input_search.getComponents()[0].setVisible(false);
		input_search.setBorder(new LineBorder(ColorList.Back_Ground));
		input_search.setEditable(true);
		input_search.addItem("Tìm kiếm");

		search.setBorder(new LineBorder(ColorList.Fore_Ground));

		iconsearch.setIcon(new ImageIcon(URL.url + URL.urlToolBarNavi + iconsr + "16px.png"));
		iconsearch.setOpaque(true);
		iconsearch.setBackground(ColorList.Back_Ground);
		iconsearch.setBorder(new EmptyBorder(0, 2, 0, 0));
	}

	private void setFonts() {
		input_location.setFont(FONT.font_mac_dinh);
		input_search.setFont(FONT.font_mac_dinh);
	}

	private void addEvent() {
		iconback.addMouseListener(mouselisten);
		iconback.addMouseMotionListener(mouselisten);
		iconforward.addMouseListener(mouselisten);
		iconforward.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		input_location.addMouseListener(mouselisten);
		input_search.addMouseListener(mouselisten);
		input_location.getComponents()[2].addKeyListener(keyListen);
		input_search.getComponents()[2].addKeyListener(keyListen);
		input_location.getComponents()[2].addMouseListener(mouselisten);
		input_search.getComponents()[2].addMouseListener(mouselisten);
		input_location.getComponents()[2].addKeyListener(keyListen);
	}

	public void eventKeySearchLocation(int code, char key) {
		if (input_location.isPopupVisible())
			if (code == 40 || code == 38) {
				if (code == 40) {
					if (input_location.getSelectedIndex() == input_location.getItemCount() - 1) {
						if (location_down_up[1] > 0) {
							input_location.setSelectedIndex(0);
							location_down_up[1] = 0;
						} else
							location_down_up[1]++;
					}
				} else {
					if (input_location.getSelectedIndex() == 0) {
						if (location_down_up[0] > 0) {
							input_location.setSelectedIndex(input_location.getItemCount() - 1);
							location_down_up[0] = 0;
						} else
							location_down_up[0]++;
					}
				}
				return;
			}
		String text = ((JTextField) input_location.getComponents()[2]).getText();
		String old = text;
		if (text.equals(""))
			return;
		input_location.removeAllItems();
		if (old.length() == 0)
			old = "";
		if (text.length() > 0)
			while (old.charAt(old.length() - 1) != '/') {
				if (old.length() > 1)
					old = old.substring(0, old.length() - 1);
				else
					break;
			}
		if (old.length() == 0)
			old = "";
		if (old.length() > 0)
			if (old.charAt(old.length() - 1) != '/')
				old = "";
		LinkedList<String> kq = null;
		if (!old.equals("") && old.charAt(0) == '/') {
			kq = checkLocation(text);
			cn = root.searchElement(1, kq.size() - 2, kq);
			if (cn != null) {
				if (!kq.getLast().equals(cn.getName())) {
					if (cn.getClass().equals(Folder.class)) {
						if (cn.getChildrents().size() > 0)
							for (Element child : cn.getChildrents()) {
								if (!child.getName().equals(kq.getLast())) {
									if (child.getName().contains(kq.getLast()))
										input_location.addItem(old + child.getName());
								} else
									input_location.addItem(old + child.getName());
							}
						else
							input_location.addItem(old);
					}
				} else {
					input_location.addItem(old + root.getName());
				}
			} else if (kq.size() == 2) {
				if (root.getName().contains(kq.getLast()))
					input_location.addItem(old + root.getName());
			}
			input_location.setPopupVisible(false);
			if (input_location.getItemCount() > 0)
				input_location.setPopupVisible(true);
			input_location.setSelectedIndex(-1);
		}
		((JTextField) input_location.getComponents()[2]).setText(text);
		if (code == 10) {
			if (text.charAt(text.length() - 1) == '/') {
				if (input_location.getItemCount() == 0) {
					JOptionPane.showConfirmDialog(screen, "Folder này không tồn tại!", "Thông báo",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
					setNows(nows);
				} else {
					if (cn != null) {
						setNows(cn);
						screen.setNowsCenter(cn);
					} else {
						setNows(null);
						screen.setNowsCenter(null);
					}

				}
			} else {
				if (input_location.getItemCount() == 0) {
					JOptionPane.showConfirmDialog(screen, "Folder/File này không tồn tại!", "Thông báo",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
					setNows(nows);
				} else {
					if (cn != null) {
						if (cn.getName().equals(kq.get(kq.size() - 2))) {
							for (Element child : cn.getChildrents())
								if (text.equals(old + child.getName()))
									if (child.getClass().equals(Folder.class)) {
										setNows(child);
										screen.setNowsCenter(child);
									} else {
										JOptionPane.showConfirmDialog(screen,
												"Chức năng chưa phát triển.\nKhông thể mở File.", "Thông báo",
												JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
										setNows(nows);
									}
						} else if (cn.getName().equals(kq.getLast())) {
							setNows(cn);
							screen.setNowsCenter(cn);
						}
					} else {
						setNows(null);
						screen.setNowsCenter(null);
					}
				}
			}
			input_location.setPopupVisible(false);
			return;
		}
	}

	public void eventKeySearch(int code, char key) {
		String text = ((JTextField) input_search.getComponents()[2]).getText();
		if (code == 40 || code == 38) {
			if (code == 40) {
				if (input_search.getSelectedIndex() == input_search.getItemCount() - 1) {
					if (search_down_up[1] > 0) {
						input_search.setSelectedIndex(0);
						search_down_up[1] = 0;
					} else
						search_down_up[1]++;
				}
			} else {
				if (input_search.getSelectedIndex() == 0) {
					if (search_down_up[0] > 0) {
						input_search.setSelectedIndex(input_search.getItemCount() - 1);
						search_down_up[0] = 0;
					} else
						search_down_up[0]++;
				}
			}
			return;
		}
		if (code == 10) {
			if(input_search.getSelectedIndex() >= 0)
			{
				Element nows = new Folder(-1);
				nows.getChildrents().add(ele.getChildrents().get(input_search.getSelectedIndex()));
				screen.setNowsCenter(nows);
			}
			else
				screen.setNowsCenter(ele);
			input_search.setPopupVisible(false);
			return;
		}
		input_search.removeAllItems();
		LinkedList<Element> set = new LinkedList();
		ele = new Folder(-1);
		setList(set, root, text);
		for (Element pl : set) {
			input_search.addItem(pl.getName() + " - " + pl.getLocation() + pl.getName());
			ele.getChildrents().add(pl);
		}
		input_search.setPopupVisible(false);
		if (set.size() > 0)
			input_search.setPopupVisible(true);
		input_search.setSelectedIndex(-1);
		((JTextField) input_search.getComponents()[2]).setText(text);
	}

	public void setList(LinkedList<Element> list, Element e, String text) {
		if (e.getName().contains(text))
			list.add(e);
		if (e.getClass().equals(Folder.class)) {
			if (e.getChildrents().size() > 0) {
				for (Element child : e.getChildrents())
					setList(list, child, text);
			}
		}
	}

	public LinkedList<String> checkLocation(String text) {
		LinkedList<String> kq = new LinkedList<String>();
		if (text.equals(""))
			return kq;
		if (text.charAt(0) != '/')
			return kq;
		int z = 0;
		for (int i = 0; i < text.length(); i++)
			if (text.charAt(i) == '/') {
				kq.add(text.substring(z, i));
				z = i + 1;
			}
		kq.add(text.substring(z, text.length()));
		return kq;
	}

	public void mouseOnclick(int hash) {
		if (hash == input_location.hashCode()) {
			if (!in_input) {
				in_input = true;
			}
		} else if (hash == input_search.hashCode()) {
			if (input_location.isEditable()) {
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
		address.add(input_location);
		search.setLayout(new BorderLayout());
		search.add(input_search, BorderLayout.CENTER);
		search.add(iconsearch, BorderLayout.WEST);
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

	public void setEnableBack(Boolean bool) {
		if (bool == false)
			iconback.setBackground(ColorList.Back_Ground);
		iconback.setEnabled(bool);
	}

	public void setEnableForward(Boolean bool) {
		if (bool == false)
			iconforward.setBackground(ColorList.Back_Ground);
		iconforward.setEnabled(bool);
	}

	public void Onclick(int hash) {
		if (hash == iconback.hashCode() && iconback.isEnabled()) {
			back();
		} else if (hash == iconforward.hashCode() && iconforward.isEnabled()) {
			forward();
		}
		if (hash == input_search.getComponents()[2].hashCode()) {
			if (((JTextField) input_search.getComponents()[2]).getText().equals("Tìm kiếm")) {
				((JTextField) input_search.getComponents()[2]).setText("");
				input_search.removeAllItems();
			}
		}
	}

	public void back() {
		screen.Back();
	}

	public void forward() {
		screen.Forward();
	}

	public void setNows(Element e) {
		nows = e;
		String location = "";
		if (e != null) {
			icon.setIcon(new ImageIcon(URL.url + URL.urlContentLeftFolder + e.getIcon() + "16px.png"));
			if (e.getChildrents().size() > 0)
				location = e.getChildrents().get(0).getLocation();
			else
				location = e.getLocation() + e.getName() + "/";
		} else {
			icon.setIcon(null);
			location = "/";
		}
		input_location.setSelectedItem(location);
	}

	public JComboBox getInput_Location() {
		return input_location;
	}

	public JComboBox getInput_Search() {
		return input_search;
	}
}
