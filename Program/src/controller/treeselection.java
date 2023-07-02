package controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import view.content.left.TreeBar;

public class treeselection implements TreeSelectionListener {
	private TreeBar tree;

	public treeselection(TreeBar tree) {
		this.tree = tree;
	}

	public void valueChanged(TreeSelectionEvent e) {
		tree.treeSelection(e.getPath());
	}
}
