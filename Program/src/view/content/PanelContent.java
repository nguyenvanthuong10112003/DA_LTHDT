package view.content;

import java.awt.Cursor;
import java.awt.Dimension;
import model.Element;
import javax.swing.JPanel;
import controller.mouse;
import define.ColorList;
import model.Folder;
import view.content.center.PanelContentCenter;
import view.content.left.ScrollPaneTree;
import view.content.right.PanelContentRight;
import view.screen.Screen;

public class PanelContent extends JPanel {
	/**
	 * 
	 */
	private Screen screen;
	private ScrollPaneTree contentLeft;
	private PanelContentCenter contentCenter;
	private PanelContentRight contentRight;
	private int space;
	private Boolean Show_Content_Left;
	private Boolean Show_Content_Right;
	private Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);
	private Folder root;
	private mouse mouslisten = new mouse(this);
	private Boolean islogin;

	public PanelContent(Screen screen, Folder root, Boolean islogin) {
		super();
		try {
			if (root != null) {
				this.root = root;
			} else {
				this.root = null;
			}
			this.screen = screen;
			this.islogin = islogin;
			this.contentLeft = new ScrollPaneTree(this, this.root);
			this.contentCenter = new PanelContentCenter(this, root);
			this.contentRight = new PanelContentRight(this);
			this.Show_Content_Left = true;
			this.Show_Content_Right = true;
			this.getSize();
			this.addObj();
			this.setColorObj();
			this.addEvent();
			System.out.println("Upload success Content");
		} catch (Exception e) {
			System.out.println("Error Content");
		}

	}

	private void addEvent() {
		this.addMouseListener(mouslisten);
		this.contentCenter.getTable().addMouseListener(mouslisten);
		this.contentLeft.getTreeBar().addMouseListener(mouslisten);
	}

	public void update(int width, int height) {
		contentLeft.setMinimumSize(new Dimension(60, 50));
		contentLeft.setMaximumSize(new Dimension(width / 3, height - space * 2));
		contentRight.setMaximumSize(new Dimension(width / 3, height - space * 2));
		contentRight.setMinimumSize(new Dimension(50, height - space * 2));
		if (this.Show_Content_Left) {
			contentLeft.setVisible(true);
			contentLeft.getTreeBar().setVisible(true);
		} else {
			contentLeft.setVisible(false);
			contentLeft.getTreeBar().setVisible(false);
		}
		if (this.Show_Content_Right)
			contentRight.setVisible(true);
		else
			contentRight.setVisible(false);
		this.setBoundsObj(width, height, 200, height, 200);
		contentLeft.getTreeBar().setIconClose(18);
	}

	private void addObj() {
		this.setLayout(null);
		this.add(contentLeft);
		this.add(contentCenter);
		this.add(contentRight);
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int sp) {
		space = sp;
	}

	private void setColorObj() {
		this.contentCenter.setOpaque(true);
		// this.contentCenter.setBackground(back);
		this.contentCenter.setForeground(ColorList.Fore_Ground);

		this.contentRight.setOpaque(true);
		this.contentRight.setBackground(ColorList.Back_Ground);
		this.contentRight.setForeground(ColorList.Fore_Ground);
	}

	public ScrollPaneTree getPanelContentLeft() {
		return contentLeft;
	}

	public void setBoundsObj(int width, int height, int widthscroll, int heightscroll, int rightbar) {
		this.setVisible(false);
		if (Show_Content_Left)
			this.contentLeft.setBounds(space, space,
					widthscroll > contentLeft.getMaximumSize().width ? contentLeft.getMaximumSize().width : widthscroll,
					heightscroll == height - space * 2
							? (height - space * 2 > contentLeft.getMaximumSize().height
									? contentLeft.getMaximumSize().height
									: height - space * 2)
							: (heightscroll > contentLeft.getMaximumSize().height ? contentLeft.getMaximumSize().height
									: heightscroll));
		else
			this.contentLeft.setBounds(space, space, 0, 0);
		if (Show_Content_Right) {
			this.contentRight.setBounds(width - rightbar - space, space, rightbar, height - space * 2);
			this.contentRight.Edit();
		} else
			this.contentRight.setSize(0, 0);
		this.contentCenter.setBounds(contentLeft.getSize().width + (Show_Content_Left ? space * 2 : space), space,
				width - contentLeft.getSize().width - contentRight.getSize().width
						- (Show_Content_Right ? space * 2 : space) - (Show_Content_Left ? space * 2 : space),
				height - space * 2);
		this.contentCenter.addObj();
		this.setVisible(true);
	}

	public void ClosePanelRight() {
		Show_Content_Right = false;
		update(this.getSize().width, this.getSize().height);
	}

	public void ShowPanelRight() {
		if (Show_Content_Right)
			return;
		else {
			Show_Content_Right = true;
			update(this.getSize().width, this.getSize().height);
		}
	}

	public void ClosePanelLeft() {
		Show_Content_Left = false;
		update(this.getSize().width, this.getSize().height);
	}

	public void ShowPanelLeft() {
		if (Show_Content_Left) {
			System.out.println("ok");
			return;
		} else {
			Show_Content_Left = true;
			update(this.getSize().width, this.getSize().height);
		}
	}

	public int getSizePanelRight_Width() {
		return contentRight.getSize().width;
	}

	public void setBoundsLeft(int x, int y) {
		if (Show_Content_Left)
			this.contentLeft.setBounds(space, space, x, y - space * 2);
	}

	public int getWidthContentRight() {
		return contentRight.getSize().width;
	}

	public Cursor cursorDefault() {
		return cursorDefault;
	}

	public PanelContentCenter getCenter() {
		return contentCenter;
	}

	public void SELECTtable(Element e) {
		contentRight.selected(e);
	}

	public Element getSelect() {
		return contentRight.getSelect();
	}
	
	public void noSelected() {
		contentRight.noselected();
	}

	public void setRootCenter(Folder folder) {
		contentCenter.setRoot(folder);
	}

	public Boolean isLogin() {
		return islogin;
	}

	public void setFunSelectedTablie(Boolean select) {
		this.screen.setSelectTable(select);
	}

	public Screen getScreen() {
		return screen;
	}

	public void UpdateLeft() {
		contentLeft.getTreeBar().Update();
	}
}
