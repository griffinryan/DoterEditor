package com.griffinryan.doter.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class SimpleFileTreeItem extends TreeItem<File> {

	private final Node rootIcon =
			new ImageView(new Image("doter_icon.png"));
	private final Node folderIcon =
			new ImageView(new Image("folder.png"));

	/**
	 * Calling the constructor of super class in oder to create a new
	 * TreeItem<File>.
	 *
	 * @param f
	 *            an object of type File from which a tree should be build or
	 *            which children should be gotten.
	 */
	public SimpleFileTreeItem(File f) {
		super(f);
		this.setExpanded(true);
	}

	@Override
	public ObservableList<TreeItem<File>> getChildren() {
		if (isFirstTimeChildren) {
			isFirstTimeChildren = false;
			/*
			 * First getChildren() call, so we actually go off and determine the
			 * children of the File contained in this TreeItem.
			 */
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}

	@Override
	public boolean isLeaf() {
		if (isFirstTimeLeaf) {
			isFirstTimeLeaf = false;
			File f = (File) getValue();
			isLeaf = f.isFile();
		}

		return isLeaf;
	}

	/**
	 * Returning a collection of type ObservableList containing TreeItems, which
	 * represent all children available in handed TreeItem.
	 *
	 * @param TreeItem
	 *            the root node from which children a collection of TreeItem
	 *            should be created.
	 * @return an ObservableList<TreeItem<File>> containing TreeItems, which
	 *         represent all children available in handed TreeItem. If the
	 *         handed TreeItem is a leaf, an empty list is returned.
	 */
	private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
		File tempF = TreeItem.getValue();

		/* Add icons to the File Tree items here! */
		if(tempF.isFile()){
			TreeItem.setGraphic(rootIcon);
			TreeItem.setExpanded(true);
		} else if(tempF.isDirectory()) {
			TreeItem.setGraphic(folderIcon);
		}

		File f = TreeItem.getValue();
		if (f != null && f.isDirectory()) {
			File[] files = f.listFiles();
			if (files != null) {
				ObservableList<TreeItem<File>> children = FXCollections
						.observableArrayList();

				for (File childFile : files) {
					children.add(new SimpleFileTreeItem(childFile));
				}

				return children;
			}
		}

		return FXCollections.emptyObservableList();
	}

	private boolean isFirstTimeChildren = true;
	private boolean isFirstTimeLeaf = true;
	private boolean isLeaf;
}
