package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import libary.ColorList;
import libary.FONT;
import libary.URL;
import model.Element;
import model.Folder;
import view.screen.Screen;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import controller.mouse;

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
		this.setComboBoxShow(root);
		AutoCompleteDecorator.decorate(input_show);
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
			if(e.getClass().equals(Folder.class)) {
				if(e.getChildrents().size() > 0)
					location = e.getChildrents().get(0).getLocation();
				else
					location = e.getLocation() + e.getName() + "/";
			}
		
			else
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
