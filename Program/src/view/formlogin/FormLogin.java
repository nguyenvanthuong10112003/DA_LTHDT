package view.formlogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libary.JTextFieldPassWord;

public class FormLogin extends JFrame
{
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
	 private String icon = "icon-login.png";
	 private Font font32 = new Font("Arial", Font.BOLD, 28);
	 private Font fontp = new Font("Arial", Font.PLAIN, 14);
	 private Font fontb = new Font("Arial", Font.BOLD, 14);
	 private Color color = Color.BLACK;
     public FormLogin()
     {
    	 this.setTitle("Đăng nhập");
    	 this.setSize(500, 250);
    	 //this.setLocationRelativeTo(null);
    	 this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	 this.setBackground(getForeground());
    	 this.setResizable(false);
    	 this.init();
    	 this.setIcon();
    	 this.setColor();
    	 this.setText();
    	 this.addObj();
    	 this.setCusor();
    	 this.setVisible(true);
     }
     private void setColor()
     {
    	 cancel.setOpaque(true);
    	 register.setOpaque(true);
    	 login.setOpaque(true);
    	 cancel.setBackground(color);
    	 register.setBackground(color);
    	 login.setBackground(color);
    	 login.setForeground(Color.white);
    	 cancel.setForeground(Color.white);
    	 register.setForeground(Color.white);
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
     private void setText()
     {
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
     private void addObj()
     {
    	 content.setLayout(new GridLayout(4,1));
    	 login_text.setHorizontalAlignment(JLabel.CENTER);
    	 login_text.setBackground(Color.black);
    	 content.add(login_text);
    	 form.setLayout(new GridLayout(2,4,5,5));
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
     }
}
