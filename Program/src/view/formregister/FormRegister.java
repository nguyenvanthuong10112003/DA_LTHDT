package view.formregister;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.InsetsUIResource;

import controller.mouse;
import define.ColorList;
import define.DATE;
import define.JTextFieldPassWord;
import define.table.FOLDER;
import define.table.USER;
import model.User;
import test.ConnectSQL;
import view.toolbar.Panel_Functions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormRegister extends JFrame {
	private JPanel content;
	private JLabel register_text;
	private JPanel form;
	private JLabel tdn_text;
	private JTextField tdn_input;
	private JLabel pass_text;
	private JTextFieldPassWord pass_input;
	private JLabel pass_check;
	private JTextFieldPassWord pass_check_input;
	private JLabel full_name;
	private JTextField full_name_input;
	private JLabel date_of_birth;
	private JComboBox day;
	private JComboBox month;
	private JComboBox year;
	private JLabel sex;
	private JPanel check_sex;
	private JRadioButton nam;
	private JRadioButton nu;
	private ButtonGroup gb;
	private JLabel phoneNumber;
	private JTextField phone_number_input;
	private JLabel email;
	private JTextField email_input;
	private JPanel containerbt;
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
	private DATE date = new DATE(LocalDateTime.now().getYear());
	private Font font32 = new Font("Arial", Font.BOLD, 24);
	private Font fontp = new Font("Arial", Font.PLAIN, 14);
	private Font fontb = new Font("Arial", Font.BOLD, 14);
	private mouse mouseListen;
	
	public FormRegister(Panel_Functions fun) {
		try {
			if (fun != null)
				this.fun = fun;
			this.setTitle("Đăng ký");
			this.setSize(380, 480);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.setBackground(getForeground());
			this.setResizable(false);
			this.setData();
			this.init();
			this.setIcon();
			this.setColor();
			this.setText();
			this.Edit();
			this.setTime();
			this.addEvent();
			this.addObj();
			this.setCusor();
			this.setVisible(true);
			System.out.println("Upload success form register");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error form register");
		}
	}

	private void setData() throws ClassNotFoundException {
		checkUser = new HashMap<String, Boolean>();
		try {
			String sql = "SELECT " + USER.username + " FROM " + USER.nametable;
			Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
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
			// TODO Auto-generated catch block
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
		nam.setBackground(ColorList.Back_Ground);
		nu.setBackground(ColorList.Back_Ground);
	}

	private void init() {
		content = new JPanel();
		register_text = new JLabel();
		form = new JPanel();
		tdn_text = new JLabel();
		pass_text = new JLabel();
		tdn_input = new JTextField();
		pass_input = new JTextFieldPassWord();
		cancel = new JLabel();
		login = new JLabel();
		containerbt = new JPanel();
		hoi = new JLabel();
		register = new JLabel();
		pass_check = new JLabel();
		pass_check_input = new JTextFieldPassWord();
		full_name = new JLabel();
		full_name_input = new JTextField();
		date_of_birth = new JLabel();
		sex = new JLabel();
		gb = new ButtonGroup();
		check_sex = new JPanel();
		nam = new JRadioButton();
		nu = new JRadioButton();
		phoneNumber = new JLabel();
		phone_number_input = new JTextField();
		email = new JLabel();
		email_input = new JTextField();
		year = new JComboBox();
		month = new JComboBox();
		day = new JComboBox();
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
		full_name.setText("Họ và tên");
		full_name.setFont(fontp);
		date_of_birth.setText("Ngày sinh(dd/mm/yyyy)");
		date_of_birth.setFont(fontp);
		sex.setText("Giới tính");
		sex.setFont(fontp);
		nam.setText("Nam");
		nam.setFont(fontb);
		nu.setText("Nữ");
		nu.setFont(fontb);
		phoneNumber.setText("Số điện thoại");
		phoneNumber.setFont(fontp);
		email.setText("Email");
		email.setFont(fontp);
		day.setToolTipText("ngày");
		month.setToolTipText("tháng");
		year.setToolTipText("năm");
	}

	private void addObj() {
		content.setLayout(new BorderLayout());
		content.setBorder(new EmptyBorder(10, 15, 5, 15));
		register_text.setHorizontalAlignment(JLabel.CENTER);
		register_text.setBorder(new EmptyBorder(0, 0, 10, 0));
		content.add(register_text, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setBackground(ColorList.Back_Ground);
		p.setLayout(new GridLayout(8, 2, 0, 15));
		p.add(tdn_text);
		p.add(tdn_input, BorderLayout.CENTER);

		p.add(pass_text);
		p.add(pass_input);

		p.add(pass_check);
		p.add(pass_check_input);

		p.add(full_name);
		p.add(full_name_input);

		p.add(sex);
		
		JPanel l = new JPanel();
		l.setBackground(ColorList.Back_Ground);
		l.setLayout(new FlowLayout(FlowLayout.CENTER));
		gb.add(nam);
		gb.add(nu);
		l.add(nam);
		l.add(nu);
		p.add(l);
		p.add(date_of_birth);
		l = new JPanel();
		l.setBackground(ColorList.Back_Ground);
		l.setLayout(new GridLayout(1, 3));
		l.add(day);
		l.add(month);
		l.add(year);
		p.add(l);
		p.add(phoneNumber);
		p.add(phone_number_input);

		p.add(email);
		p.add(email_input);

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
				JOptionPane.showMessageDialog(day, passno, thongbao, JOptionPane.WARNING_MESSAGE);
			} else if (checkUser != null) {
				if (checkUser.size() == 0) {
					JOptionPane.showMessageDialog(day, text_accept, thongbao, JOptionPane.NO_OPTION);
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
						JOptionPane.showMessageDialog(day, text_accept, thongbao, JOptionPane.NO_OPTION);
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
						JOptionPane.showMessageDialog(day, da_ton_tai, thongbao, JOptionPane.OK_OPTION);
					}
				}
			} else {
				JOptionPane.showMessageDialog(day, errorList, thongbao, JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(day, text_thieu, thongbao, JOptionPane.WARNING_MESSAGE);
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
		Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
		if (connect != null) {
			System.out.println("Ket noi database thanh cong");
		} else {
			connect.close();
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
				String sql2 = "INSERT INTO _USER(" + USER.name + "," + USER.username + "," + USER.password + ","
						+ USER.phone + "," + USER.email + "," + USER.birth + "," + USER.create + "," + USER.folder + ","
						+ USER.sex + ")" + "VALUES (N'" + full_name_input.getText() + "', '" + tdn_input.getText()
						+ "', '" + pass_input.getPass() + "', '" + phone_number_input.getText() + "', '"
						+ email_input.getText() + "', '" + year.getSelectedItem() + "-" + month.getSelectedItem() + "-"
						+ day.getSelectedItem() + "', '" + java.time.LocalDate.now().getYear() + "-"
						+ java.time.LocalDate.now().getMonth() + "-" + java.time.LocalDate.now().getDayOfMonth() + "', "
						+ (idMax + 1) + ", " + (nam.isSelected() == true ? 1 : 0) + ")";
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
		} else
			return;
		checkUser.put(tdn_input.getText(), true);
		this.setVisible(false);
	}

	private String two(String s) {
		if (s.length() == 2)
			return s;
		else
			return "0" + s;
	}

	private Boolean CheckInput() {
		if (tdn_input.getText().equals("") || pass_input.getText().equals("") || pass_check_input.getText().equals("")
				|| full_name_input.getText().equals("") || !(nam.isSelected() || nu.isSelected()) ||
				// ((String)day.getSelectedItem()).equals("") ||
				// ((String)month.getSelectedItem()).equals("") ||
				// ((String)year.getSelectedItem()).equals("") ||
				phone_number_input.getText().equals("") || email_input.getText().equals(""))
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
	
	private void setTime() {
		year.setEditable(true);
		month.setEditable(true);
		day.setEditable(true);

		for (int i : date.year())
			year.addItem(i);
		for (int i : date.month())
			month.addItem(i);
		for (int i : date.day((Integer) month.getSelectedItem(), (Integer) year.getSelectedItem()))
			day.addItem(i);
		year.setSelectedItem(year.getItemAt(year.getItemCount() - 1));
		year.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateTime();
			}
		});
		month.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateTime();
			}
		});
		day.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (day.getSelectedItem() == null)
					day.setSelectedItem(1);
				else if (!day.getSelectedItem().getClass().equals(Integer.class))
					day.setSelectedItem((Integer) day.getItemAt(day.getItemCount() - 1));
				else if ((Integer) day.getSelectedItem() > (Integer) day.getItemAt(day.getItemCount() - 1))
					day.setSelectedItem((Integer) day.getItemAt(day.getItemCount() - 1));
				else if ((Integer) day.getSelectedItem() < (Integer) day.getItemAt(0))
					day.setSelectedItem((Integer) day.getItemAt(0));
			}
		});
	}

	private void updateTime() {
		int d = (int) day.getSelectedItem();
		if (year.getSelectedItem() == null)
			year.setSelectedItem((Integer) year.getItemAt(year.getItemCount() - 1));
		else if (!year.getSelectedItem().getClass().equals(Integer.class))
			year.setSelectedItem((Integer) year.getItemAt(year.getItemCount() - 1));
		else if ((Integer) year.getSelectedItem() > (Integer) year.getItemAt(year.getItemCount() - 1))
			year.setSelectedItem((Integer) year.getItemAt(year.getItemCount() - 1));
		else if ((Integer) year.getSelectedItem() < (Integer) year.getItemAt(0))
			year.setSelectedItem((Integer) year.getItemAt(0));

		if (month.getSelectedItem() == null)
			month.setSelectedItem(1);
		else if (!month.getSelectedItem().getClass().equals(Integer.class))
			month.setSelectedItem((Integer) month.getItemAt(month.getItemCount() - 1));
		else if ((Integer) month.getSelectedItem() > (Integer) month.getItemAt(month.getItemCount() - 1))
			month.setSelectedItem((Integer) month.getItemAt(month.getItemCount() - 1));
		else if ((Integer) month.getSelectedItem() < (Integer) month.getItemAt(0))
			month.setSelectedItem((Integer) month.getItemAt(0));

		day.removeAllItems();
		for (int i : date.day((Integer) month.getSelectedItem(), (Integer) year.getSelectedItem())) {
			day.addItem(i);
		}
		if (d <= (int) day.getItemAt(day.getItemCount() - 1))
			day.setSelectedItem(d);
		else
			day.setSelectedItem((int) day.getItemAt(day.getItemCount() - 1));

	}
}
