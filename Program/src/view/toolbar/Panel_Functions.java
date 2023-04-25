package view.toolbar;
import libary.JLabelIcon;
import libary.ColorList;
import libary.FONT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.mouse;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.screen.Screen;

public class Panel_Functions extends JToolBar{
	  private JPanel content;
	  private JButton login;
	  private JButton register;
	  private Color black = Color.BLACK;
	  private Color white = Color.WHITE;
	  private Color blue = Color.BLUE;
	  private FormLogin Flogin;
	  private FormRegister Fregister;
	  private JPanel  container_icon_function;
	  private JLabelIcon pin_to_access_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("pin_to_access_24.png"))));
	  private JLabelIcon cut_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("cut16.png"))));
	  private JLabelIcon copy_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("copy2_16.png"))));
	  private JLabelIcon paste_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("paste16.png"))));
	  private JLabelIcon move_to_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("move.png"))));
	  private JTextArea move_to_text;
	  private JLabelIcon copy_to_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("copy_to32.png"))));
	  private JTextArea copy_to_text;
	  private JLabelIcon delete_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("delete24.png"))));
	  private JComboBox delete_text;
	  private JLabelIcon rename_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rename24.png"))));
	  private JLabel rename_text;
	  private JLabelIcon new_folder_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("new_folder24.png"))));
	  private JLabel new_folder_text;
	  private JLabelIcon new_file_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("new_file24.png"))));
	  private JComboBox new_file_text;
	  private mouse mouseListen;
	  public Panel_Functions()
      {
    	  super();
    	  init();
    	  setFont();
    	  setText();
    	  setColor();
    	  EditObj();
    	  addObj();
    	  addEvent();
      }
      private void setFont()
      {
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
      }
      private void setColor()
      {
    	  login.setBackground(blue);
    	  //login.setForeground(white);
    	  register.setBackground(blue);
    	  //register.setForeground(white);
    	  this.setOpaque(true);
    	  this.setBackground(ColorList.Back_Ground);
    	  content.setOpaque(true);
    	  content.setBackground(ColorList.Back_Ground);
    	  container_icon_function.setOpaque(true);
    	  container_icon_function.setBackground(ColorList.Back_Ground);
    	  pin_to_access_icon.setOpaque(true);
    	  pin_to_access_icon.setBackground(ColorList.Back_Ground);
    	  cut_icon.setOpaque(true);
    	  cut_icon.setBackground(ColorList.Back_Ground);
    	  copy_icon.setOpaque(true);
    	  copy_icon.setBackground(ColorList.Back_Ground);
    	  paste_icon.setOpaque(true);
    	  paste_icon.setBackground(ColorList.Back_Ground);
    	  copy_to_text.setOpaque(true);
    	  copy_to_text.setBackground(ColorList.Back_Ground);
    	  copy_to_icon.setOpaque(true);
    	  copy_to_icon.setBackground(ColorList.Back_Ground);
    	  move_to_text.setOpaque(true);
    	  move_to_text.setBackground(ColorList.Back_Ground);
    	  move_to_icon.setOpaque(true);
    	  move_to_icon.setBackground(ColorList.Back_Ground);
    	  rename_icon.setOpaque(true);
    	  rename_icon.setBackground(ColorList.Back_Ground);
    	  rename_text.setOpaque(true);
    	  rename_text.setBackground(ColorList.Back_Ground);
    	  delete_icon.setOpaque(true);
    	  delete_icon.setBackground(ColorList.Back_Ground);
    	  delete_text.setOpaque(true);
    	  delete_text.setBackground(ColorList.Back_Ground);
    	  new_file_icon.setOpaque(true);
    	  new_file_icon.setBackground(ColorList.Back_Ground);
    	  new_file_text.setOpaque(true);
    	  new_file_text.setBackground(ColorList.Back_Ground);
    	  new_folder_icon.setOpaque(true);
    	  new_folder_icon.setBackground(ColorList.Back_Ground);
    	  new_folder_text.setOpaque(true);
    	  new_folder_text.setBackground(ColorList.Back_Ground);
      }
      private void init()
      {
    	  mouseListen = new mouse(this);
    	  container_icon_function = new JPanel();
    	  content = new JPanel();
    	  login = new JButton();
    	  register = new JButton();
          copy_to_text = new JTextArea();
          move_to_text = new JTextArea();
          rename_text = new JLabel();
          delete_text = new JComboBox();
          new_file_text = new JComboBox();
          new_folder_text = new JLabel();
      }
      private void setText()
      {
    	  login.setText("Đăng nhập");
    	  register.setText("Đăng ký");
    	  pin_to_access_icon.setToolTipText("Thêm vào truy cập nhanh");
    	  cut_icon.setText("Cut");
    	  copy_icon.setText("Copy");
    	  paste_icon.setText("Paste");
    	  copy_to_text.setText("Sao chép\r\nđến");
          move_to_text.setText("Di chuyển\r\nđến");
          rename_text.setText("Đổi tên");
          delete_text.addItem("Xóa");
          new_file_text.addItem("Tệp mới");
          new_folder_icon.setText("Thư mục mới");
      }
      private void addObj()
      {
    	  this.setLayout(new BorderLayout());
    	  //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    	  content.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	  content.add(login);
    	  content.add(register);
    	  
    	  
    	  
    	  container_icon_function.setLayout(new FlowLayout(FlowLayout.LEFT));
    	  container_icon_function.add(pin_to_access_icon);
    	  JPanel panel = new JPanel();
    	  panel.setBorder(new EmptyBorder(0,5,2,5));
    	  panel.setLayout(new GridLayout(3,1,3,3));
    	  panel.setOpaque(true);
    	  panel.setBackground(ColorList.Back_Ground);
    	  panel.add(cut_icon);
    	  panel.add(copy_icon);
    	  panel.add(paste_icon);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(move_to_icon);
    	  panel.add(move_to_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(copy_to_icon);
    	  panel.add(copy_to_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(delete_icon);
    	  panel.add(delete_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(rename_icon);
    	  panel.add(rename_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(new_folder_icon);
    	  panel.add(new_folder_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,0));
    	  panel.add(new_file_icon);
    	  panel.add(new_file_text);
    	  panel.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(panel);
    	  this.add(content, BorderLayout.EAST);
    	  this.add(container_icon_function, BorderLayout.CENTER);
    	  //this.addSeparator();
      }
      public void EditObj()
      {
    	  cut_icon.setHorizontalAlignment(JLabel.LEFT);
    	  copy_icon.setHorizontalAlignment(JLabel.LEFT);
    	  paste_icon.setHorizontalAlignment(JLabel.LEFT);
    	  
      }
      public void addEvent()
      {
    	  this.addMouseMotionListener(mouseListen);
    	  pin_to_access_icon.addMouseMotionListener(mouseListen);
    	  pin_to_access_icon.addMouseListener(mouseListen);
    	  login.addMouseListener(mouseListen);
    	  register.addMouseListener(mouseListen);
      }
      public JButton btlogin()
      {
    	  return login;
      }
      public JButton btregister()
      {
    	  return register;
      }
      public void onclick_Button(JButton bt, int x, int y)
      {
    	  if(bt.equals(login))
    	  {
    		  if(Fregister != null)
    		  {
    			  Fregister.setVisible(false);
    		  }
    		  if(Flogin != null)
    			  if(Flogin.isVisible()) 
    				  Flogin.setVisible(false);
    		  Flogin = new FormLogin();
    		  Flogin.setLocation(x - (x > Flogin.getSize().width ? Flogin.getSize().width : 0), y);
    	  }
    	  else if(bt.equals(register))
    	  {
    		  if(Flogin != null)
    		  {
    			  Flogin.setVisible(false);
    		  }
    		  if(Fregister != null)
    			  if(Fregister.isVisible())
    				  Fregister.setVisible(false);
    		  Fregister = new FormRegister(); 		  
    		  Fregister.setLocation(x - (x > Fregister.getSize().width ? Fregister.getSize().width : 0), y);
    	  }
    		   
      }
      public void setBackGroundIconFunction(int hash, Color color)
      {
			
    	  if(hash == pin_to_access_icon.hashCode())
    	  {
    		  if(pin_to_access_icon.isEnabled())
    		  pin_to_access_icon.setBackground(color);
    	  } 
      }
}
