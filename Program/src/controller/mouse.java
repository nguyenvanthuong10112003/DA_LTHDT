package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import define.ColorList;
import javax.swing.JLabel;
import view.content.PanelContent;
import view.content.center.PanelContentCenter;
import view.content.left.ScrollPaneTree;
import view.content.left.TreeBar;
import view.content.right.PanelContentRight;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.menubar.Screen_MenuBar;
import view.screen.Screen;
import view.toolbar.Panel_Navigation;
import view.toolbar.ScreenPageUser;
import view.toolbar.Panel_Functions;

public class mouse extends MouseAdapter implements MouseListener {
	private PanelContent pc;
	private Panel_Navigation pf;
	private PanelContentRight cr;
	private Panel_Functions tb;
	private Screen sc;
	private ScrollPaneTree scroll;
	private TreeBar tree;
	private Screen_MenuBar menu;
	private PanelContentCenter pct;
	private FormLogin formlogin;
	private FormRegister formregister;
	private ScreenPageUser page;
	
	public mouse(ScreenPageUser page)
	{
		this.page = page;
	}
	
	public mouse(FormLogin formlogin) {
		this.formlogin = formlogin;
	}

	public mouse(FormRegister formregister) {
		this.formregister = formregister;
	}

	public mouse(Panel_Navigation pf) {
		this.pf = pf;
	}

	public mouse(Panel_Functions tb) {
		this.tb = tb;
	}

	public mouse(PanelContentCenter pct) {
		this.pct = pct;
	}

	public mouse(PanelContent content) {
		this.pc = content;
	}

	public mouse(Screen_MenuBar menu) {
		this.menu = menu;
	}

	public mouse(Screen sc) {
		this.sc = sc;
	}

	public mouse(TreeBar tree) {
		this.tree = tree;
	}

	public mouse(PanelContentRight cr, PanelContent pc) {
		this.cr = cr;
		this.pc = pc;
	}

	public mouse(ScrollPaneTree scroll, TreeBar tree, PanelContent pc) {
		this.pc = pc;
		this.scroll = scroll;
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (tb != null) {
			try {
				tb.setClicked(e.getSource().hashCode());
				tb.onclick_Button(e.getSource().hashCode(), e.getXOnScreen(), e.getYOnScreen());
			} catch (Exception ex) {
				System.out.print("error");
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.closeClick();
			}
		} else if (cr != null) {
			if (e.getSource().equals(cr.getLabelIconClose())) {
				cr.closeClick();
			} else if (e.getSource().equals(cr.getSave())) {
				try {
					cr.clickedSave();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		} else if (menu != null) {
			menu.onClick(e.getSource().hashCode());
		} else if (pf != null) {
			pf.Onclick(e.getSource().hashCode());
		} else if (pct != null) {
			if (e.getSource().equals(pct.getTable())) {
				pct.clickedTable();
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					pct.Open();
				} 
			} else {
				pct.getTable().clearSelection();
				pct.getPanelContent().noSelected();
				pct.getPanelContent().getScreen().FunEnablueRoot(true);
			}
		} else if (formlogin != null) {
			formlogin.clicked(e.getSource().hashCode(), e.getX(), e.getY());
		} else if (formregister != null) {
			formregister.clicked(e.getSource().hashCode(), e.getX(), e.getY());
		} else if (page != null)
		{
			page.clicked(e.getSource().hashCode());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (sc != null) {

		} else if (pc != null) {

		} else if (pct != null) {
			if (e.isPopupTrigger()) {
				pct.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (scroll != null) {
		} else if (pc != null) {
		} else if (pct != null) {
			if (e.isPopupTrigger()) {
				pct.showPopup(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (scroll != null) {
			if (e.getSource().equals(scroll)) {
				scroll.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		} else if (pf != null) {
			if (e.getSource().getClass().equals(JLabel.class)) {
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Back_Ground);
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.exitClose();
			}
		} else if (cr != null) {
			if (e.getSource().equals(cr.getLabelIconClose())) {
				cr.exitClose();
			} else if (e.getSource().equals(cr.getSave())) {
				cr.exitSave();
			}
		} else if (tb != null) {
			tb.setHover(e.getSource().hashCode(), ColorList.Back_Ground);
			tb.exit(e.getSource().hashCode());
		} else if (formlogin != null) {
			formlogin.exit(e.getSource().hashCode());
		} else if (formregister != null) {
			formregister.exit(e.getSource().hashCode());
		} else if (page != null) {
			page.exit(e.getSource().hashCode());
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (scroll != null) {
			if (e.getSource().equals(scroll)) {
				scroll.setSize(e.getX(), e.getY());
			} else if (e.getSource().equals(pc)) {
				scroll.setHover(e.getX(), e.getY());
			}
		} else if (cr != null) {
			if (e.getSource().equals(cr)) {
				if (cr.getCursor() == cr.cursorResize())
					cr.setSize(e.getX());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (scroll != null) {
			if (scroll != null) {
				try {
					if (e.getSource().equals(scroll)) {
						scroll.SetCusorScroll(e.getX(), e.getY());
					} else if (e.getSource().equals(tree)) {
						scroll.SetCusorTreeBar();
					} else if (e.getSource().equals(pc)) {
						scroll.setCusorPanelContent(e.getX(), e.getY());
					}
				} catch (Exception ex) {
					System.out.println("error");
				}
			} else if (e.getSource().equals(scroll.getPanelContent())) {
				
			}
		} else if (cr != null) {
			if (e.getSource().equals(cr)) {
				cr.setCusor(e.getX(), e.getY());
			}
			else if (e.getSource().equals(cr.getLabelIconClose())) {
				cr.setDefaultCursor();
				cr.hoverClose();
			} else if (e.getSource().equals(cr.getSave())) {
				cr.hoverSave();
			}
		} else if (pf != null) {
			if (e.getSource().getClass().equals(JLabel.class)) {
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Hover);
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.hoverClose();
			}
			tree.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (pct != null) {
			
		} else if (cr != null) {
			if (e.getSource().equals(cr.getLabelIconClose())) {

			}
		} else if (tb != null) {
			tb.setHover(e.getSource().hashCode(), ColorList.Hover);
			tb.hover(e.getSource().hashCode());
		} else if (formlogin != null) {
			formlogin.hover(e.getSource().hashCode());
		} else if (formregister != null) {
			formregister.hover(e.getSource().hashCode());
		} else if (page != null)
		{
			page.hover(e.getSource().hashCode());
		}
	}

}
