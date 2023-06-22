package view.content.left;

import java.awt.Cursor;
import javax.swing.JScrollPane;
import controller.mouse;
import model.Element;
import view.content.PanelContent;

public class ScrollPaneTree extends JScrollPane {
	private PanelContent pc;
	private int space;
	private TreeBar tree;
	private mouse mouselisten;
	private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private Cursor[] resizeCursor = { new Cursor(Cursor.N_RESIZE_CURSOR), new Cursor(Cursor.W_RESIZE_CURSOR),
			new Cursor(Cursor.SE_RESIZE_CURSOR) };
	private Element root;

	public ScrollPaneTree(PanelContent pc, Element root) {
		super();
		try {
			if (root != null)
				this.root = root;
			else
				this.root = null;
			this.pc = pc;
			this.tree = new TreeBar(pc, root);
			this.setViewportView(tree);
			this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
			this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
			this.mouselisten = new mouse(this, tree, pc);
			this.addMouseMotionListener(mouselisten);
			this.tree.addMouseMotionListener(mouselisten);
			this.pc.addMouseMotionListener(mouselisten);
			this.pc.addMouseListener(mouselisten);
			this.addMouseListener(mouselisten);
			this.setCursor(defaultCursor);
			this.tree.getIconClose().addMouseMotionListener(mouselisten);
			System.out.println("Upload success content left");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Content left");
		}
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		tree.setSize(width - (width >= 20 ? 20 : 0), height - (height >= 20 ? 20 : 0));
	}

	public void setTree(Element root) {
		tree = new TreeBar(pc, root);
	}

	public void SetCusorTreeBar() {
		// tree.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void SetCusorScroll(int x, int y) {
		if (x >= this.getSize().width - 4 && y >= this.getSize().height - 4) {
			this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		} else if (x >= this.getSize().width - 1) {
			this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		} else if (y >= this.getSize().height - 1) {
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		} else {
			this.setCursor(defaultCursor);
		}
		// pc.setBoundsObj(pc.getSize().width, pc.getSize().height,
		// this.getSize().width, pc.getWidthContentRight());
	}

	public void setCusorPanelContent(int x, int y) {
		if (x - space - this.getSize().width - 3 >= 0 || y - space - this.getSize().height - 3 >= 0) {
			pc.setCursor(defaultCursor);
		}

	}

	public void setSize(int x, int y) {
		if (this.getCursor() != defaultCursor) {
			if (x >= this.getSize().width - 4 && y >= this.getSize().height - 4 && x <= this.getSize().width + 4
					&& y <= this.getSize().height + 4) {
				if (x >= this.getMinimumSize().width && y >= this.getMinimumSize().height
						&& y <= this.getMaximumSize().height && x <= this.getMaximumSize().width) {
					super.setSize(x, y);
					tree.setSize(x - (x >= 20 ? 20 : 0), y - (y >= 20 ? 20 : 0));
				}
				tree.setIconClose(18);
			} else if (x >= this.getSize().width - 10 && x <= this.getSize().width + 10) {
				if (x >= this.getMinimumSize().width && x <= this.getMaximumSize().width) {
					super.setSize(x, this.getSize().height);
					tree.setSize(x - (x >= 20 ? 20 : 0), this.getSize().height);
				}
				tree.setIconClose(18);
			} else if (y >= this.getSize().height - 10 && y <= this.getSize().height + 10) {

				if (y >= this.getMinimumSize().height && y <= this.getMaximumSize().height) {
					// System.out.println(this.getMinimumSize().height + " " +
					// this.getMaximumSize().height + " " + y);
					super.setSize(this.getSize().width, y);
					// System.out.println(this.getSize());
					tree.setSize(this.getSize().width, y - (y >= 20 ? 20 : 0));
				}
			}
			// pc.update(pc.getSize().width, pc.getSize().height);
			pc.setBoundsObj(pc.getSize().width, pc.getSize().height, this.getSize().width, this.getSize().height,
					pc.getWidthContentRight());
		}
	}

	public void setHover(int x, int y) {
		this.setSize(x - pc.getSpace(), y - pc.getSpace());
	}

	public TreeBar getTreeBar() {
		return tree;
	}

	public PanelContent getPanelContent() {
		return pc;
	}

	public Cursor getDefaultCursor() {
		return defaultCursor;
	}

	public void mouseExit(int x, int y) {
		if (x - pc.getSpace() - 3 > this.getSize().width || y - pc.getSpace() - 3 > this.getSize().height)
			this.setCursor(defaultCursor);
	}

	public Cursor[] resizeCusor() {
		return resizeCursor;
	}
}
