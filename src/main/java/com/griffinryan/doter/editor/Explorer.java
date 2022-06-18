package com.griffinryan.doter.editor;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class Explorer extends EditorTool {

	private final Node rootIcon =
			new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("small.png"))));
	private final Image folderIcon =
			new Image(Objects.requireNonNull(getClass().getResourceAsStream("folder.png")));
	/*TreeItem<String> rootNode =
			new TreeItem<String>("MyCompany Human Resources", rootIcon);*/
	private TreeView<String> finder;
	private List<Item> items;
	private TreeItem<String> rootNote;


	public Explorer(Workspace theWorkspace){

		if(theWorkspace.isHasRecent() && theWorkspace.getCurrentDirectory().exists()){
			// it has a directory to make an explorer of.


		} else {
			// it does not have a directory. maybe show a button to open one.
			this.finder = new TreeView<>();
		}

	}

	public TreeView<String> getFinder() {
		return finder;
	}

	public void setFinder(TreeView<String> finder) {
		this.finder = finder;
	}
}
