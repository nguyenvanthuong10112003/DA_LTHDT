package view.menubar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import controller.mouse;
import define.ColorList;
import define.FONT;
import view.content.PanelContent;
import view.screen.Screen;
import controller.action;

public class Screen_MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private Screen sc;
	private PanelContent pc;
	private JMenu File;
	private JMenu Edit;
	private JMenu View;
	private JMenuItem item1_1;
	private JMenuItem item1_2;
	private JMenu item3_1;
	private JRadioButtonMenuItem item3_1_1;
	private JRadioButtonMenuItem item3_1_2;
	private JRadioButtonMenuItem item3_1_3;
	private JMenu item3_2;
	private JMenu item3_3;
	private JMenuItem item3_3_1;
	private JMenuItem item3_3_2;
	private JMenuItem item3_2_1;
	private JMenuItem item3_2_2;
	private JMenuItem item3_2_3;
	private ButtonGroup groupbutton;
	private mouse mouselisten;
	private action actionlisten;
	private static int sort;

	public Screen_MenuBar(Screen sc) {
		super();
		try {
			Screen_MenuBar.sort = 2;
			this.sc = sc;
			init();
			setIconItem();
			setText();
			addEvent();
			Edit();
			addObj();
			setBack();
			System.out.println("Upload success menu");
		} catch (Exception e) {
			System.out.println("Error thanh menu");
		}
	}

	public void setPanelContent(PanelContent pc) {
		this.pc = pc;
	}

	private void setBack() {
		// panel_file.setBackground(backcl);
		this.setBackground(ColorList.Back_Ground);
	}

	private void Edit() {
		item3_2_3.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\check.png"));
	}

	private void init() {
		File = new JMenu();
		Edit = new JMenu();
		View = new JMenu();
		item1_1 = new JMenuItem();
		item1_2 = new JMenuItem();
		item3_1 = new JMenu();
		item3_1_1 = new JRadioButtonMenuItem();
		item3_1_2 = new JRadioButtonMenuItem();
		item3_1_3 = new JRadioButtonMenuItem();
		item3_2 = new JMenu();
		groupbutton = new ButtonGroup();
		item3_3 = new JMenu();
		item3_3_1 = new JMenuItem();
		item3_3_2 = new JMenuItem();
		item3_2_1 = new JMenuItem();
		item3_2_2 = new JMenuItem();
		item3_2_3 = new JMenuItem();
		mouselisten = new mouse(this);
		actionlisten = new action(this);
	}

	private void addEvent() {
		this.addMouseListener(mouselisten);
		Edit.addMouseListener(mouselisten);
		item3_3_1.addActionListener(actionlisten);
		item3_3_2.addActionListener(actionlisten);
		item3_2_1.addActionListener(actionlisten);
		item3_2_2.addActionListener(actionlisten);
		item3_2_3.addActionListener(actionlisten);
		item1_2.addActionListener(actionlisten);
	}

	private void setText() {
		File.setText("File");
		File.setFont(FONT.font_mac_dinh);
		Edit.setText("Edit");
		Edit.setFont(FONT.font_mac_dinh);
		View.setText("View");
		View.setFont(FONT.font_mac_dinh);
		item1_1.setText("Mở trong cửa sổ mới");
		item1_1.setFont(FONT.font_mac_dinh);
		item1_2.setText("Đóng");
		item1_2.setFont(FONT.font_mac_dinh);
		item3_1.setText("Chế độ xem");
		item3_1.setFont(FONT.font_mac_dinh);
		item3_1_1.setText("Chi tiết");
		item3_1_1.setFont(FONT.font_mac_dinh);
		item3_1_2.setText("Biểu tượng");
		item3_1_2.setFont(FONT.font_mac_dinh);
		item3_1_3.setText("Biểu tượng lớn");
		item3_1_3.setFont(FONT.font_mac_dinh);
		item3_2.setText("Sắp xếp theo");
		item3_2.setFont(FONT.font_mac_dinh);
		item3_3.setText("Hiển thị");
		item3_3.setFont(FONT.font_mac_dinh);
		item3_3_1.setText("Hiện ngăn xem trước");
		item3_3_1.setFont(FONT.font_mac_dinh);
		item3_3_2.setText("Hiện ngăn xem chi tiết");
		item3_3_2.setFont(FONT.font_mac_dinh);
		item3_2_1.setText("Tên");
		item3_2_1.setFont(FONT.font_mac_dinh);
		item3_2_2.setText("Ngày tạo");
		item3_2_2.setFont(FONT.font_mac_dinh);
		item3_2_3.setText("Ngày sửa đổi gần nhất");
		item3_2_3.setFont(FONT.font_mac_dinh);
	}

	private void addObj() {
		item3_1_1.setSelected(true);
		groupbutton.add(item3_1_1);
		groupbutton.add(item3_1_2);
		groupbutton.add(item3_1_3);

		item3_1.add(item3_1_1);
		item3_1.add(item3_1_2);
		item3_1.add(item3_1_3);

		item3_2.add(item3_2_1);
		item3_2.add(item3_2_3);

		item3_3.add(item3_3_1);
		item3_3.add(item3_3_2);

		File.add(item1_2);
		
		View.add(item3_2);
		View.addSeparator();
		View.add(item3_3);
		this.add(File);
		this.add(Edit);
		this.add(View);
	}

	private void setIconItem() {
		try {
			item1_1.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\new window1_16px.png"));
			item1_1.setIconTextGap(7);
			item1_2.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\close_16px.png"));
			item1_2.setIconTextGap(7);
			item3_1.setIconTextGap(7);
			item3_1_1.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\che do xem chi tiet1.png"));
			item3_1_1.setIconTextGap(7);
			item3_1_2.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\bieu tuong.png"));
			item3_1_2.setIconTextGap(7);
			item3_1_3.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\bieu tuong lon.png"));
			item3_1_3.setIconTextGap(7);
			item3_2.setIcon((new ImageIcon(define.URL.url + "\\Icon\\menubar\\sort.png")));
			item3_2.setIconTextGap(7);
			item3_3.setIcon((new ImageIcon(define.URL.url + "\\Icon\\menubar\\eye.png")));
			item3_3.setIconTextGap(7);
		} catch (Exception e) {
		}
	}

	public void handleEvent(int hash) {
		if (item3_1_1.hashCode() == hash) {

		} else if (item3_1_2.hashCode() == hash) {

		} else if (item3_1_3.hashCode() == hash) {

		} else if (item3_3_1.hashCode() == hash) {
			pc.ShowPanelLeft();
		} else if (item3_3_2.hashCode() == hash) {
			pc.ShowPanelRight();
		} else if (item3_2_1.hashCode() == hash) {
			item3_2_1.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\check.png"));
			item3_2_2.setIcon(null);
			item3_2_3.setIcon(null);
			sort = 0;
			sc.UpdateTable();
		} else if (item3_2_2.hashCode() == hash) {
			item3_2_1.setIcon(null);
			item3_2_2.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\check.png"));
			item3_2_3.setIcon(null);
			sort = 1;
			sc.UpdateTable();
		} else if (item3_2_3.hashCode() == hash) {
			item3_2_1.setIcon(null);
			item3_2_2.setIcon(null);
			item3_2_3.setIcon(new ImageIcon(define.URL.url + "\\Icon\\menubar\\check.png"));
			sort = 2;
			sc.UpdateTable();
		} else if (item1_2.hashCode() == hash)
		{
			System.exit(0);
		}
	}

	public void onClick(int hash) {
		if (hash == Edit.hashCode()) {
			sc.setToolBarVisiable();
		}
	}

	public static int getSort() {
		return sort;
	}
}