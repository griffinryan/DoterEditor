package com.griffinryan.doter.editor;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class Explorer extends EditorTool {

	private TreeView<String> treeView;
	private List<Item> items;
	private TreeItem<String> rootNode;

	private final Node rootIcon =
			new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("small.png"))));
	private final Image folderIcon =
			new Image(Objects.requireNonNull(getClass().getResourceAsStream("folder.png")));

	public Explorer(Workspace theWorkspace){

		if(theWorkspace.isHasRecent() && theWorkspace.getCurrentDirectory().exists()){
			// it has a directory to make an explorer of.
			this.rootNode = new TreeItem<>("MyCompany Human Resources", rootIcon);


		} else {
			// it does not have a directory. maybe show a button to open one.
		}
	}

	public TreeView<String> getTreeView() {
		return treeView;
	}

	public void setTreeView(TreeView<String> treeView) {
		this.treeView = treeView;
	}
}