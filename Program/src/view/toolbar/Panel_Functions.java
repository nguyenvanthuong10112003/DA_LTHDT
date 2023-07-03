package view.toolbar;

import model.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controller.mouse;
import define.ColorList;
import define.FONT;
import define.URL;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.screen.Screen;
import controller.*;

public class Panel_Functions extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ScreenPageUser pageUser;
	private Screen screen;
	private JPanel content;
	private JLabel login;
	private JLabel register;
	private FormLogin Flogin;
	private FormRegister Fregister;
	private JPanel container_icon_function;
	private JLabel pin_to_access_icon;
	private JTextArea pinTo_text;
	private JLabel cut_icon;
	private JLabel copy_icon;
	private JLabel paste_icon;
	private JLabel move_to_icon;
	private JTextArea move_to_text;
	private JLabel copy_to_icon;
	private JTextArea copy_to_text;
	private JLabel delete_icon;
	private JLabel delete_text;
	private JLabel rename_icon;
	private JLabel rename_text;
	private JLabel new_folder_icon;
	private JLabel new_folder_text;
	private JLabel new_file_icon;
	private JLabel new_file_text;
	private JLabel select_all;
	private JLabel select_no;
	private JPanel PinTo;
	private JPanel MoveTo;
	private JPanel CopyTo;
	private JPanel Delete;
	private JPanel Rename;
	private JPanel NewFile;
	private JPanel NewFolder;
	private JPanel Select;
	private JLabel tdn;
	private JLabel dang_xuat;
	private Boolean islogin;
	private JLabel hello;
	private JButton button; 
	private mouse mouseListen;
	private action actionListen;
	private String pin_to = "pin_to_access_24.png";
	private String cut = "cut16.png";
	private String copy = "copy2_16.png";
	private String paste = "paste16.png";
	private String move_to = "move.png";
	private String copy_to = "copy_to32.png";
	private String delete = "delete24.png";
	private String rename = "rename32.png";
	private String new_folder = "newfolder24px.png";
	private String new_file = "newfile24px.png";
	private String select_all_icon = "selectall24.png";
	private String select_no_icon = "noselect24.png";
	private String threedots = "dots16px.png";
	
	public Panel_Functions(Screen screen, Boolean islogin) {
		super();
		try {
			this.screen = screen;
			this.islogin = islogin;
			init();
			setFont();
			setText();
			setColor();
			EditObj();
			addObj();
			addEvent();
			System.out.println("Upload success functions");
		} catch (Exception e) {
			System.out.println("Error functions!");
		}
	}

	private void setFont() {
		login.setFont(FONT.font_IN_DAM);
		register.setFont(FONT.font_IN_DAM);
		cut_icon.setFont(FONT.font_mac_dinh);
		copy_icon.setFont(FONT.font_mac_dinh);
		paste_icon.setFont(FONT.font_mac_dinh);
		copy_to_text.setFont(FONT.font_mac_dinh);
		move_to_text.setFont(FONT.font_mac_dinh);
		rename_text.setFont(FONT.font_mac_dinh);
		delete_text.setFont(FONT.font_mac_dinh);
		new_file_text.setFont(FONT.font_mac_dinh);
		new_folder_text.setFont(FONT.font_mac_dinh);
		pinTo_text.setFont(FONT.font_11);
		tdn.setFont(FONT.font_IN_DAM);
		dang_xuat.setFont(FONT.font_IN_DAM);
		hello.setFont(FONT.font_IN_DAM);
		select_no.setFont(FONT.font_mac_dinh);
		select_all.setFont(FONT.font_mac_dinh);
	}

	private void setColor() {
		this.setOpaque(true);
		this.setBackground(ColorList.Back_Ground);
		
		content.setOpaque(true);
		content.setBackground(ColorList.Back_Ground);
		
		container_icon_function.setOpaque(true);
		container_icon_function.setBackground(ColorList.Back_Ground);
		
		pinTo_text.setOpaque(true);
		pinTo_text.setBackground(ColorList.Back_Ground);
		pinTo_text.setForeground(ColorList.Fore_Ground);
		
		pin_to_access_icon.setOpaque(true);
		pin_to_access_icon.setBackground(ColorList.Back_Ground);
		pin_to_access_icon.setForeground(ColorList.Fore_Ground);
		
		cut_icon.setOpaque(true);
		cut_icon.setBackground(ColorList.Back_Ground);
		cut_icon.setForeground(ColorList.Fore_Ground);
		
		copy_icon.setOpaque(true);
		copy_icon.setBackground(ColorList.Back_Ground);
		copy_icon.setForeground(ColorList.Fore_Ground);
		
		paste_icon.setOpaque(true);
		paste_icon.setBackground(ColorList.Back_Ground);
		paste_icon.setForeground(ColorList.Fore_Ground);
		
		copy_to_text.setOpaque(true);
		copy_to_text.setBackground(ColorList.Back_Ground);
		copy_to_text.setForeground(ColorList.Fore_Ground);
		
		copy_to_icon.setOpaque(true);
		copy_to_icon.setBackground(ColorList.Back_Ground);
		copy_to_icon.setForeground(ColorList.Fore_Ground);
		
		move_to_text.setOpaque(true);
		move_to_text.setBackground(ColorList.Back_Ground);
		move_to_text.setForeground(ColorList.Fore_Ground);
		
		move_to_icon.setOpaque(true);
		move_to_icon.setBackground(ColorList.Back_Ground);
		move_to_icon.setForeground(ColorList.Fore_Ground);
		
		rename_icon.setOpaque(true);
		rename_icon.setBackground(ColorList.Back_Ground);
		rename_icon.setForeground(ColorList.Fore_Ground);
		
		rename_text.setOpaque(true);
		rename_text.setBackground(ColorList.Back_Ground);
		rename_text.setForeground(ColorList.Fore_Ground);
		
		delete_icon.setOpaque(true);
		delete_icon.setBackground(ColorList.Back_Ground);
		delete_icon.setForeground(ColorList.Fore_Ground);
		
		delete_text.setOpaque(true);
		delete_text.setBackground(ColorList.Back_Ground);
		delete_text.setForeground(ColorList.Fore_Ground);
		
		new_file_icon.setOpaque(true);
		new_file_icon.setBackground(ColorList.Back_Ground);
		new_file_icon.setForeground(ColorList.Fore_Ground);
		
		new_file_text.setOpaque(true);
		new_file_text.setBackground(ColorList.Back_Ground);
		new_file_text.setForeground(ColorList.Fore_Ground);
		
		new_folder_icon.setOpaque(true);
		new_folder_icon.setBackground(ColorList.Back_Ground);
		new_folder_icon.setForeground(ColorList.Fore_Ground);
		
		new_folder_text.setOpaque(true);
		new_folder_text.setBackground(ColorList.Back_Ground);
		new_folder_text.setForeground(ColorList.Fore_Ground);
		
		NewFile.setOpaque(true);
		NewFile.setBackground(ColorList.Back_Ground);
		
		NewFolder.setOpaque(true);
		NewFolder.setBackground(ColorList.Back_Ground);
		
		tdn.setOpaque(true);
		tdn.setBackground(ColorList.Back_Ground);
		tdn.setForeground(ColorList.Fore_Ground);
		
		dang_xuat.setBackground(Color.BLUE);
		dang_xuat.setForeground(ColorList.Fore_Ground);
		
		hello.setOpaque(true);
		hello.setBackground(ColorList.Back_Ground);
		hello.setForeground(ColorList.Fore_Ground);
		
		select_all.setOpaque(true);
		select_all.setBackground(ColorList.Back_Ground);
		select_all.setForeground(ColorList.Fore_Ground);
		
		select_no.setOpaque(true);
		select_no.setBackground(ColorList.Back_Ground);
		select_no.setForeground(ColorList.Fore_Ground);
		
		Select.setOpaque(true);
		Select.setBackground(ColorList.Back_Ground);
		
		button.setBackground(ColorList.Back_Ground);
	}

	private void init() {
		mouseListen = new mouse(this);
		actionListen = new action(this);
		container_icon_function = new JPanel();
		content = new JPanel();
		login = new JLabel();
		register = new JLabel();
		copy_to_text = new JTextArea();
		move_to_text = new JTextArea();
		rename_text = new JLabel();
		delete_text = new JLabel();
		new_file_text = new JLabel();
		new_folder_text = new JLabel();
		pinTo_text = new JTextArea();
		PinTo = new JPanel();
		MoveTo = new JPanel();
		CopyTo = new JPanel();
		Delete = new JPanel();
		Rename = new JPanel();
		NewFile = new JPanel();
		NewFolder = new JPanel();
		tdn = new JLabel();
		dang_xuat = new JLabel();
		hello = new JLabel();
		Select = new JPanel();
		button = new JButton();
		pin_to_access_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + pin_to));
		cut_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + cut));
		copy_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + copy));
		paste_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + paste));
		move_to_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + move_to));
		copy_to_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + copy_to));
		delete_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + delete));
		rename_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + rename));
		new_folder_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + new_folder));
		new_file_icon = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + new_file));
		select_no = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + select_no_icon));
		select_all = new JLabel(new ImageIcon(define.URL.url + URL.urlToolBarFun + select_all_icon));
	}

	private void setText() {
		login.setText("     Đăng nhập     ");
		register.setText("      Đăng ký      ");
		pinTo_text.setText("    Ghim vào \ntruy cập nhanh");
		pin_to_access_icon.setToolTipText("Thêm vào truy cập nhanh");
		cut_icon.setText("Cắt");
		copy_icon.setText("Sao chép");
		paste_icon.setText("Dán");
		copy_to_text.setText("Sao chép\r\n    đến");
		move_to_text.setText("Di chuyển\r\n     đến");
		rename_text.setText("Đổi tên");
		delete_text.setText("Xóa");
		new_file_text.setText("Tệp mới");
		new_folder_text.setText("Thư mục mới");
		dang_xuat.setText("     Đăng xuất     ");
		hello.setText("Xin chào,");
		select_all.setText("Chọn tất cả");
		select_no.setText("Bỏ chọn");
		if (Screen.getUser() != null)
			tdn.setText(Screen.getUser().getName());
	}

	private void addObj() {
		this.setLayout(new BorderLayout());
		if (!islogin) {
			content.setLayout(new GridLayout(2, 1, 5, 5));
			content.add(login);
			content.add(register);
			content.setBorder(new EmptyBorder(5, 5, 5, 5));
		} else {
			content.setLayout(new GridLayout(2, 1, 2, 2));
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2,1,0,0));
			panel.add(hello);
			panel.add(tdn);
			content.add(panel);
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(dang_xuat, BorderLayout.CENTER);
			panel.add(button, BorderLayout.EAST);
			content.add(panel);
			content.setBorder(new EmptyBorder(3, 3, 3, 3));
		}

		container_icon_function.setLayout(new FlowLayout(FlowLayout.LEFT));

		PinTo.setLayout(new GridLayout(2, 1));
		PinTo.add(pin_to_access_icon);
		PinTo.add(pinTo_text);
		container_icon_function.add(PinTo);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 5, 2, 5));
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		panel.setOpaque(true);
		panel.setBackground(ColorList.Back_Ground);
		panel.add(cut_icon);
		panel.add(copy_icon);
		panel.add(paste_icon);
		
		container_icon_function.add(panel);

		MoveTo.setLayout(new GridLayout(2, 1, 0, 0));
		MoveTo.add(move_to_icon);
		MoveTo.add(move_to_text);
		MoveTo.setBackground(ColorList.Back_Ground);

		CopyTo.setLayout(new GridLayout(2, 1, 0, 0));
		CopyTo.add(copy_to_icon);
		CopyTo.add(copy_to_text);
		CopyTo.setBackground(ColorList.Back_Ground);

		Delete.setLayout(new GridLayout(2, 1, 0, 0));
		Delete.add(delete_icon);
		Delete.add(delete_text);
		Delete.setBackground(ColorList.Back_Ground);
		container_icon_function.add(Delete);

		Rename.setLayout(new GridLayout(2, 1, 0, -8));
		Rename.add(rename_icon);
		Rename.add(rename_text);
		Rename.setBackground(ColorList.Back_Ground);
		
		container_icon_function.add(Rename);

		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, -5));
		
		NewFile.setLayout(new FlowLayout(FlowLayout.LEFT));
		NewFile.add(new_file_icon);
		NewFile.add(new_file_text);
		
		NewFolder.setLayout(new FlowLayout(FlowLayout.LEFT));
		NewFolder.add(new_folder_icon);
		NewFolder.add(new_folder_text);
		
		panel.setOpaque(true);
		panel.setBackground(ColorList.Back_Ground);
		panel.add(NewFolder);
		panel.add(NewFile);
		
		container_icon_function.add(panel);

		Select.setLayout(new GridLayout(2, 1, 0, 0));
		Select.add(select_all);
		Select.add(select_no);
		
		container_icon_function.add(Select);

		this.add(content, BorderLayout.EAST);
		this.add(container_icon_function, BorderLayout.CENTER);
	}

	public void EditObj() {
		cut_icon.setHorizontalAlignment(JLabel.LEFT);
		copy_icon.setHorizontalAlignment(JLabel.LEFT);
		paste_icon.setHorizontalAlignment(JLabel.LEFT);
		tdn.setHorizontalAlignment(JLabel.CENTER);
		hello.setHorizontalAlignment(JLabel.CENTER);
		select_all.setHorizontalAlignment(JLabel.LEFT);
		select_no.setHorizontalAlignment(JLabel.LEFT);
		register.setHorizontalAlignment(JLabel.CENTER);
		login.setHorizontalAlignment(JLabel.CENTER);
		dang_xuat.setHorizontalAlignment(JLabel.CENTER);

		pinTo_text.setEditable(false);
		copy_to_text.setEditable(false);
		move_to_text.setEditable(false);

		Delete.setBorder(new EmptyBorder(8, 10, 8, 10));
		Rename.setBorder(new EmptyBorder(4, 4, 4, 4));
		cut_icon.setBorder(new EmptyBorder(3, 3, 3, 3));
		copy_icon.setBorder(new EmptyBorder(3, 3, 3, 3));
		paste_icon.setBorder(new EmptyBorder(3, 3, 3, 3));
		select_all.setBorder(new EmptyBorder(3, 3, 3, 3));
		select_no.setBorder(new EmptyBorder(3, 3, 3, 3));
		register.setBorder(new LineBorder(ColorList.Fore_Ground));
		login.setBorder(new LineBorder(ColorList.Fore_Ground));
		dang_xuat.setBorder(new LineBorder(ColorList.Fore_Ground));
		button.setBorder(new EmptyBorder(0,-1,0,-1));
		
		setSelected(false);

		NewFile.setEnabled(false);
		new_file_icon.setEnabled(false);
		new_file_text.setEnabled(false);
		NewFolder.setEnabled(false);
		new_folder_icon.setEnabled(false);
		new_folder_text.setEnabled(false);
		paste_icon.setEnabled(false);
		
		EnableRoot(true);
		
		register.setOpaque(true);
		dang_xuat.setOpaque(true);
		login.setOpaque(isOpaque());
		
		exit(register.hashCode());
		exit(login.hashCode());
		exit(dang_xuat.hashCode());
		
		button.setIcon(new ImageIcon(URL.url + URL.urlToolBarFun + threedots));
	}

	public void EnableRoot(Boolean root) {
		Delete.setEnabled(!root);
		delete_icon.setEnabled(!root);
		delete_text.setEnabled(!root);
		Rename.setEnabled(!root);
		rename_icon.setEnabled(!root);
		rename_text.setEnabled(!root);
		MoveTo.setEnabled(!root);
		move_to_icon.setEnabled(!root);
		move_to_text.setEnabled(!root);
		cut_icon.setEnabled(!root);
	}

	public void addEvent() {
		addMouseMotionListener(mouseListen);
		
		login.addMouseListener(mouseListen);
		login.addMouseMotionListener(mouseListen);
		
		register.addMouseListener(mouseListen);
		register.addMouseMotionListener(mouseListen);
		
		dang_xuat.addMouseListener(mouseListen);
		dang_xuat.addMouseMotionListener(mouseListen);
		
		pin_to_access_icon.addMouseListener(mouseListen);
		pin_to_access_icon.addMouseMotionListener(mouseListen);
		
		pinTo_text.addMouseMotionListener(mouseListen);
		pinTo_text.addMouseListener(mouseListen);

		cut_icon.addMouseMotionListener(mouseListen);
		cut_icon.addMouseListener(mouseListen);

		copy_icon.addMouseListener(mouseListen);
		copy_icon.addMouseMotionListener(mouseListen);

		paste_icon.addMouseListener(mouseListen);
		paste_icon.addMouseMotionListener(mouseListen);

		move_to_icon.addMouseListener(mouseListen);
		move_to_icon.addMouseMotionListener(mouseListen);
		
		move_to_text.addMouseListener(mouseListen);
		move_to_text.addMouseMotionListener(mouseListen);

		copy_to_icon.addMouseListener(mouseListen);
		copy_to_icon.addMouseMotionListener(mouseListen);
		
		copy_to_text.addMouseListener(mouseListen);
		copy_to_text.addMouseMotionListener(mouseListen);

		delete_icon.addMouseListener(mouseListen);
		delete_icon.addMouseMotionListener(mouseListen);
		
		delete_text.addMouseListener(mouseListen);
		delete_text.addMouseMotionListener(mouseListen);
		
		Delete.addMouseListener(mouseListen);
		Delete.addMouseMotionListener(mouseListen);

		rename_icon.addMouseListener(mouseListen);
		rename_icon.addMouseMotionListener(mouseListen);
		
		rename_text.addMouseListener(mouseListen);
		rename_text.addMouseMotionListener(mouseListen);
		
		Rename.addMouseListener(mouseListen);
		Rename.addMouseMotionListener(mouseListen);

		NewFile.addMouseListener(mouseListen);
		NewFile.addMouseMotionListener(mouseListen);
		
		new_file_icon.addMouseListener(mouseListen);
		new_file_icon.addMouseMotionListener(mouseListen);
		
		new_file_text.addMouseListener(mouseListen);
		new_file_text.addMouseMotionListener(mouseListen);

		NewFolder.addMouseListener(mouseListen);
		NewFolder.addMouseMotionListener(mouseListen);
		
		new_folder_icon.addMouseListener(mouseListen);
		new_folder_icon.addMouseMotionListener(mouseListen);
		
		new_folder_text.addMouseListener(mouseListen);
		new_folder_text.addMouseMotionListener(mouseListen);

		select_all.addMouseListener(mouseListen);
		select_all.addMouseMotionListener(mouseListen);
		
		select_no.addMouseListener(mouseListen);
		select_no.addMouseMotionListener(mouseListen);
		
		Select.addMouseListener(mouseListen);
		Select.addMouseMotionListener(mouseListen);
		
		button.addActionListener(actionListen);
	}

	public JLabel btlogin() {
		return login;
	}

	public JLabel btregister() {
		return register;
	}

	public void onclick_Button(int code, int x, int y) {
		if (code == login.hashCode()) {
			if (Fregister != null) {
				Fregister.setVisible(false);
			}
			if (Flogin != null)
				if (Flogin.isVisible())
					Flogin.setVisible(false);
			Flogin = new FormLogin(this);
			Flogin.setLocation(x - (x > Flogin.getSize().width ? Flogin.getSize().width : 0), y);
		} else if (code == register.hashCode()) {
			if (Flogin != null) {
				Flogin.setVisible(false);
			}
			if (Fregister != null)
				if (Fregister.isVisible())
					Fregister.setVisible(false);
			Fregister = new FormRegister(this);
			Fregister.setLocation(x - (x > Fregister.getSize().width ? Fregister.getSize().width : 0), y);
		} else if (code == dang_xuat.hashCode()) {
			logOut();
		}

	}
	
	public void logOut()
	{
		if(pageUser != null)
			pageUser.setVisible(false);
		screen.setVisible(false);
		screen = new Screen(screen.getTitle(), null, false);
	}

	public void onclick_InForm(Boolean f, int x, int y) {
		if (f) {
			if (Fregister != null) {
				Fregister.setVisible(false);
			}
			if (Flogin != null)
				if (Flogin.isVisible())
					Flogin.setVisible(false);
			Flogin = new FormLogin(this);
			Flogin.setLocation(x - (x > Flogin.getSize().width ? Flogin.getSize().width : 0), y);
		} else {
			if (Flogin != null) {
				Flogin.setVisible(false);
			}
			if (Fregister != null)
				if (Fregister.isVisible())
					Fregister.setVisible(false);
			Fregister = new FormRegister(this);
			Fregister.setLocation(x - (x > Fregister.getSize().width ? Fregister.getSize().width : 0), y);
		}

	}

	public void setHover(int hash, Color color) {

		if (hash == pin_to_access_icon.hashCode() || hash == pinTo_text.hashCode()) {
			if (PinTo.isEnabled()) {
				pin_to_access_icon.setBackground(color);
				pinTo_text.setBackground(color);
			}
		} else if (hash == cut_icon.hashCode()) {
			if (cut_icon.isEnabled()) {
				cut_icon.setBackground(color);
			}
		} else if (hash == copy_icon.hashCode()) {
			if (copy_icon.isEnabled()) {
				copy_icon.setBackground(color);
			}
		} else if (hash == paste_icon.hashCode()) {
			if (paste_icon.isEnabled()) {
				paste_icon.setBackground(color);
			}
		} else if (hash == delete_icon.hashCode() || hash == delete_text.hashCode() || hash == Delete.hashCode()) {
			if (Delete.isEnabled()) {
				Delete.setBackground(color);
				delete_icon.setBackground(color);
				delete_text.setBackground(color);
			}
		} else if (hash == rename_icon.hashCode() || hash == rename_text.hashCode() || hash == Rename.hashCode()) {
			if (Rename.isEnabled()) {
				rename_icon.setBackground(color);
				rename_text.setBackground(color);
				Rename.setBackground(color);
			}
		} else if (hash == new_file_icon.hashCode() || hash == new_file_text.hashCode() || hash == NewFile.hashCode()) {
			if (NewFile.isEnabled()) {
				new_file_icon.setBackground(color);
				new_file_text.setBackground(color);
				NewFile.setBackground(color);
			}
		} else if (hash == new_folder_icon.hashCode() || hash == new_folder_text.hashCode()
				|| hash == NewFolder.hashCode()) {
			if (NewFolder.isEnabled()) {
				NewFolder.setBackground(color);
				new_folder_icon.setBackground(color);
				new_folder_text.setBackground(color);
			}
		} else if (hash == move_to_icon.hashCode() || hash == move_to_text.hashCode()) {
			if (MoveTo.isEnabled()) {
				move_to_icon.setBackground(color);
				move_to_text.setBackground(color);
			}
		} else if (hash == copy_to_icon.hashCode() || hash == copy_to_text.hashCode()) {
			if (CopyTo.isEnabled()) {
				copy_to_icon.setBackground(color);
				copy_to_text.setBackground(color);
			}
		} else if (hash == select_no.hashCode()) {
			select_no.setBackground(color);
		} else if (hash == select_all.hashCode()) {
			select_all.setBackground(color);
		}
	}

	public void setClicked(int hash) throws IOException, ClassNotFoundException {
		int n = -1;
		if (hash == pin_to_access_icon.hashCode() || hash == PinTo.hashCode() || hash == pinTo_text.hashCode()) {
			n = 1;
		} else if (hash == cut_icon.hashCode()) {
			n = 2;
		} else if (hash == copy_icon.hashCode()) {
			n = 3;
		} else if (hash == paste_icon.hashCode()) {
			n = 4;
		} else if (hash == move_to_icon.hashCode() || hash == move_to_text.hashCode() || hash == MoveTo.hashCode()) {
			n = 5;
		} else if (hash == copy_to_icon.hashCode() || hash == copy_to_text.hashCode() || hash == CopyTo.hashCode()) {
			n = 6;
		} else if (hash == Delete.hashCode() || hash == delete_icon.hashCode() || hash == delete_text.hashCode()) {
			if (Delete.isEnabled()) {
				n = 7;
				Delete.setBackground(ColorList.Back_Ground);
				delete_icon.setBackground(ColorList.Back_Ground);
				delete_text.setBackground(ColorList.Back_Ground);
			}
		} else if (hash == rename_icon.hashCode() || hash == rename_text.hashCode() || hash == Rename.hashCode()) {
			n = 8;
		} else if (hash == new_file_icon.hashCode() || hash == new_file_text.hashCode() || hash == NewFile.hashCode()) {
			n = 9;
		} else if (hash == new_folder_icon.hashCode() || hash == new_folder_text.hashCode()
				|| hash == NewFolder.hashCode()) {
			n = 10;
		} else if (hash == select_all.hashCode()) {
			n = 11;
			Rename.setEnabled(false);
			rename_text.setEnabled(false);
			rename_icon.setEnabled(false);
		} else if (hash == select_no.hashCode()) {
			n = 12;
		}
		if (0 < n && n < 13)
			screen.runfun(n);
	}

	public Screen getScreen() {
		return screen;
	}

	public void resert(User user) {
		screen.setVisible(false);
		screen = new Screen(screen.getTitle(), user, true);
	}

	public void setNew(Boolean show) {
		NewFile.setEnabled(show);
		new_file_icon.setEnabled(show);
		new_file_text.setEnabled(show);

		NewFolder.setEnabled(show);
		new_folder_icon.setEnabled(show);
		new_folder_text.setEnabled(show);
	}

	public void exit(int code)
	{
		if(code == login.hashCode())
		{
			login.setBackground(ColorList.Fore_Ground);
			login.setForeground(ColorList.Back_Ground);
		}
		else if(code == register.hashCode())
		{
			register.setBackground(ColorList.Fore_Ground);
			register.setForeground(ColorList.Back_Ground);
		}
		if(code == dang_xuat.hashCode())
		{
			dang_xuat.setBackground(ColorList.Fore_Ground);
			dang_xuat.setForeground(ColorList.Back_Ground);
		}
	}
	
	public void hover(int code)
	{
		if(code == login.hashCode())
		{
			login.setBackground(ColorList.Back_Ground);
			login.setForeground(ColorList.Fore_Ground);
		}
		else if(code == register.hashCode())
		{
			register.setBackground(ColorList.Back_Ground);
			register.setForeground(ColorList.Fore_Ground);
		} 
		else if(code == dang_xuat.hashCode())
		{
			dang_xuat.setBackground(ColorList.Back_Ground);
			dang_xuat.setForeground(ColorList.Fore_Ground);
		}
	}
	
	public void setSelected(Boolean selected) {
		PinTo.setEnabled(selected);
		pin_to_access_icon.setEnabled(selected);
		pinTo_text.setEnabled(selected);
		copy_icon.setEnabled(selected);
		CopyTo.setEnabled(selected);
		copy_to_icon.setEnabled(selected);
		copy_to_text.setEnabled(selected);
	}

	public void setEnPaste(Boolean bool) {
		paste_icon.setEnabled(bool);
	}
	
	public void clickedThreeDots()
	{
		if(pageUser == null)
		{
			pageUser = new ScreenPageUser(Screen.getUser(), this);
		} else
		{
			pageUser.setVisible(false);
			pageUser = new ScreenPageUser(Screen.getUser(), this);
		}
	}
}