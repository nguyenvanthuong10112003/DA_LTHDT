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
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libary.JTextFieldPassWord;
import model.User;
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
	 private String icon = "..//image//formlogin//icon-login.png";
	 private Font font32 = new Font("Arial", Font.BOLD, 28);
	 private Font fontp = new Font("Arial", Font.PLAIN, 14);
	 private Font fontb = new Font("Arial", Font.BOLD, 14);
	 private Color black = Color.BLACK;
	 private Color white = Color.WHITE;
	 private Color blue = Color.BLUE;
	 private LinkedList <User> users;
	 private Map<String, User> checkUser;
	 private Panel_Functions fun;
     public FormLogin(Panel_Functions fun)
     {
    	 if(fun!= null)
    		 this.fun = fun;
    	 this.setTitle("Đăng nhập");
    	 this.setSize(500, 250);
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
     }
}
