package view.content.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controller.mouse;
import libary.ColorList;
import libary.FONT;
import model.Element;
import model.Folder;
import view.content.PanelContent;

public class PanelContentRight extends JPanel {
	private PanelContent pc;
	private JLabel close;
	private String iconClose16 = "\\Icon\\content\\right\\close16.png";
	private String iconClose24 = "\\Icon\\content\\right\\close24.png";
	private mouse mouselisten;
	private Cursor cursorResize = new Cursor(Cursor.W_RESIZE_CURSOR);
	private JLabel iconContent;
	private JScrollPane scrollName;
	private JTextArea nameContent;
	private JLabel locationText;
	private JTextArea location;
	private JScrollPane scrollLocation;
	private JLabel sizeText;
	private JLabel size;
	private JLabel containText;
	private JLabel contain;
	private JLabel createdText;
	private JLabel create;
	private JLabel modifieldText;
	private JLabel modifield;
	private JLabel typeText;
	private JLabel type;
	private String url;
	private String px = "32px";
	private String duoi = ".png";
	private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private JPanel panel;
	public PanelContentRight(PanelContent pc, String url) {
		super();
		try {
			this.pc = pc;
			this.url = url;
            this.init();
            this.addEvent();
            this.Edit();
			this.addObj();
			this.setFont();
			System.out.println("Tải thành công content bên phải");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Content bên phải");
		}
	}

	public void init()
	{
		mouselisten = new mouse(this, pc);
		close = new JLabel(new ImageIcon(url + iconClose16));
		iconContent = new JLabel();
		nameContent = new JTextArea();
	    locationText = new JLabel("Location:");
		location = new JTextArea();
		sizeText = new JLabel("Size:");
		size = new JLabel();
		containText = new JLabel("Contains:");
		contain = new JLabel();
		createdText = new JLabel("Created:");
		create = new JLabel();
		modifieldText = new JLabel("Modified:");
		modifield = new JLabel();
        type = new JLabel();
        typeText = new JLabel("Type:");
        panel = new JPanel();
        scrollName = new JScrollPane();
        scrollLocation = new JScrollPane();
		panel.setVisible(false);
		containText.setVisible(false);
		contain.setVisible(false);
		modifieldText.setVisible(false);
		modifield.setVisible(false);
	}
	
	
	public void addEvent()
	{
		this.addMouseMotionListener(mouselisten);
		this.setBorder(new LineBorder(Color.black));	
		close.addMouseMotionListener(mouselisten);
		close.addMouseListener(mouselisten);  
	}
	
	public void Edit()
	{
		close.setBorder(new EmptyBorder(0, 0, 0, 0));
		close.setOpaque(true);
		close.setBackground(Color.WHITE);
		close.setSize(20, 20);
		close.setBounds(1, 1, close.getSize().width, close.getSize().height);
		panel.setBorder(new LineBorder(ColorList.Fore_Ground));
		panel.setBounds(5, 21, this.getWidth() - 10, this.getHeight() - 26); 
		panel.setOpaque(true);
		panel.setBackground(ColorList.Back_Ground);
		location.setAutoscrolls(true);
		nameContent.setLineWrap(true);
		location.setLineWrap(true);
		location.setEditable(false);
		iconContent.setBounds(5, 10, 32, 32);
		scrollLocation.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollName.setBounds(iconContent.getBounds().width + 10, 10 + iconContent.getHeight()/2 - 17, 
				panel.getSize().width - iconContent.getSize().width - 15, 35);
		scrollName.setBorder(new LineBorder(ColorList.Fore_Ground));
		scrollName.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollName.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		nameContent.setFont(FONT.font_mac_dinh);
		locationText.setBounds(5, iconContent.getHeight() + iconContent.getBounds().y + 10, 50, 20);
		scrollLocation.setBounds(locationText.getBounds().width + 10, locationText.getBounds().y, 
				panel.getSize().width - locationText.getSize().width - 15, 30);
		typeText.setBounds(5, locationText.getHeight() + locationText.getBounds().y + 10, 50, 20);
		type.setBounds(typeText.getBounds().width + 10, typeText.getBounds().y, 
				panel.getSize().width - typeText.getSize().width - 15, 20);
		sizeText.setBounds(5, typeText.getHeight() + typeText.getBounds().y + 5, 50, 20);
		size.setBounds(sizeText.getBounds().width + 10, sizeText.getBounds().y, 
				panel.getSize().width - sizeText.getSize().width - 15, 20);
		if(containText.isVisible())
		{
			containText.setBounds(5, sizeText.getHeight() + sizeText.getBounds().y + 5, 50, 20);
			contain.setBounds(containText.getBounds().width + 10, containText.getBounds().y, 
					panel.getSize().width - containText.getSize().width - 15, 20);
		}
		createdText.setBounds(5, (containText.isVisible() == true ? (containText.getHeight() + containText.getBounds().y) : (sizeText.getHeight() + sizeText.getBounds().y)) + 5, 50, 20);
		create.setBounds(createdText.getBounds().width + 10, createdText.getBounds().y,
				panel.getSize().width - createdText.getSize().width - 15, 20);
	    if(modifieldText.isVisible())
	    {
	    	modifieldText.setBounds(5, createdText.getHeight() + createdText.getBounds().y + 5, 50, 20);
	    	modifield.setBounds(modifieldText.getBounds().width + 10, modifieldText.getBounds().y, 
					panel.getSize().width - modifieldText.getSize().width - 15, 20);
	    }
	}
	
	public void setFont()
	{
		location.setFont(FONT.font_mac_dinh);
		create.setFont(FONT.font_mac_dinh);
		modifield.setFont(FONT.font_mac_dinh);
		nameContent.setFont(FONT.font_mac_dinh);
		locationText.setFont(FONT.font_mac_dinh);
		type.setFont(FONT.font_mac_dinh);
		typeText.setFont(FONT.font_mac_dinh);
		size.setFont(FONT.font_mac_dinh);
		sizeText.setFont(FONT.font_mac_dinh);
		locationText.setFont(FONT.font_mac_dinh);
		createdText.setFont(FONT.font_mac_dinh);
		modifieldText.setFont(FONT.font_mac_dinh);
		containText.setFont(FONT.font_mac_dinh);
		contain.setFont(FONT.font_mac_dinh);
	}
	public void addObj()
	{
		this.setLayout(null);	
		this.add(close);
		this.add(panel);
		panel.setLayout(null);
		panel.add(iconContent);
		scrollName.setViewportView(nameContent);
		panel.add(scrollName);
		panel.add(locationText);
		panel.add(scrollLocation);
		scrollLocation.setViewportView(location);
		panel.add(sizeText);
		panel.add(size);
		panel.add(containText);
		panel.add(contain);
		panel.add(createdText);
		panel.add(create);
		panel.add(modifieldText);
		panel.add(modifield);
		panel.add(typeText);
		panel.add(type);
	}
	
	public int getWidth() {
		return this.getSize().width;
	}

	public int getHeight() {
		return this.getSize().height;
	}

	public JLabel getLabelIconClose() {
		return close;
	}

	public void hoverClose() {
		close.setBackground(ColorList.Hover);
	}

	public void exitClose() {
		close.setBackground(ColorList.Back_Ground);
	}

	public void closeClick() {
		pc.ClosePanelRight();
	}

	public void setCusor(int x, int y) {
		if (0 <= x && x <= 3) {
			this.setCursor(cursorResize);
		} else {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public void setSize(int x) {
		if (x - 2 <= 0 || x + 2 >= 0)
			this.setBounds(this.getBounds().x + x, this.getBounds().y,
					(this.getSize().width - x) > this.getMaximumSize().width ? this.getMaximumSize().width
							: ((this.getSize().width - x) >= this.getMinimumSize().width ? (this.getSize().width - x)
									: this.getMinimumSize().width),
					this.getSize().height);
		panel.setBounds(5, 21, this.getWidth() - 10, this.getHeight() - 26);
		pc.setBoundsObj(pc.getSize().width, pc.getSize().height, pc.getPanelContentLeft().getSize().width,
				pc.getPanelContentLeft().getSize().height, this.getSize().width);
	}

	public void setDefaultCursor() {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public Cursor cursorResize() {
		return cursorResize;
	}
	
	public void selected(Element e)
	{
		if(e.getClass().equals(Folder.class))
		{
			location.setText(searchLocation(e.getParent()));
			size.setText("aa");
			contain.setText("bb");
			create.setText(e.getTime(e.getDateCreate()));
			type.setText(e.getExName());
			iconContent.setIcon(new ImageIcon(url + urlIconFolder + e.getIcon() + px + duoi));
		    nameContent.setText(e.getName());
			containText.setVisible(!false);
			contain.setVisible(!false);
			Edit();
		}
		else
		{
			location.setText(searchLocation(e.getParent()));
			size.setText(e.getSize() + "kb");
			create.setText(e.getTime(e.getDateCreate()));
			modifield.setText(e.getTime(e.getDateModified()));
			type.setText(e.getExName());
			iconContent.setIcon(new ImageIcon(url + urlIconFile + e.getIcon() + px + duoi));
		    nameContent.setText(e.getName());
			iconContent.setVisible(!false);
			scrollName.setVisible(!false);
			nameContent.setVisible(!false);
			modifieldText.setVisible(!false);
			modifield.setVisible(!false);
			Edit();
		}

		panel.setVisible(true);
	}
	
	public void noselected()
	{
		panel.setVisible(false);
		containText.setVisible(false);
		contain.setVisible(false);
		modifieldText.setVisible(false);
		modifield.setVisible(false);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	public String searchLocation(Element e)
	{
		if(e == null)
		   return "/";
		return searchLocation(e.getParent()) + e.getName() + "/";
	}
}
