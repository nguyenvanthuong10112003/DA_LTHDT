package view.formlogin;

import view.toolbar.Panel_Functions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.mouse;
import define.ColorList;
import libary.JTextFieldPassWord;
import define.table.FOLDER;
import define.table.USER;
import model.User;
import libary.ConnectSQL;
import model.Folder;

public class FormLogin extends JFrame {
	private JPanel content;
	private JLabel login_text;
	private JPanel form;
	private JLabel tdn_text;
	private JLabel pass_text;
	private JTextField tdn_input;
	private JTextFieldPassWord pass_input;
	private JPanel containerbt;
	private JLabel cancel;
	private JLabel login;
	private JPanel t;
	private JLabel hoi;
	private JLabel register;
	private String icon = "\\Icon\\formlogin\\icon-login.png";
	private Font font32 = new Font("Arial", Font.BOLD, 28);
	private Font fontp = new Font("Arial", Font.PLAIN, 14);
	private Font fontb = new Font("Arial", Font.BOLD, 14);
	private Color black = Color.BLACK;
	private Color blue = Color.BLUE;
	private Map<String, User> checkUser;
	private Panel_Functions fun;
	private JOptionPane option;
	private String thongbao = "Thông báo";
	private String text_accept = "Thành công!";
	private String text_no = "Thông tin đăng nhập không tồn tại!\nHãy tạo tài khoản mới.";
	private String text_error = "Thông tin đăng nhập không chính xác!";
	private String text_thieu = "Yêu cầu nhập đầy đủ thông tin đăng nhập!";
	private mouse mouselisten;

	public FormLogin(Panel_Functions fun) {
		try {
			if (fun != null)
				this.fun = fun;
			this.setTitle("Đăng nhập");
			this.setSize(500, 250);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.setBackground(getForeground());
			this.setResizable(false);
			this.setData();
			this.init();
			this.setIcon();
			this.setColor(blue, black);
			this.setText();
			this.Edit();
			this.addObj();
			this.setCusor();
			this.setEvent();
			this.setVisible(true);
			System.out.println("Upload form login success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Form login");
		}
	}

	private void setData() throws ClassNotFoundException {
		checkUser = new HashMap<String, User>();
		try {
			String sql = "SELECT * FROM " + USER.nametable;
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
			Statement sta = connect.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				User user = new User(
									rs.getString(USER.username).trim(), 
									rs.getString(USER.password).trim(), 
									rs.getString(USER.name) == null ? "" :  rs.getString(USER.name).trim(),
									rs.getString(USER.phone) == null ? "" : rs.getString(USER.phone).trim(), 
									rs.getString(USER.email) == null ? "" : rs.getString(USER.email).trim(), 
									rs.getString(USER.sex) == null ? null : ( rs.getInt(USER.sex) == 1 ? true : false),
									rs.getString(USER.address) == null ? "" : rs.getString(USER.address).trim(), 
									toDate(rs.getString(USER.create)), 
									rs.getString(USER.birth) == null ? null : toDate(rs.getString(USER.birth)),
						new Folder(rs.getInt(USER.folder)));
				checkUser.put(user.getTenDangNhap(), user);
			}
			sta.close();
			rs.close();
			connect.close();
			System.out.println("Upload data success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private java.util.Date toDate(String d) {
		return new Date(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)),
				Integer.parseInt(d.substring(8, 10)));
	}

	private java.util.Date toDateTime(String d) {
		return new Date(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)),
				Integer.parseInt(d.substring(8, 10)), Integer.parseInt(d.substring(11, 13)),
				Integer.parseInt(d.substring(14, 16)));
	}

	private void setColor(Color back, Color font) {
		cancel.setOpaque(true);
		register.setOpaque(true);
		login.setOpaque(true);
		login.setForeground(ColorList.Back_Ground);
		cancel.setForeground(ColorList.Back_Ground);
		register.setForeground(ColorList.Back_Ground);
		exit(cancel.hashCode());
		exit(login.hashCode());
		exit(register.hashCode());
	}

	private void Edit() {
		containerbt.setBorder(new EmptyBorder(8, 0, 0, 0));
		EmptyBorder border = new EmptyBorder(10, 10, 10, 10);
		login.setBorder(border);
		register.setBorder(border);
		cancel.setBorder(border);
		content.setBackground(ColorList.Back_Ground);
		form.setBackground(ColorList.Back_Ground);
		containerbt.setBackground(ColorList.Back_Ground);
		t.setBackground(ColorList.Back_Ground);
	}

	private void setIcon() {
		try {
			this.setIconImage((new ImageIcon(define.URL.url + icon)).getImage());
		} catch (Exception e) {
			System.out.print("error");
		}
	}

	private void setCusor() {
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void init() {
		content = new JPanel();
		login_text = new JLabel();
		form = new JPanel();
		tdn_text = new JLabel();
		pass_text = new JLabel();
		tdn_input = new JTextField();
		pass_input = new JTextFieldPassWord();
		cancel = new JLabel();
		login = new JLabel();
		containerbt = new JPanel();
		t = new JPanel();
		hoi = new JLabel();
		register = new JLabel();
		mouselisten = new mouse(this);
	}

	private void setText() {
		login_text.setText("Đăng nhập");
		login_text.setFont(font32);
		tdn_text.setText("Tên đăng nhập");
		tdn_text.setFont(fontp);
		pass_text.setText("Mật khẩu");
		pass_text.setFont(fontp);
		cancel.setText("Hủy");
		cancel.setFont(fontb);
		login.setText("Đăng nhập");
		login.setFont(fontb);
		hoi.setText("Bạn chưa có tài khoản?");
		hoi.setFont(fontp);
		register.setText("Đăng ký");
		register.setFont(fontb);
	}

	private void addObj() {
		content.setLayout(new GridLayout(4, 1));
		login_text.setHorizontalAlignment(JLabel.CENTER);
		login_text.setBackground(Color.black);
		content.add(login_text);
		form.setLayout(new GridLayout(2, 4, 5, 5));
		tdn_text.setHorizontalAlignment(JLabel.LEFT);
		pass_text.setHorizontalAlignment(JLabel.LEFT);
		form.add(new JLabel());
		form.add(tdn_text);
		form.add(tdn_input);
		form.add(new JLabel());
		form.add(new JLabel());
		form.add(pass_text);
		form.add(pass_input);
		form.add(new JLabel());
		content.add(form);
		containerbt.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerbt.add(cancel);
		containerbt.add(login);
		content.add(containerbt);
		t.setLayout(new FlowLayout(FlowLayout.CENTER));
		t.add(hoi);
		t.add(register);
		content.add(t);
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
	}

	private void setEvent() {
		register.addMouseListener(mouselisten);
		register.addMouseMotionListener(mouselisten);
		cancel.addMouseListener(mouselisten);
		cancel.addMouseMotionListener(mouselisten);
		login.addMouseListener(mouselisten);
		login.addMouseMotionListener(mouselisten);
	}

	public void clickLogin() {
		if (!(tdn_input.getText().equals("") || pass_input.getText().equals(""))) {
			if (checkUser != null) {
				if (checkUser.get(tdn_input.getText()) != null) {
					if (checkUser.get(tdn_input.getText()).getPassWord().equals(pass_input.getPass())) {
						JOptionPane.showMessageDialog(login, text_accept, thongbao, JOptionPane.INFORMATION_MESSAGE);
						success(checkUser.get(tdn_input.getText()));
						setVisible(false);
						if (fun.getScreen() != null)
							fun.resert(checkUser.get(tdn_input.getText()));
					} else {
						JOptionPane.showMessageDialog(login, text_error, thongbao, JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(login, text_no, thongbao, JOptionPane.YES_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(login, text_no, thongbao, JOptionPane.YES_OPTION);
			}
		} else {
			JOptionPane.showMessageDialog(login, text_thieu, thongbao, JOptionPane.WARNING_MESSAGE);
		}
	}

	public void clickRegister(int x, int y) {
		fun.onclick_InForm(false, x, y);
	}

	public void clicked(int code, int x, int y) {
		if (code == login.hashCode()) {
			clickLogin();
		} else if (code == register.hashCode()) {
			clickRegister(x, y);
		} else if (code == cancel.hashCode()) {
			setVisible(false);
		}
	}

	public void hover(int code) {
		if (code == login.hashCode()) {
			login.setBackground(ColorList.Blue);
		} else if (code == register.hashCode()) {
			register.setBackground(ColorList.Blue);
		} else if (code == cancel.hashCode()) {
			cancel.setBackground(ColorList.Blue);
		}
	}

	public void exit(int code) {
		if (code == login.hashCode()) {
			login.setBackground(ColorList.Fore_Ground);
		} else if (code == register.hashCode()) {
			register.setBackground(ColorList.Fore_Ground);
		} else if (code == cancel.hashCode()) {
			cancel.setBackground(ColorList.Fore_Ground);
		}
	}

	private void success(User user) {
		try {
			String sql = "SELECT * FROM " + FOLDER.nametable + " WHERE " + FOLDER.id + " = " + user.getRoot().getId();
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
			Statement sta = connect.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			rs.next();
			user.getRoot().setId(rs.getInt(FOLDER.id));
			user.getRoot().setName(rs.getString(FOLDER.nameFolder));
			user.getRoot().setDateCreate(toDateTime(rs.getString(FOLDER.create)));
			System.out.println("Update success");
			sta.close();
			rs.close();
			connect.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
