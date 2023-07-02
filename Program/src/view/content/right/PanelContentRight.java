package view.content.right;

import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controller.mouse;
import define.ColorList;
import define.FONT;
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
	private JLabel save;
	private String px = "32px";
	private String duoi = ".png";
	private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private JPanel panel;
	private Element select;

	public PanelContentRight(PanelContent pc) {
		super();
		try {
			this.pc = pc;
			this.init();
			this.addEvent();
			this.Edit();
			this.addObj();
			this.setFont();
			System.out.println("Upload succrss content right");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Content right");
		}
	}

	public void init() {
		mouselisten = new mouse(this, pc);
		close = new JLabel(new ImageIcon(define.URL.url + iconClose16));
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
		save = new JLabel("Lưu");
	}

	public void addEvent() {
		this.addMouseMotionListener(mouselisten);
		this.setBorder(new LineBorder(Color.black));
		close.addMouseMotionListener(mouselisten);
		close.addMouseListener(mouselisten);
		save.addMouseListener(mouselisten);
		save.addMouseMotionListener(mouselisten);
	}

	public void Edit() {
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
		scrollName.setBounds(iconContent.getBounds().width + 10, 10 + iconContent.getHeight() / 2 - 17,
				panel.getSize().width - iconContent.getSize().width - 15, 35);
		scrollName.setBorder(new LineBorder(ColorList.Fore_Ground));
		scrollName.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollName.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		nameContent.setFont(FONT.font_mac_dinh);
		locationText.setBounds(5, iconContent.getHeight() + iconContent.getBounds().y + 10, 55, 20);
		scrollLocation.setBounds(locationText.getBounds().width + 10, locationText.getBounds().y,
				panel.getSize().width - locationText.getSize().width - 15, 30);
		typeText.setBounds(5, locationText.getHeight() + locationText.getBounds().y + 10, 55, 20);
		type.setBounds(typeText.getBounds().width + 10, typeText.getBounds().y,
				panel.getSize().width - typeText.getSize().width - 15, 20);
		sizeText.setBounds(5, typeText.getHeight() + typeText.getBounds().y + 5, 55, 20);
		size.setBounds(sizeText.getBounds().width + 10, sizeText.getBounds().y,
				panel.getSize().width - sizeText.getSize().width - 15, 20);
		if (containText.isVisible()) {
			containText.setBounds(5, sizeText.getHeight() + sizeText.getBounds().y + 5, 55, 20);
			contain.setBounds(containText.getBounds().width + 10, containText.getBounds().y,
					panel.getSize().width - containText.getSize().width - 15, 20);
		}
		createdText.setBounds(5,
				(containText.isVisible() == true ? (containText.getHeight() + containText.getBounds().y)
						: (sizeText.getHeight() + sizeText.getBounds().y)) + 5,
				55, 20);
		create.setBounds(createdText.getBounds().width + 10, createdText.getBounds().y,
				panel.getSize().width - createdText.getSize().width - 15, 20);
		if (modifieldText.isVisible()) {
			modifieldText.setBounds(5, createdText.getHeight() + createdText.getBounds().y + 5, 55, 20);
			modifield.setBounds(modifieldText.getBounds().width + 10, modifieldText.getBounds().y,
					panel.getSize().width - modifieldText.getSize().width - 15, 20);
		}
		save.setBounds(panel.getSize().width - 55,
				(modifield.isVisible() == true ? modifield.getSize().height + modifield.getBounds().y
						: create.getSize().height + create.getBounds().y) + 5,
				50, 30);
		save.setFont(FONT.font_IN_DAM);
		save.setForeground(ColorList.Back_Ground);
		save.setOpaque(true);
		save.setBackground(ColorList.Fore_Ground);
		save.setHorizontalAlignment(JLabel.CENTER);
	}

	public void setFont() {
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

	public void addObj() {
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
		panel.add(save);
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

	public void selected(Element e) {
		select = e;
		if (e.getClass().equals(Folder.class)) {
			contain.setText("Folder(" + countFolder(e) + ")  File(" + countFile(e) + ")");
			create.setText(e.getTime(e.getDateCreate()));
			type.setText(e.getExName());
			iconContent.setIcon(new ImageIcon(define.URL.url + urlIconFolder + e.getIcon() + px + duoi));
			nameContent.setText(e.getName());
			containText.setVisible(!false);
			contain.setVisible(!false);
			modifieldText.setVisible(false);
			modifield.setVisible(false);
		} else {
			create.setText(e.getTime(e.getDateCreate()));
			modifield.setText(e.getTime(e.getDateModified()));
			type.setText(e.getExName());
			iconContent.setIcon(new ImageIcon(define.URL.url + urlIconFile + e.getIcon() + px + duoi));
			nameContent.setText(e.getName() + (e.getExType().equals("") == true ? "" : ".") + e.getExType());
			modifieldText.setVisible(!false);
			modifield.setVisible(!false);
			containText.setVisible(false);
			contain.setVisible(false);
		}
		location.setText(e.getLocation());
		size.setText(e.getSize(e) + "kb");
		Edit();
		panel.setVisible(true);
	}

	public void noselected() {
		select = null;
		panel.setVisible(false);
		containText.setVisible(false);
		contain.setVisible(false);
		modifieldText.setVisible(false);
		modifield.setVisible(false);
		pc.setFunSelectedTablie(false);
	}

	public JPanel getPanel() {
		return panel;
	}

	public int countFolder(Element root) {
		int n = 0;
		for (Element e : root.getChildrents())
			if (e.getClass().equals(Folder.class)) {
				n += 1 + countFolder(e);
			}
		return n;
	}

	public int countFile(Element root) {
		int n = 0;
		for (Element e : root.getChildrents())
			if (e.getClass().equals(Folder.class))
				n += countFile(e);
			else
				n++;
		return n;
	}

	public JLabel getSave() {
		return save;
	}

	public void hoverSave() {
		save.setBackground(ColorList.Back_Ground);
		save.setForeground(ColorList.Fore_Ground);
		save.setBorder(new LineBorder(ColorList.Fore_Ground));
	}

	public void exitSave() {
		save.setForeground(ColorList.Back_Ground);
		save.setBackground(ColorList.Fore_Ground);
		save.setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public void clickedSave() throws IOException, ClassNotFoundException {
		if (select.getParent() == null) {
			return;
		}
		String s = nameContent.getText();
		while (s.charAt(0) == ' ')
			s = s.substring(1, s.length());
		while (s.charAt(s.length() - 1) == ' ')
			s = s.substring(0, s.length() - 1);
		for (int i = 0; i < s.length();) {
			if (s.charAt(i) == '\n')
				s = s.substring(0, i) + s.substring(i + 1, s.length());
			else
				i++;
		}

		if (s.equals("") || s.equals(select.getName())) {
			nameContent.setText(select.getName());
			return;
		}

		for (Element e : select.getParent().getChildrents())
			if (e.getName().equals(s) && !e.equals(select)) {
				nameContent.setText(select.getName());
				JOptionPane.showMessageDialog(typeText, "Tên đã tồn tại!\nHãy đặt tên khác.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

		if (select.getClass().equals(model.File.class)) {
			int in = -1;
			for (int i = s.length() - 1; i >= 0; i--)
				if (s.charAt(i) == '.') {
					in = i;
					break;
				}
			if (in != -1) {
				select.setExType(s.substring(in + 1, s.length()));
				s = s.substring(0, in);
				while (s.charAt(s.length() - 1) == ' ')
					s = s.substring(0, s.length() - 1);
				if (!s.equals("")) {
					for (Element e : select.getParent().getChildrents())
						if (e.getName().equals(s) && !e.equals(select)) {
							nameContent.setText(select.getName());
							JOptionPane.showMessageDialog(typeText, "Tên đã tồn tại!\nHãy đặt tên khác.", "Thông báo",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					iconContent.setIcon(new ImageIcon(define.URL.url + urlIconFile + select.getIcon() + px + duoi));
				} else
					return;
			}
		}
		type.setText(select.getExName());
		select.setName(s);
		pc.getCenter().Update();
		pc.UpdateLeft();
		if (!pc.isLogin())
			pc.getCenter().ghiFile();
		else
			pc.getCenter().updateDB(select);
	}
	
	public Element getSelect()
	{
		return select;
	}

}
