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
import java.awt.im.InputContext;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.LineBorder;

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
	  private JLabel delete_text;
	  private JLabelIcon rename_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("rename32.png"))));
	  private JLabel rename_text;
	  private JLabelIcon new_folder_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("new_folder24.png"))));
	  private JLabel new_folder_text;
	  private JLabelIcon new_file_icon = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("new_file24.png"))));
	  private JLabel new_file_text;
	  private JTextArea pinTo_text;
	  private JPanel Cut;
	  private JPanel Copy;
	  private JPanel Paste;
	  private JPanel PinTo;
	  private JPanel MoveTo;
	  private JPanel CopyTo;
	  private JPanel Delete;
	  private JPanel Rename;
	  private JPanel NewFile;
	  private JPanel NewFolder;
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
          pinTo_text.setFont(FONT.font_11);
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
    	  NewFile.setOpaque(true);
    	  NewFile.setBackground(ColorList.Back_Ground);
    	  NewFolder.setOpaque(true);
    	  NewFolder.setBackground(ColorList.Back_Ground);

    	  //;
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
          delete_text = new JLabel();
          new_file_text = new JLabel();
          new_folder_text = new JLabel();
          pinTo_text = new JTextArea();
          PinTo = new JPanel();
          Cut = new JPanel();
          Copy = new JPanel();
          Paste = new JPanel();
          MoveTo = new JPanel();
          CopyTo = new JPanel();
          Delete = new JPanel();
          Rename = new JPanel();
          NewFile = new JPanel();
          NewFolder = new JPanel();
        		  
      }
      private void setText()
      {
    	  login.setText("Đăng nhập");
    	  register.setText("Đăng ký");
    	  pinTo_text.setText("    Ghim vào \ntruy cập nhanh");
    	  pin_to_access_icon.setToolTipText("Thêm vào truy cập nhanh");
    	  cut_icon.setText("Cut");
    	  copy_icon.setText("Copy");
    	  paste_icon.setText("Paste");
    	  copy_to_text.setText("Sao chép\r\n    đến");
          move_to_text.setText("Di chuyển\r\n     đến");
          rename_text.setText("Đổi tên");
          delete_text.setText("Xóa");
          new_file_text.setText("Tệp mới");
          new_folder_text.setText("Thư mục mới");
      }
      private void addObj()
      {
    	  this.setLayout(new BorderLayout());
    	  //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    	  content.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	  content.add(login);
    	  content.add(register);
    	  
    	  
    	  
    	  container_icon_function.setLayout(new FlowLayout(FlowLayout.LEFT));
    	  
    	  PinTo.setLayout(new GridLayout(2,1));
    	  PinTo.add(pin_to_access_icon);
    	  PinTo.add(pinTo_text);
    	  container_icon_function.add(PinTo);
    	  
    	  JPanel panel = new JPanel();
    	  panel.setBorder(new EmptyBorder(0,5,2,5));
    	  panel.setLayout(new GridLayout(3,1,0,0));
    	  panel.setOpaque(true);
    	  panel.setBackground(ColorList.Back_Ground);
    	  panel.add(cut_icon);
    	  panel.add(copy_icon);
    	  panel.add(paste_icon);
    	  container_icon_function.add(panel);
    	  
    	  MoveTo.setLayout(new GridLayout(2,1,0,0));
    	  MoveTo.add(move_to_icon);
    	  MoveTo.add(move_to_text);
    	  MoveTo.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(MoveTo);
    	  
    	  CopyTo.setLayout(new GridLayout(2,1,0,0));
    	  CopyTo.add(copy_to_icon);
    	  CopyTo.add(copy_to_text);
    	  CopyTo.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(CopyTo);
    	  
    	  Delete.setLayout(new GridLayout(2,1,0,0));
    	  Delete.add(delete_icon);
    	  Delete.add(delete_text);
    	  Delete.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(Delete);
    	  
    	  Rename.setLayout(new GridLayout(2,1,0,-8));
    	  Rename.add(rename_icon);
    	  Rename.add(rename_text);
    	  Rename.setBackground(ColorList.Back_Ground);
    	  container_icon_function.add(Rename);
    	  
    	  panel = new JPanel();
    	  panel.setLayout(new GridLayout(2,1,0,-5));
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
    	  
    	  this.add(content, BorderLayout.EAST);
    	  this.add(container_icon_function, BorderLayout.CENTER);
    	  //this.addSeparator();
      }
      public void EditObj()
      {
    	  cut_icon.setHorizontalAlignment(JLabel.LEFT);
    	  copy_icon.setHorizontalAlignment(JLabel.LEFT);
     	  paste_icon.setHorizontalAlignment(JLabel.LEFT);
    	  
    	  pinTo_text.setEditable(false);
    	  copy_to_text.setEditable(false);
          move_to_text.setEditable(false); 
          
          Delete.setBorder(new EmptyBorder(8,10,8,10));
          Rename.setBorder(new EmptyBorder(4,4,4,4));
          cut_icon.setBorder(new EmptyBorder(3,3,3,3));
          copy_icon.setBorder(new EmptyBorder(3,3,3,3));
          paste_icon.setBorder(new EmptyBorder(3,3,3,3));
      }
      public void addEvent()
      {
    	  this.addMouseMotionListener(mouseListen);
    	  login.addMouseListener(mouseListen);
    	  register.addMouseListener(mouseListen);

    	  pin_to_access_icon.addMouseMotionListener(mouseListen);
    	  pinTo_text.addMouseMotionListener(mouseListen);
    	  pin_to_access_icon.addMouseListener(mouseListen);
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
    	  
    	  rename_icon.addMouseListener(mouseListen);
    	  rename_icon.addMouseMotionListener(mouseListen);
    	  rename_text.addMouseListener(mouseListen);
    	  rename_text.addMouseMotionListener(mouseListen);
    	  
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
      public void setHover(int hash, Color color)
      {
    	  
    	  if(hash == pin_to_access_icon.hashCode() || hash == pinTo_text.hashCode())
    	  {
    		  if(PinTo.isEnabled()) {
    	        pin_to_access_icon.setBackground(color);
    		    pinTo_text.setBackground(color);
    		  }
    	  }
    	  else if(hash == cut_icon.hashCode())
    	  {
    		  if(cut_icon.isEnabled()) {
      	        cut_icon.setBackground(color);
      		  }
    	  }
    	  else if(hash == copy_icon.hashCode())
    	  {
    		  if(copy_icon.isEnabled()) {
                copy_icon.setBackground(color);
      		  }
    	  }
    	  else if(hash == paste_icon.hashCode())
    	  {
    		  if(paste_icon.isEnabled()) {
                paste_icon.setBackground(color);
      		  }
    	  }
    	  else if(hash == delete_icon.hashCode() || hash == delete_text.hashCode())
    	  {
    		  if(Delete.isEnabled()) {
    			Delete.setBackground(color);
      	        delete_icon.setBackground(color);
      	        delete_text.setBackground(color);
      		  }
    	  }
    	  else if(hash == rename_icon.hashCode() || hash == rename_text.hashCode())
    	  {
    		  if(Rename.isEnabled()) {
      	        rename_icon.setBackground(color);
      	        rename_text.setBackground(color);
      	        Rename.setBackground(color);
      		  }
    	  }
    	  else if(hash == new_file_icon.hashCode() || hash == new_file_text.hashCode() || hash == NewFile.hashCode())
    	  {
    		  if(NewFile.isEnabled()) {
      	        new_file_icon.setBackground(color);
      	        new_file_text.setBackground(color);
      	        NewFile.setBackground(color);
      		  }
    	  }
    	  else if(hash == new_folder_icon.hashCode() || hash == new_folder_text.hashCode() || hash == NewFolder.hashCode())
    	  {
    		  if(NewFolder.isEnabled()) {
    			NewFolder.setBackground(color);
      	        new_folder_icon.setBackground(color);
      	        new_folder_text.setBackground(color);
      		  }
    	  }
    	  else if(hash == move_to_icon.hashCode() || hash == move_to_text.hashCode())
    	  {
    		  if(MoveTo.isEnabled())
    		  {
    			  move_to_icon.setBackground(color);
    			  move_to_text.setBackground(color);
    		  }
    	  }
    	  else if(hash == copy_to_icon.hashCode() || hash == copy_to_text.hashCode())
    	  {
    		  if(CopyTo.isEnabled())
    		  {
    			  copy_to_icon.setBackground(color);
    			  copy_to_text.setBackground(color);
    		  }
    	  }
      }
}