package view.formregister;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.swing.BoxLayout;
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
import libary.ConnectSQL;
import view.toolbar.Panel_Functions;
import java.util.HashMap;

public class FormRegister extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel register_text;
	private JLabel tdn_text;
	private JTextField tdn_input;
	private JLabel pass_text;
	private JTextFieldPassWord pass_input;
	private JLabel pass_check;
	private JTextFieldPassWord pass_check_input;
	private JLabel cancel;
	private JLabel login;
	private JLabel hoi;
	private JLabel register;
	private Panel_Functions fun;
	private String icon = "\\Icon\\formregister\\icon-register.png";
	private Map<String, Boolean> checkUser;
	private String thongbao = "Thông báo";
	private String text_accept = "Thành công!";
	private String text_thieu = "Yêu cầu nhập đầy đủ thông tin!";
	private String da_ton_tai = "Thông tin tên đăng nhập đã tồn tại! Hãy đặt tên đăng nhập khác.";
	private String errorList = "Error List User is null";
	private String passno = "Mật khẩu nhập lại không trùng khớp với mật khẩu!";
	private Font font32 = new Font("Arial", Font.BOLD, 24);
	private Font fontp = new Font("Arial", Font.PLAIN, 14);
	private Font fontb = new Font("Arial", Font.BOLD, 14);
	private mouse mouseListen;
	
	public FormRegister(Panel_Functions fun) {
		try {
			if (fun != null)
				this.fun = fun;
			this.setTitle("Đăng ký");
			this.setSize(350, 400);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.setBackground(getForeground());
			this.setResizable(false);
			this.setData();
			this.init();
			this.setIcon();
			this.setColor();
			this.setText();
			this.Edit();
			this.addEvent();
			this.addObj();
			this.setCusor();
			this.setVisible(true);
			System.out.println("Upload form register success");
		} catch (Exception e) {
			System.out.println("Error form register");
		}
	}

	private void setData() throws ClassNotFoundException {
		checkUser = new HashMap<String, Boolean>();
		try {
			String sql = "SELECT " + USER.username + " FROM " + USER.nametable;
			Connection connect = ConnectSQL.getJDBCConnection();
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				System.out.println("Ket noi database that bai");
				return;
			}
			Statement sta = connect.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next())
				checkUser.put(rs.getString(USER.username), true);
			sta.close();
			rs.close();
			System.out.println("Upload data success!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setColor() {
		cancel.setOpaque(true);
		register.setOpaque(true);
		login.setOpaque(true);
		cancel.setForeground(ColorList.Back_Ground);
		login.setForeground(ColorList.Back_Ground);
		register.setForeground(ColorList.Back_Ground);
		exit(cancel.hashCode());
		exit(login.hashCode());
		exit(register.hashCode());
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

	private void Edit() {
		EmptyBorder border = new EmptyBorder(9,10,9,10);
		cancel.setBorder(border);
		register.setBorder(border);
		login.setBorder(border);
		content.setBackground(ColorList.Back_Ground);
		tdn_text.setVerticalAlignment(JLabel.BOTTOM);
		pass_text.setVerticalAlignment(JLabel.BOTTOM);
		pass_check.setVerticalAlignment(JLabel.BOTTOM);
	}

	private void init() {
		content = new JPanel();
		register_text = new JLabel();
		tdn_text = new JLabel();
		pass_text = new JLabel();
		tdn_input = new JTextField();
		pass_input = new JTextFieldPassWord();
		cancel = new JLabel();
		login = new JLabel();
		hoi = new JLabel();
		register = new JLabel();
		pass_check = new JLabel();
		pass_check_input = new JTextFieldPassWord();
		mouseListen = new mouse(this);
	}

	private void setText() {
		register_text.setText("Đăng ký");
		register_text.setFont(font32);
		tdn_text.setText("Tên đăng nhập");
		tdn_text.setFont(fontp);
		pass_text.setText("Mật khẩu");
		pass_text.setFont(fontp);
		cancel.setText("Hủy");
		cancel.setFont(fontb);
		login.setText("Đăng nhập");
		login.setFont(fontb);
		hoi.setText("Bạn đã có tài khoản?");
		hoi.setFont(fontp);
		register.setText("Đăng ký");
		register.setFont(fontb);
		pass_check.setText("Nhập lại mật khẩu");
		pass_check.setFont(fontp);
	}

	private void addObj() {
		content.setLayout(new BorderLayout());
		content.setBorder(new EmptyBorder(20, 40, 5, 40));
		register_text.setHorizontalAlignment(JLabel.CENTER);
		register_text.setBorder(new EmptyBorder(0, 0, 10, 0));
		content.add(register_text, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setBackground(ColorList.Back_Ground);
		p.setLayout(new GridLayout(6, 1, 0, 3));
		p.add(tdn_text);
		p.add(tdn_input, BorderLayout.CENTER);

		p.add(pass_text);
		p.add(pass_input);

		p.add(pass_check);
		p.add(pass_check_input);

//		p.add(full_name);
//		p.add(full_name_input);
//
//		p.add(sex);
		
		JPanel l = new JPanel();
//		l.setBackground(ColorList.Back_Ground);
//		l.setLayout(new FlowLayout(FlowLayout.CENTER));
//		gb.add(nam);
//		gb.add(nu);
//		l.add(nam);
//		l.add(nu);
//		p.add(l);
//		p.add(date_of_birth);
//		l = new JPanel();
//		l.setBackground(ColorList.Back_Ground);
//		l.setLayout(new GridLayout(1, 3));
//		l.add(day);
//		l.add(month);
//		l.add(year);
//		p.add(l);
//		p.add(phoneNumber);
//		p.add(phone_number_input);
//
//		p.add(email);
//		p.add(email_input);
//
		content.add(p, BorderLayout.CENTER);
		l = new JPanel();
		l.setBackground(ColorList.Back_Ground);
		l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
		p = new JPanel();
		p.setBackground(ColorList.Back_Ground);
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.add(cancel);
		p.add(register);
		l.add(p);
		p = new JPanel();
		p.setBackground(ColorList.Back_Ground);
		p.add(hoi);
		p.add(login);
		l.add(p);
		content.add(l, BorderLayout.SOUTH);
		this.add(content, BorderLayout.CENTER);
	}

	public void clickRegister()
	{
		if (CheckInput()) {
			if (!pass_check_input.getPass().equals(pass_input.getPass())) {
				JOptionPane.showMessageDialog(this, passno, thongbao, JOptionPane.WARNING_MESSAGE);
			} else if (checkUser != null) {
				if (checkUser.size() == 0) {
					JOptionPane.showMessageDialog(this, text_accept, thongbao, JOptionPane.NO_OPTION);
					try {
						Accept();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					if (checkUser.get(tdn_input.getText()) == null) {
						JOptionPane.showMessageDialog(this, text_accept, thongbao, JOptionPane.NO_OPTION);
						try {
							Accept();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(this, da_ton_tai, thongbao, JOptionPane.OK_OPTION);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, errorList, thongbao, JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, text_thieu, thongbao, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void clicked(int code, int x, int y)
	{
		if(code == login.hashCode())
		{
			fun.onclick_InForm(true, x, y);
		} else if(code == register.hashCode())
		{
			clickRegister();
		} else if(code == cancel.hashCode())
		{
			setVisible(false);
		}
	}
	
	public void hover(int code)
	{
		if(code == login.hashCode())
		{
			login.setBackground(ColorList.Blue);
		} else if(code == register.hashCode())
		{
			register.setBackground(ColorList.Blue);
		} else if(code == cancel.hashCode())
		{
			cancel.setBackground(ColorList.Blue);
		}
	}
	
	public void exit(int code)
	{
		if(code == login.hashCode())
		{
			login.setBackground(ColorList.Fore_Ground);
		} else if(code == register.hashCode())
		{
			register.setBackground(ColorList.Fore_Ground);
		} else if(code == cancel.hashCode())
		{
			cancel.setBackground(ColorList.Fore_Ground);
		}
	}
	
	private void Accept() throws ClassNotFoundException, SQLException {
		Connection connect = ConnectSQL.getJDBCConnection();
		if (connect != null) {
			System.out.println("Ket noi database thanh cong");
		} else {
			System.out.println("Ket noi database that bai");
			return;
		}
		if (connect != null) {
			try {
				java.time.LocalDate.now();
				String sql1 = "SELECT MAX(" + FOLDER.id + ") AS ID FROM " + FOLDER.nametable;
				Statement sta = connect.createStatement();
				ResultSet result = sta.executeQuery(sql1);
				result.next();
				int idMax = result.getInt("ID");
				String sql2 = "INSERT INTO _USER(" 
												+ USER.username + "," 
												+ USER.password + ","
											    + USER.create + "," 
												+ USER.folder + ")" + 
							  "VALUES ('" 
												+ tdn_input.getText() + "', '" 
												+ pass_input.getPass() + "', '" 
												+ java.time.LocalDate.now().getYear() + "-" + java.time.LocalDate.now().getMonth().getValue() + "-" + java.time.LocalDate.now().getDayOfMonth() + "', "
												+ (idMax + 1) + ")";
				System.out.println(sql2);
				String sql3 = "INSERT INTO _FOLDER(" + FOLDER.id + "," + FOLDER.nameFolder + "," + FOLDER.create + ")"
						+ "VALUES (" + (idMax + 1) + ", 'This pc', '" + +java.time.LocalDateTime.now().getYear() + "-"
						+ two(((Integer) java.time.LocalDateTime.now().getMonthValue()).toString()) + "-"
						+ two(((Integer) java.time.LocalDateTime.now().getDayOfMonth()).toString()) + " "
						+ two(((Integer) java.time.LocalDateTime.now().getHour()).toString()) + ":"
						+ two(((Integer) java.time.LocalDateTime.now().getMinute()).toString()) + ":"
						+ two(((Integer) java.time.LocalDateTime.now().getSecond()).toString()) + "')";
				int check = sta.executeUpdate(sql3);
				if (check > 0) {
					check = sta.executeUpdate(sql2);
				}
				sta.close();
				result.close();
				connect.close();
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}
			checkUser.put(tdn_input.getText(), true);
			this.setVisible(false);
		} 
	}

	private String two(String s) {
		if (s.length() == 2)
			return s;
		else
			return "0" + s;
	}

	private Boolean CheckInput() {
		if (tdn_input.getText().equals("") || pass_input.getText().equals("") || pass_check_input.getText().equals(""))
			return false;
		return true;
	}

	private void addEvent()
	{
		login.addMouseListener(mouseListen);
		login.addMouseMotionListener(mouseListen);
		register.addMouseListener(mouseListen);
		register.addMouseMotionListener(mouseListen);
		cancel.addMouseListener(mouseListen);
		cancel.addMouseMotionListener(mouseListen);
	}
}
