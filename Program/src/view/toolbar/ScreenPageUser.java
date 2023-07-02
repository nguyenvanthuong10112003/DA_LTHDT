package view.toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controller.mouse;
import java.awt.*;
import define.ColorList;
import define.FONT;
import define.URL;
import libary.ConnectSQL;
import libary.DATE;
import libary.JTextFieldPassWord;
import view.screen.Screen;
import controller.action;

public class ScreenPageUser extends JFrame {
		private Panel_Functions fun;
		private JPanel container;
		private JPanel contentTTCN;
		private JPanel contentDMK;
		private String icon = "user16px.png";
		private String iconAvatar = "user64px.png";
		private String iconEdit = "edit16px.png";
		private String iconSave = "save16px.png";
		private model.User user;
		private JLabel ttcn;
		private JLabel dmk;
		private JLabel avatar;
		private JLabel textUsername;
		private JLabel logOut;
		private JLabel textName;
		private JLabel textAddress;
		private JLabel textBirth;
		private JLabel textCreate;
		private JLabel textPhoneNumber;
		private JLabel textEmail;
		private JLabel textSex;
		private JComponent Name;
		private JComponent Address;
		private JComponent Birth;
		private JLabel Create;
		private JComponent PhoneNumber;
		private JComponent Email;
		private JComponent Sex;
		private JLabel textPass;
		private JTextFieldPassWord inputPass;
		private JLabel textNew;
		private JTextFieldPassWord inputnewPass;
		private JLabel textcheck;
		private JTextFieldPassWord inputcheckPass;
		private JLabel cancel;
		private JLabel acept;
		private JTextField inputName;
		private JTextField inputAddress;
		private JComboBox day;
		private JComboBox month;
		private JComboBox year;
		private JPanel inputBirth;
		private JTextField inputPhoneNumber;
		private JTextField inputEmail;
		private JRadioButton nam;
		private JRadioButton nu;
		private ButtonGroup gb;
		private JPanel inputSex;
		private JLabel []arrButton = new JLabel[7];
		private JButton []arrButtonEdit = new JButton[7];
		private JButton []arrButtonSave = new JButton[7];
		private mouse mouseListen;
		private action actionListen;
		private DATE date = new DATE(LocalDateTime.now().getYear());
		private Font fontb = new Font("Arial", Font.BOLD, 14);
		public enum Select{
			TTCN, DMK
		}
		private Select selection = Select.TTCN;
		
		public ScreenPageUser(model.User user, Panel_Functions fun)
		{
			this.fun = fun;
			this.user = user;
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			setResizable(false);
			setSize(600, 350);
			init();
			setTime();
			setButton();
			addObj();
			setText();
			Edit();
			addEvent();
			setIconImage(new ImageIcon(URL.url + URL.urlToolBarFun + icon).getImage());
			setTitle("Thông tin người dùng");
			setVisible(true);
		}
		
		private void init()
		{
			actionListen = new action(this);
			mouseListen = new mouse(this);
			gb = new ButtonGroup();
			nam = new JRadioButton();
			nu = new JRadioButton();
			year = new JComboBox();
			month = new JComboBox();
			day = new JComboBox();
			container = new JPanel();
			avatar = new JLabel(new ImageIcon(URL.url + URL.urlToolBarFun + iconAvatar));
			textName = new JLabel("Họ và tên: ");
			textAddress = new JLabel("Địa chỉ: ");
			textBirth = new JLabel("Ngày sinh: ");
			textCreate = new JLabel("Ngày tạo: ");
			textPhoneNumber = new JLabel("Số điện thoại: ");
			textEmail = new JLabel("Email: ");
			textSex = new JLabel("Giới tính: ");
			Name = new JLabel(user.getName());
			Address = new JLabel(user.getAddress());
			Birth = new JLabel(user.stringDateOfBirth());
			Create = new JLabel(user.getTime(user.getDateCreated()));
			PhoneNumber = new JLabel(user.getPhoneNumber());
			Email = new JLabel(user.getEmail());
			Sex = new JLabel(user.getSex() == null ? "" : (user.getSex() == false ? "Nữ" : "Nam"));
			ttcn = new JLabel("Thông tin cá nhân");
			dmk = new JLabel("Đổi mật khẩu");
			contentDMK = new JPanel();
			contentTTCN = new JPanel();
			inputName = new JTextField();
			inputAddress = new JTextField();
			inputEmail = new JTextField();
			inputPhoneNumber = new JTextField();
			inputBirth = new JPanel();
			inputSex = new JPanel();
			textUsername = new JLabel(user.getTenDangNhap());
			logOut = new JLabel("Đăng xuất");
			inputPass = new JTextFieldPassWord();
			inputnewPass = new JTextFieldPassWord();
			inputcheckPass = new JTextFieldPassWord();
			acept = new JLabel("Đổi mật khẩu");
			cancel = new JLabel("Hủy");
			textPass = new JLabel("Mật khẩu hiện tại");
			textNew = new JLabel("Mật khẩu mới");
			textcheck = new JLabel("Nhập lại mật khẩu mới");
		}
		
		private void addObj()
		{
			avatar.add(textUsername);
			avatar.add(logOut);
			
			gb.add(nam);
			gb.add(nu);
			setLayout(new BorderLayout());
			container.setLayout(null);
			container.add(avatar);
			container.add(ttcn);
			container.add(dmk);
			add(container, BorderLayout.CENTER);
			
			inputSex.setLayout(new FlowLayout(FlowLayout.LEFT));
			inputSex.add(nam);
			inputSex.add(nu);
			inputBirth.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 0;
			c.gridx = 0;
			c.gridy = 0;
			inputBirth.add(new JLabel("Ngày: "), c);
			c.weightx = 0.5;
			c.gridx = 1;
			c.gridy = 0;
			inputBirth.add(day, c);
			c.weightx = 0;
			c.gridx = 2;
			c.gridy = 0;
			inputBirth.add(new JLabel(" Tháng: "), c);
			c.weightx = 0.5;
			c.gridx = 3;
			c.gridy = 0;
			inputBirth.add(month, c);
			c.weightx = 0;
			c.gridx = 4;
			c.gridy = 0;
			inputBirth.add(new JLabel(" Năm: "), c);
			c.weightx = 0.5; 
			c.gridx = 5;
			c.gridy = 0;
			inputBirth.add(year, c);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(ColorList.Back_Ground);
			panel.add(textName);
			panel.add(Name);
			panel.add(inputName);
			panel.add(arrButton[0]);
			
			panel.add(textSex);
			panel.add(Sex);
			panel.add(inputSex);
			panel.add(arrButton[1]);
			
			panel.add(textBirth);
			panel.add(Birth);
			panel.add(inputBirth);
			panel.add(arrButton[2]);
			
			panel.add(textAddress);
			panel.add(Address);
			panel.add(inputAddress);
			panel.add(arrButton[3]);
			
			panel.add(textPhoneNumber);
			panel.add(PhoneNumber);
			panel.add(inputPhoneNumber);
			panel.add(arrButton[4]);
			
			panel.add(textEmail);
			panel.add(Email);
			panel.add(inputEmail);
			panel.add(arrButton[5]);
			
			panel.add(textCreate);
			panel.add(Create);
			
			contentTTCN.setLayout(new BorderLayout());
			contentTTCN.add(panel, BorderLayout.CENTER);
			
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(textPass);
			panel.add(inputPass);
			panel.add(textNew);
			panel.add(inputnewPass);
			panel.add(textcheck);
			panel.add(inputcheckPass);
			panel.add(cancel);
			panel.add(acept);
			
			panel.setBackground(ColorList.Back_Ground);
			contentDMK.setLayout(new BorderLayout());
			contentDMK.add(panel, BorderLayout.CENTER);
			
			container.setLayout(new BorderLayout());
			container.add(contentTTCN, BorderLayout.CENTER);
		}
		
		private void Edit()
		{
			int border = 30;
			int space = 2;
			setBackground(ColorList.Back_Ground);
			container.setBackground(ColorList.Back_Ground);
			nam.setBackground(ColorList.Back_Ground);
			nu.setBackground(ColorList.Back_Ground);
			
			avatar.setBounds(0, 0, 64 + border*2, 110 + border);
			avatar.setBorder(new EmptyBorder(20, 0, 0, 0));
			avatar.setHorizontalAlignment(JLabel.CENTER);
			avatar.setVerticalAlignment(JLabel.TOP);
			avatar.setOpaque(true);
			avatar.setBackground(ColorList.Back_Ground);
			
			logOut.setBounds(space, avatar.getSize().height - 30 - space, avatar.getSize().width - space*2, 30);
			logOut.setHorizontalAlignment(JLabel.CENTER);
			logOut.setVerticalAlignment(JLabel.CENTER);
			logOut.setOpaque(true);
			logOut.setBackground(ColorList.Fore_Ground);
			logOut.setForeground(ColorList.Back_Ground);
			logOut.setBorder(new LineBorder(ColorList.Fore_Ground));
			
			textUsername.setBounds(space, logOut.getBounds().y - 20 - space, avatar.getSize().width - space*2, 20);
			textUsername.setHorizontalAlignment(JLabel.CENTER);
			textUsername.setVerticalAlignment(JLabel.CENTER);
			textUsername.setForeground(ColorList.Fore_Ground);
			
			ttcn.setBounds(0, avatar.getSize().height + space, avatar.getSize().width + 2, 30);
			ttcn.setHorizontalAlignment(JLabel.CENTER);
			ttcn.setVerticalAlignment(JLabel.CENTER);
			ttcn.setOpaque(true);
			ttcn.setBackground(ColorList.Back_Ground);
			ttcn.setBorder(new LineBorder(ColorList.Back_Ground));
			ttcn.setForeground(ColorList.Fore_Ground);
			
			dmk.setBounds(0, ttcn.getBounds().y + ttcn.getBounds().height + space, avatar.getSize().width, 30);
			dmk.setHorizontalAlignment(JLabel.CENTER);
			dmk.setVerticalAlignment(JLabel.CENTER);
			dmk.setOpaque(true);
			dmk.setBackground(new Color(126, 73, 161));
			dmk.setBorder(new LineBorder(ColorList.Back_Ground));
			dmk.setForeground(ColorList.Back_Ground);
			
			contentTTCN.setBackground(new Color(126, 73, 161));
			contentTTCN.setBorder(new EmptyBorder(0,avatar.getSize().width + space,0,0));
			
			contentDMK.setBackground(new Color(126, 73, 161));
			contentDMK.setBorder(new EmptyBorder(0,avatar.getSize().width + space,0,0));
			contentDMK.setVisible(false);
			
			textName.setBounds(30,50,90,30);
			textSex.setBounds(textName.getBounds().x, textName.getSize().height + textName.getBounds().y, textName.getSize().width, textName.getSize().height);
			textBirth.setBounds(textName.getBounds().x, textName.getSize().height + textSex.getBounds().y, textName.getSize().width, textName.getSize().height);
			textAddress.setBounds(textName.getBounds().x, textName.getSize().height + textBirth.getBounds().y, textName.getSize().width, textName.getSize().height);
			textPhoneNumber.setBounds(textName.getBounds().x, textName.getSize().height + textAddress.getBounds().y, textName.getSize().width, textName.getSize().height);
			textEmail.setBounds(textName.getBounds().x, textName.getSize().height + textPhoneNumber.getBounds().y, textName.getSize().width, textName.getSize().height);
			textCreate.setBounds(textName.getBounds().x, textName.getSize().height + textEmail.getBounds().y, textName.getSize().width, textName.getSize().height);
			
			inputName.setBounds(textName.getBounds().x + textName.getSize().width, textName.getBounds().y + space, 250, textName.getSize().height - space*2);
			inputSex.setBounds(textName.getBounds().x + textName.getSize().width, textSex.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			inputBirth.setBounds(textName.getBounds().x + textName.getSize().width, textBirth.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			inputAddress.setBounds(textName.getBounds().x + textName.getSize().width, textAddress.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			inputPhoneNumber.setBounds(textName.getBounds().x + textName.getSize().width, textPhoneNumber.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			inputEmail.setBounds(textName.getBounds().x + textName.getSize().width, textEmail.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			
			Name.setBounds(textName.getBounds().x + textName.getSize().width, textName.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			Sex.setBounds(textName.getBounds().x + textName.getSize().width, textSex.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			Birth.setBounds(textName.getBounds().x + textName.getSize().width, textBirth.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			Address.setBounds(textName.getBounds().x + textName.getSize().width, textAddress.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			PhoneNumber.setBounds(textName.getBounds().x + textName.getSize().width, textPhoneNumber.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			Email.setBounds(textName.getBounds().x + textName.getSize().width, textEmail.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			Create.setBounds(textName.getBounds().x + textName.getSize().width, textCreate.getBounds().y + space, inputName.getSize().width, textName.getSize().height - space*2);
			
			arrButton[0].setBounds(Name.getSize().width + Name.getBounds().x + space, Name.getBounds().y, 70, Name.getSize().height);
			arrButton[1].setBounds(Name.getSize().width + Name.getBounds().x + space, Sex.getBounds().y, 70, Name.getSize().height);
			arrButton[2].setBounds(Name.getSize().width + Name.getBounds().x + space, Birth.getBounds().y, 70, Name.getSize().height);
			arrButton[3].setBounds(Name.getSize().width + Name.getBounds().x + space, Address.getBounds().y, 70, Name.getSize().height);
			arrButton[4].setBounds(Name.getSize().width + Name.getBounds().x + space, PhoneNumber.getBounds().y, 70, Name.getSize().height);
			arrButton[5].setBounds(Name.getSize().width + Name.getBounds().x + space, Email.getBounds().y, 70, Name.getSize().height);
			
			inputBirth.setBackground(ColorList.Back_Ground);
			inputSex.setBackground(ColorList.Back_Ground);
			inputSex.setBorder(new EmptyBorder(-4, 0, 0, 0));
			inputName.setVisible(false);
			inputSex.setVisible(false);
			inputBirth.setVisible(false);
			inputAddress.setVisible(false);
			inputPhoneNumber.setVisible(false);
			inputEmail.setVisible(false);
			
			textPass.setVerticalAlignment(JLabel.BOTTOM);
			textPass.setBounds(70, 50, 150, 20);
			inputPass.setBounds(textPass.getBounds().x, textPass.getBounds().y + textPass.getSize().height, textPass.getSize().width, 30);
			
			textNew.setVerticalAlignment(JLabel.BOTTOM);
			textNew.setBounds(textPass.getBounds().x, inputPass.getBounds().y + inputPass.getSize().height + space*2, textPass.getSize().width, textPass.getSize().height);
			inputnewPass.setBounds(textPass.getBounds().x, textNew.getBounds().y + textNew.getSize().height, textPass.getSize().width, inputPass.getSize().height);
			
			textcheck.setVerticalAlignment(JLabel.BOTTOM);
			textcheck.setBounds(textPass.getBounds().x, inputnewPass.getBounds().y + inputnewPass.getSize().height + space*2, textPass.getSize().width, textPass.getSize().height);
			inputcheckPass.setBounds(textPass.getBounds().x, textcheck.getBounds().y + textcheck.getSize().height, textPass.getSize().width, inputPass.getSize().height);
			
			acept.setBounds(textPass.getBounds().x , inputcheckPass.getBounds().y + inputcheckPass.getSize().height + 5, textPass.getSize().width, 30);
			
			acept.setOpaque(true);
			acept.setBackground(ColorList.Fore_Ground);
			acept.setForeground(ColorList.Back_Ground);
			acept.setVerticalAlignment(JLabel.CENTER);
			acept.setHorizontalAlignment(JLabel.CENTER);
			acept.setBorder(new LineBorder(ColorList.Fore_Ground));
		}
		
		private void setButton()
		{
			for(int i = 0; i < 6; i++)
			{
				arrButton[i] = new JLabel();
				arrButton[i].setLayout(new GridLayout(1,2,5,5));
				arrButtonEdit[i] = new JButton();
				arrButtonSave[i] = new JButton();
				arrButton[i].add(arrButtonEdit[i]);
				arrButton[i].add(arrButtonSave[i]);
				arrButtonSave[i].setEnabled(false);
				arrButtonEdit[i].setIcon(new ImageIcon(URL.url + URL.urlToolBarFun + iconEdit));
				arrButtonSave[i].setIcon(new ImageIcon(URL.url + URL.urlToolBarFun + iconSave));
				arrButtonEdit[i].addActionListener(actionListen);
				arrButtonSave[i].addActionListener(actionListen);
			}
		}
		
		private void setText()
		{
			Font fontD = new Font("Arial", Font.BOLD, 13);
			Font font = new Font("Arial", Font.PLAIN, 13);
			day.setToolTipText("ngày");
			month.setToolTipText("tháng");
			year.setToolTipText("năm");
			nam.setText("Nam");
			nam.setFont(font);
			nu.setText("Nữ");
			nu.setFont(font);
			dmk.setFont(fontD);
			ttcn.setFont(fontD);
			textName.setFont(font);
			textAddress.setFont(font);
			textBirth.setFont(font);
			textCreate.setFont(font);
			textPhoneNumber.setFont(font);
			textEmail.setFont(font);
			textSex.setFont(font);
			Name.setFont(font);
			Address.setFont(font);
			Birth.setFont(font);
			Create.setFont(font);
			PhoneNumber.setFont(font);
			Email.setFont(font);
			Sex.setFont(font);
			logOut.setFont(fontD);
			textUsername.setFont(fontD);
			acept.setFont(fontD);
			inputAddress.setFont(font);
			inputcheckPass.setFont(font);
			inputEmail.setFont(font);
			inputName.setFont(font);
			inputnewPass.setFont(font);
			inputPass.setFont(font);
			inputPhoneNumber.setFont(font);
			textPass.setFont(font);
			textcheck.setFont(font);
			textNew.setFont(font);
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

				public void actionPerformed(ActionEvent e) {
					updateTime();
				}
			});
			month.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					updateTime();
				}
			});
			day.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
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
			int d = (Integer) day.getSelectedItem();
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
			if (d <= (Integer) day.getItemAt(day.getItemCount() - 1))
				day.setSelectedItem(d);
			else
				day.setSelectedItem((Integer) day.getItemAt(day.getItemCount() - 1));

		}
		
		public void addEvent()
		{
			ttcn.addMouseListener(mouseListen);
			ttcn.addMouseMotionListener(mouseListen);
			dmk.addMouseListener(mouseListen);
			dmk.addMouseMotionListener(mouseListen);
			logOut.addMouseListener(mouseListen);
			logOut.addMouseMotionListener(mouseListen);
			acept.addMouseListener(mouseListen);
			acept.addMouseMotionListener(mouseListen);
		}
		
		public void hover(int code)
		{
			if(code == ttcn.hashCode())
			{
				ttcn.setBackground(ColorList.Back_Ground);
				ttcn.setForeground(ColorList.Fore_Ground);
			} else if(code == dmk.hashCode())
			{
				dmk.setBackground(ColorList.Back_Ground);
				dmk.setForeground(ColorList.Fore_Ground);
			} else if(code == logOut.hashCode())
			{
				logOut.setBackground(ColorList.Back_Ground);
				logOut.setForeground(ColorList.Fore_Ground);
			} else if(code == acept.hashCode())
			{
				acept.setBackground(ColorList.Back_Ground);
				acept.setForeground(ColorList.Fore_Ground);
			}
		}
		
		public void exit(int code)
		{
			if(code == ttcn.hashCode() && !selection.equals(Select.TTCN))
			{
				ttcn.setBackground(new Color(126, 73, 161));
				ttcn.setForeground(ColorList.Back_Ground);
			} else if(code == dmk.hashCode() && !selection.equals(Select.DMK))
			{
				dmk.setBackground(new Color(126, 73, 161));
				dmk.setForeground(ColorList.Back_Ground);
			} else if(code == logOut.hashCode())
			{
				logOut.setBackground(ColorList.Fore_Ground);
				logOut.setForeground(ColorList.Back_Ground);
			} else if(code == acept.hashCode())
			{
				acept.setBackground(ColorList.Fore_Ground);
				acept.setForeground(ColorList.Back_Ground);
			}
		}
		
		public void update()
		{
			addObj();
			setText();
			Edit();
			addEvent();
		}
		
		public void edit(int i)
		{
			if(i == 0)
			{
				Name.setVisible(false);
				inputName.setVisible(true);
				inputName.setText(user.getName());
			} else if(i == 1)
			{
				Sex.setVisible(false);
				inputSex.setVisible(true);
				if(user.getSex() != null)
					if(user.getSex())
						nam.setSelected(true);
					else
						nu.setSelected(false);
			} else if(i == 2)
			{
				Birth.setVisible(false);
				inputBirth.setVisible(true);
				if(user.getDateOfBirth() != null)
				{
					day.setSelectedItem(user.getDateOfBirth().getDay());
					month.setSelectedItem(user.getDateOfBirth().getMonth());
					year.setSelectedItem(user.getDateOfBirth().getYear());
				} 
			} else if(i == 3)
			{
				Address.setVisible(false);
				inputAddress.setVisible(true);
				inputAddress.setText(user.getAddress());
			} else if(i == 4)
			{
				PhoneNumber.setVisible(false);
				inputPhoneNumber.setVisible(true);
				inputPhoneNumber.setText(user.getPhoneNumber());
			} else if(i == 5)
			{
				Email.setVisible(false);
				inputEmail.setVisible(true);
				inputEmail.setText(user.getEmail());
			}
		}
		
		public Boolean sosanh(String a, String b)
		{
			a.trim();
			b.trim();
			if(a.equals(b))
				return true;
			return false;
		}
		
		public void save(int i) throws ClassNotFoundException, SQLException
		{
			if(i == 0)
			{
				if(!sosanh(user.getName(), inputName.getText()))
				{
					user.setName(inputName.getText().trim());
				}
				Name.setVisible(!false);
				inputName.setVisible(!true);
				((JLabel) Name).setText(user.getName());
			} else if(i == 1)
			{
				if(nam.isSelected() || nu.isSelected())
				{
					user.setSex(nam.isSelected());
					Sex.setVisible(!false);
					inputSex.setVisible(!true);
					((JLabel) Sex).setText(user.getSex() == true ? "Nam" : "Nữ");
				}
			} else if(i == 2)
			{
				user.setDateOfBirth(new Date((Integer)year.getSelectedItem(), (Integer)month.getSelectedItem(), (Integer)day.getSelectedItem()));
				Birth.setVisible(!false);
				inputBirth.setVisible(!true);
				((JLabel) Birth).setText(user.stringDateOfBirth());
			} else if(i == 3)
			{
				if(!sosanh(inputAddress.getText(), user.getAddress())) {
					user.setAddess(inputAddress.getText().trim());
				}
				Address.setVisible(!false);
				inputAddress.setVisible(!true);
				((JLabel) Address).setText(user.getAddress());
			} else if(i == 4)
			{
				if(!sosanh(inputPhoneNumber.getText(), user.getPhoneNumber())) {
					user.setPhoneNumber(inputPhoneNumber.getText().trim());
				}
				PhoneNumber.setVisible(!false);
				inputPhoneNumber.setVisible(!true);
				((JLabel) PhoneNumber).setText(user.getPhoneNumber());
			} else if(i == 5)
			{
				if(!sosanh(inputEmail.getText(), user.getEmail())) {
					user.setEmail(inputEmail.getText().trim());
				}
				Email.setVisible(!false);
				inputEmail.setVisible(!true);
				((JLabel) Email).setText(user.getEmail());
			}
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
				Statement sta = connect.createStatement();
				try {
					user.updateToSql(sta);
				} catch (Exception e) {
					e.printStackTrace();
					sta.close();
					connect.close();
					return;
				}
				sta.close();
				connect.close();
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
		}
		
		public void DoiMatKhau() throws ClassNotFoundException, SQLException
		{
			String pass = inputPass.getText().trim();
			String newPass = inputnewPass.getText().trim();
			String checkPass = inputcheckPass.getText().trim();
			if("".equals(pass) || "".equals(newPass) || "".equals(checkPass))
			{
				JOptionPane.showMessageDialog(inputnewPass, "Yêu cầu nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!inputnewPass.getPass().equals(inputcheckPass.getPass()))
			{
				JOptionPane.showMessageDialog(inputnewPass, "Mật khẩu mới không trùng khớp!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!inputPass.getPass().trim().equals(user.getPassWord()))
			{
				JOptionPane.showMessageDialog(inputnewPass, "Mật khẩu hiện tại sai!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			user.setPassWord(inputnewPass.getPass());
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
				Statement sta = connect.createStatement();
				try {
					user.UpdatePassWordToSql(sta);
				} catch (Exception e) {
					e.printStackTrace();
					sta.close();
					connect.close();
					return;
				}
				sta.close();
				connect.close();
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
			JOptionPane.showMessageDialog(inputnewPass, "Thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			inputcheckPass.setText("");
			inputPass.setText("");
			inputnewPass.setText("");
		}
		
		public void clicked(int code)
		{
			if(code == ttcn.hashCode())
			{
				if(!selection.equals(Select.TTCN))
				{
					dmk.setBackground(new Color(126, 73, 161));
					dmk.setForeground(ColorList.Back_Ground);
					ttcn.setBackground(ColorList.Back_Ground);
					ttcn.setForeground(ColorList.Fore_Ground);
					selection = Select.TTCN;
					contentDMK.setVisible(false);
					contentTTCN.setVisible(true);
					ttcn.setSize(ttcn.getSize().width + 2, ttcn.getSize().height);
					dmk.setSize(dmk.getSize().width - 2, dmk.getSize().height);
					container.add(contentTTCN, BorderLayout.CENTER);
				}
			} else if(code == dmk.hashCode())
			{
				if(!selection.equals(Select.DMK))
				{
					ttcn.setBackground(new Color(126, 73, 161));
					ttcn.setForeground(ColorList.Back_Ground);
					dmk.setBackground(ColorList.Back_Ground);
					dmk.setForeground(ColorList.Fore_Ground);
					selection = Select.DMK;
					contentDMK.setVisible(!false);
					contentTTCN.setVisible(!true);
					ttcn.setSize(ttcn.getSize().width - 2, ttcn.getSize().height);
					dmk.setSize(dmk.getSize().width + 2, dmk.getSize().height);
					container.add(contentDMK, BorderLayout.CENTER);
					inputcheckPass.setText("");
					inputPass.setText("");
					inputnewPass.setText("");
				}
			} else if(code == logOut.hashCode())
			{
				setVisible(false);
				fun.logOut();
			} else if(code == acept.hashCode())
			{
				try {
					DoiMatKhau();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else 
			{
				for(int i = 0; i < 6; i++)
				{
					if(code == arrButtonEdit[i].hashCode())
					{
						if(!arrButtonSave[i].isEnabled()) {
							arrButtonSave[i].setEnabled(true);
							edit(i);
						}
						return;
					}
					if(code == arrButtonSave[i].hashCode())
					{
						arrButtonSave[i].setEnabled(false);
						try {
							save(i);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			} 
		}
}
