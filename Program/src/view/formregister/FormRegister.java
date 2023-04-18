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

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.InsetsUIResource;

import libary.JTextFieldPassWord;

public class FormRegister extends JFrame
{
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
	 private JButton cancel;
	 private JButton login;
	 private JPanel t;
	 private JLabel hoi;
	 private JButton register;
	 private String icon = "icon-register.png";
	 private Font font32 = new Font("Arial", Font.BOLD, 24);
	 private Font fontp = new Font("Arial", Font.PLAIN, 14);
	 private Font fontb = new Font("Arial", Font.BOLD, 14);
	 private Color black = Color.BLACK;
	 private Color white = Color.WHITE;
	 private Color blue = Color.BLUE;
     public FormRegister()
     {
    	 this.setTitle("Đăng ký");
    	 this.setSize(300, 450);
    	 //this.setLocationRelativeTo(null);
    	 this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	 this.setBackground(getForeground());
    	 this.setResizable(false);
    	 this.init();
    	 this.setIcon();
    	 this.setColor(blue, black);
    	 this.setText();
    	 this.addObj();
    	 this.setCusor();
    	 this.setVisible(true);
     }
     private void setColor(Color back, Color font)
     {
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
     private void setIcon()
     {
    	 try {
    		 this.setIconImage((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(icon)))).getImage());
    	 } catch(Exception e)
    	 {
    		 System.out.print("error");
    	 }
     }
     private void setCusor()
     {
    	 cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	 login.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	 register.setCursor(new Cursor(Cursor.HAND_CURSOR));
     }
     private void init()
     {
    	 content = new JPanel();
    	 register_text = new JLabel();
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
    	 pass_check = new JLabel();
    	 pass_check_input = new JTextFieldPassWord();
    	 full_name = new JLabel();
    	 full_name_input = new JTextField();   	 
    	 date_of_birth = new JLabel();
    	 day = new JComboBox();
    	 month = new JComboBox();
    	 year = new JComboBox(); 
    	 sex = new JLabel();
    	 gb = new ButtonGroup();
    	 check_sex = new JPanel();
    	 nam = new JRadioButton();
    	 nu = new JRadioButton();	 
    	 phoneNumber = new JLabel();
    	 phone_number_input = new JTextField();	 
    	 email = new JLabel();
    	 email_input = new JTextField();
     }
     private void setText()
     {
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
    	 date_of_birth.setText("Ngày sinh");
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
     private void addObj()
     {
    	 content.setLayout(new BorderLayout());
    	 content.setBorder(new EmptyBorder(5, 15, 0, 15));
    	 register_text.setHorizontalAlignment(JLabel.CENTER);
    	 content.add(register_text, BorderLayout.NORTH);
         JPanel p = new JPanel();
         p.setLayout(new GridLayout(8,2,0,15));
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
         l.setLayout(new FlowLayout(FlowLayout.CENTER));
         gb.add(nam);
         gb.add(nu);
         l.add(nam);
         l.add(nu);
         p.add(l);
         
         p.add(date_of_birth);
         l = new JPanel();
         l.setLayout(new GridLayout(1,3));
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
         l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
         p = new JPanel();
         p.setLayout(new FlowLayout(FlowLayout.RIGHT));
         p.add(cancel);
         p.add(register);
         l.add(p);
         p = new JPanel();
         p.add(hoi);
         p.add(login);
         l.add(p);
         content.add(l, BorderLayout.SOUTH);
    	 this.add(content, BorderLayout.CENTER);
     }
}
