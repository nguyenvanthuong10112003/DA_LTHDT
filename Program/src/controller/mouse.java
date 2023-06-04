package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import libary.ColorList;
import javax.swing.JLabel;
import view.content.PanelContent;
import view.content.center.PanelContentCenter;
import view.content.left.ScrollPaneTree;
import view.content.left.TreeBar;
import view.content.right.PanelContentRight;
import view.menubar.Screen_MenuBar;
import view.screen.Screen;
import view.toolbar.Panel_Navigation;
import view.toolbar.Panel_Functions;

public class mouse extends MouseAdapter implements MouseListener {
	private PanelContent pc;
	private Panel_Navigation pf;
	private PanelContentRight cr;
	private JPanel content;
	private Panel_Functions tb;
	private Screen sc;
	private ScrollPaneTree scroll;
	private TreeBar tree;
	private Boolean mousep = false;
	private Screen_MenuBar menu;
	private PanelContentCenter pct;
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
		// TODO Auto-generated method stub
		// System.out.println(e.getSource());
		if (tb != null) {
			try {
				if (e.getSource().getClass().equals(JButton.class)) {
					tb.onclick_Button((JButton) e.getSource(), e.getXOnScreen(), e.getYOnScreen());
				} else {
					tb.setClicked(e.getSource().hashCode());
				}
			} catch (Exception ex) {
				System.out.print("error");
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.closeClick();
			}
		} else if (cr != null) {
			if (e.getSource().equals(cr.getLabelIconClose())) {
				// System.out.println(e.getSource());
				cr.closeClick();
			}
			else if(e.getSource().equals(cr.getSave()))
			{
				try {
					cr.clickedSave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (menu != null) {
			menu.onClick(e.getSource().hashCode());
		} else if (pf != null) {
			// System.out.println(e.getSource());
			pf.mouseOnclick(e.getSource().hashCode());
			pf.Onclick(e.getSource().hashCode());
		} else if(pct != null)
		{
			pct.getTable().clearSelection();
		    pct.getPanelContent().noSelected();
		    pct.getPanelContent().getScreen().FunEnablueRoot(true);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (scroll != null) {
			if (e.getSource().equals(scroll))
				mousep = false;
		} else if (pc != null) {
		} else if (pct != null) {
			if (e.isPopupTrigger()) {
				if (pct.getTable().getSelectedRow() < 0)
					pct.NoSelected();
				else
					pct.Selected();
				pct.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (scroll != null) {
			if (e.getSource().equals(scroll)) {
				// System.out.println(e.getX() + " " + e.getY());
				// pc.setCursor(scroll.getDefaultCursor());
				// scroll.setCursor(scroll.getDefaultCursor());
			}
			// if(e.getSource().equals(scroll))
			// scroll.mouseExit(e.getX(), e.getY());

		}
		if (pf != null) {
			if (e.getSource().getClass().equals(JLabel.class)) {
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Back_Ground);
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.exitClose();
			}
		} else if (cr != null) {
			if(e.getSource().equals(cr.getLabelIconClose())) {
				cr.exitClose();
			}
			else if(e.getSource().equals(cr.getSave()))
			{
				cr.exitSave();
			}
		} else if (tb != null) {
			tb.setHover(e.getSource().hashCode(), ColorList.Back_Ground);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (scroll != null) {
			if (scroll != null) {
				try {
					if (e.getSource().equals(scroll)) {
						scroll.SetCusorScroll(e.getX(), e.getY());
					} else if (e.getSource().equals(tree))
						scroll.SetCusorTreeBar();
					else if (e.getSource().equals(pc)) {
						scroll.setCusorPanelContent(e.getX(), e.getY());
					}
				} catch (Exception ex) {
					System.out.println("error");
				}
			} else if (e.getSource().equals(scroll.getPanelContent())) {
				// scroll.mouseExit(e.getX(), e.getY());
			}

		} else if (cr != null) {
			if (e.getSource().equals(cr))
				cr.setCusor(e.getX(), e.getY());
			else if (e.getSource().equals(cr.getLabelIconClose())) {
				cr.setDefaultCursor();
				cr.hoverClose();
			}
			else if(e.getSource().equals(cr.getSave()))
			{
				cr.hoverSave();
			}
		} else if (pf != null) {
			if (e.getSource().getClass().equals(JLabel.class)) {
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Hover);
			}
		} else if (tree != null) {
			if (e.getSource().equals(tree.getIconClose())) {
				tree.hoverClose();
				;
			}
		} else if (pct != null) {
		} else if (cr != null) {
			if (e.getSource().equals(cr.getLabelIconClose())) {

			}
		} else if (tb != null) {
			// System.out.println(e.getSource());
			tb.setHover(e.getSource().hashCode(), ColorList.Hover);
			;
		}
	}

}
