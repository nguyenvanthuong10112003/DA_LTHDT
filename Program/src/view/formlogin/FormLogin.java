package view.formlogin;

import view.formregister.FormRegister;
import view.toolbar.Panel_Functions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import libary.JTextFieldPassWord;
import model.User;
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
	private JButton cancel;
	private JButton login;
	private JPanel t;
	private JLabel hoi;
	private JButton register;
	private String icon = "\\Icon\\formlogin\\icon-login.png";
	private Font font32 = new Font("Arial", Font.BOLD, 28);
	private Font fontp = new Font("Arial", Font.PLAIN, 14);
	private Font fontb = new Font("Arial", Font.BOLD, 14);
	private Color black = Color.BLACK;
	private Color white = Color.WHITE;
	private Color blue = Color.BLUE;
	private Map<String, User> checkUser;
	private Panel_Functions fun;
	private JOptionPane option;
	private String thongbao = "Thông báo";
	private String text_accept = "Thành công!";
	private String text_no = "Thông tin đăng nhập không tồn tại!\nHãy tạo tài khoản mới.";
	private String text_error = "Thông tin đăng nhập không chính xác!";
	private String text_thieu = "Yêu cầu nhập đầy đủ thông tin đăng nhập!";

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
			this.addObj();
			this.setCusor();
			this.setVisible(true);
			System.out.println("Tải thành công form đăng nhập");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Form đăng nhập");
		}
	}

	private void setData()
	{
    	checkUser = new HashMap<String, User>();
    	try {
    	    String sql = "SELECT * FROM _User";
    	    Connection con = fun.getConnection();
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next())
			{
				///Fullname 
				//tdn
				//pass 
				//phone 
				//email 
				//country 
				//birth 
				//created 
				//rootFolder
				//sex
				User user = new User(
				rs.getString("tdn"), 
				rs.getString("pass"),
				rs.getString("Fullname"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getInt("sex") == 1 ? true : false,
				rs.getString("country"),
				toDate(rs.getString("created")),
				toDate(rs.getString("birth")),
				new Folder(rs.getInt("rootFolder")));
				checkUser.put(user.getTenDangNhap(), user);
			}
			sta.close();
			rs.close();
			System.out.println("Tải dữ liệu user thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private java.util.Date toDate(String d)
	{
		return new Date(
			Integer.parseInt(d.substring(0, 4)), 
			Integer.parseInt(d.substring(5, 7)), 
			Integer.parseInt(d.substring(8, 10)));
	}
	
	private java.util.Date toDateTime(String d)
	{
		return new Date(
			Integer.parseInt(d.substring(0, 4)), 
			Integer.parseInt(d.substring(5, 7)), 
			Integer.parseInt(d.substring(8, 10)),
			Integer.parseInt(d.substring(11, 13)),
			Integer.parseInt(d.substring(14, 16)));
	}
	private void setColor(Color back, Color font) {
		cancel.setOpaque(true);
		register.setOpaque(true);
		login.setOpaque(true);
		cancel.setBackground(back);
		register.setBackground(back);
		login.setBackground(back);
		login.setForeground(font);
		cancel.setForeground(font);
		register.setForeground(font);
	}

	private void setIcon() {
		try {
			this.setIconImage((new ImageIcon(libary.URL.url + icon)).getImage());
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
		cancel = new JButton();
		login = new JButton();
		containerbt = new JPanel();
		t = new JPanel();
		hoi = new JLabel();
		register = new JButton();
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
		form.add(new JPanel());
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
		register.addMouseListener(new MouseListener() {

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
				fun.onclick_InForm(false, e.getX(), e.getY());
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
	}
	private void success(User user)
	{
		try {
			String sql = "SELECT * FROM _Folder WHERE id = " + user.getRoot().getId();
			Connection con = fun.getConnection();
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			rs.next();
			user.getRoot().setName(rs.getString("Fullname"));
			user.getRoot().setDateCreate(toDateTime(rs.getString("date_create")));
			System.out.println("Update thành công");
			sta.close();
			rs.close();
		} catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		 
	}
}
