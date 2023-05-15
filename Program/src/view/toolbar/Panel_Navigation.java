package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import libary.ColorList;
import libary.FONT;
import libary.JLabelIcon;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.mouse;

public class Panel_Navigation extends JPanel{
	private JLabel iconback;
	private JLabel iconforward;
	private String back = "back16.png";
	private String forward = "forward16.png";
	private mouse mouselisten;      
	private JPanel address;
	private JTextField input_show;
	private JComboBox search;
	private Boolean in_input = false;
	public Panel_Navigation()
	{
		super();
		this.setBorder(new LineBorder(Color.black));
		this.init();
		this.EditObj();
		this.setFonts();
		this.addEvent();
		this.addObj();
	}
	private void init()
	{
		mouselisten = new mouse(this);
		iconback = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(back))));
		iconback.setToolTipText("back");
		iconforward = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(forward))));
		iconforward.setToolTipText("forward");
		address = new JPanel();
		input_show = new JTextField();
		input_show.setText("Địa chỉ");
		search = new JComboBox();
		search.addItem("Tìm kiếm");
	}
	private void EditObj()
	{
		iconback.setOpaque(true);
		iconforward.setOpaque(true);
		iconback.setBackground(ColorList.Back_Ground);
		iconforward.setBackground(ColorList.Back_Ground);
		
		address.setBorder(new LineBorder(Color.BLACK));
		address.setOpaque(true);
		address.setBackground(ColorList.Back_Ground);
		search.setOpaque(true);
		input_show.setBorder(new EmptyBorder(0,5,0,0));
		input_show.setAlignmentX(JTextField.CENTER);
		search.setBorder(new LineBorder(Color.BLACK));
		search.setEditable(true);
		input_show.setEditable(false);
		input_show.setBackground(ColorList.Back_Ground);
		//
	}
	private void setFonts()
	{
		input_show.setFont(FONT.font_mac_dinh);
		search.setFont(FONT.font_mac_dinh);
	}
	private void addEvent()
	{
		iconback.addMouseListener(mouselisten);
		iconback.addMouseMotionListener(mouselisten);
		iconforward.addMouseListener(mouselisten);
		iconforward.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		input_show.addMouseListener(mouselisten);
		search.addMouseListener(mouselisten);
	}
	public void mouseOnclick(int hash)
	{
		if(hash == input_show.hashCode())
		{
			if(!in_input) {
			input_show.setEditable(true);
			in_input = true;
			}
		}
		else if(hash == search.hashCode())
		{
			if(input_show.isEditable()) {
			input_show.setEditable(false);
			in_input = false;
			}
		}
		else
		{
			if(input_show.isEditable()) {
			input_show.setEditable(false);
			in_input = false;
			}
		}
	}
	private void addObj()
	{
		this.setLayout(new BorderLayout());		
		
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEFT));
		pn.add(iconback, BorderLayout.WEST);
		pn.add(iconforward);
		pn.setOpaque(true);
		pn.setBackground(ColorList.Back_Ground);
		this.add(pn, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2,4,2,4));
		panel.setLayout(new GridLayout(1,2,5,5));
		panel.setOpaque(true);
		panel.setBackground(ColorList.Back_Ground);
		address.setLayout(new BorderLayout());
		address.add(new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("folder.png")))), BorderLayout.WEST);
		address.add(input_show);
		panel.add(address);
		panel.add(search);

		this.add(panel, BorderLayout.CENTER);
	}
	private void EditSize()
	{

	}
	public void setColorIcon(int hash, Color color)
	{
		if(iconback.hashCode() == hash)
		{
			if(iconback.isEnabled())
			iconback.setBackground(color);
		}
		else if(iconforward.hashCode() == hash)
		{
			if(iconforward.isEnabled())
			iconforward.setBackground(color);
		}
	}
}
