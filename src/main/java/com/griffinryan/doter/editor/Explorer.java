package com.griffinryan.doter.editor;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.*;

public class Explorer extends EditorTool {

	private TreeView<String> treeView;
	private List<Item> items;
	private TreeItem<String> rootNode;

	private final Node rootIcon =
			new ImageView(new Image("small.png"));
	private final Image folderIcon =
			new Image("folder.png");

	public Explorer(Workspace theWorkspace){

		/* Instantiate the List<Item> items to keep a list
		*  of all the user Doter-workspace files/folders.		*/
		this.items = new ArrayList<>(theWorkspace.getFileGroup().length);

		for(int i = 0; i < theWorkspace.getFileGroup().length; i++){
			this.items.add(new Item(theWorkspace.getFileGroup()[i], theWorkspace.getCurrentDirectory()));
		}

		this.rootNode = new TreeItem<>("Doter Project", rootIcon);

		for(Item item : items){
			TreeItem<String> itemLeaf = new TreeItem<>(item.getName());
			boolean found = false;
			for (TreeItem<String> depNode : rootNode.getChildren()) {
				if (depNode.getValue().contentEquals(item.getFolder())){
					depNode.getChildren().add(itemLeaf);
					found = true;
					break;
				}
			}
			if (!found) {
				TreeItem<String> depNode = new TreeItem<String>(
						item.getFolder(),
						new ImageView(folderIcon)
				);
				rootNode.getChildren().add(depNode);
				depNode.getChildren().add(itemLeaf);
			}
		}

		this.treeView = new TreeView<>(rootNode);

		if(theWorkspace.isHasRecent() && theWorkspace.getCurrentDirectory().exists()){
			// it has a directory to make an explorer of.
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
