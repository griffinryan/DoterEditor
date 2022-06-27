package com.griffinryan.doter.editor;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.File;

public class Explorer extends EditorTool {

	private TreeView<File> fileView;
	private TreeItem<File> rootNode;

	private final Node rootIcon =
			new ImageView(new Image("small.png"));
	private final Node folderIcon =
			new ImageView(new Image("folder.png"));

	public Explorer(Workspace workspace){
		this.fileView = new TreeView<>(new SimpleFileTreeItem(workspace.getCurrentDirectory()));

		fileView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {

			public TreeCell<File> call(TreeView<File> tv) {
				return new TreeCell<>() {

					@Override
					protected void updateItem(File item, boolean empty) {
						super.updateItem(item, empty);

						setText((empty || item == null) ? "" : item.getName());
						Font f = new Font("SourceSansPro-Regular.ttf", 14);
						setFont(f);

						for(int i = 0; i < this.getChildren().size(); i++){
							File temp = this.getTreeView().getTreeItem(i).getValue();
							if(temp.isDirectory()){
								setGraphic(folderIcon);
							} else if (temp.isFile()){
								setGraphic(rootIcon);
							}
						}
						// Set icons with for-loop above.
					}

				};
			}
		});
	}

	public TreeView<File> getFileView() {
		return fileView;
	}

	public void setFileView(TreeView<File> fileView) {
		this.fileView = fileView;
	}

	/*
	public Explorer(Workspace theWorkspace){
		this.items = new ArrayList<>(theWorkspace.getFileGroup().length);

		for(int i = 0; i < theWorkspace.getFileGroup().length; i++){
			this.items.add(new Item(theWorkspace.getFileGroup()[i], theWorkspace.getCurrentDirectory()));
		}

		this.rootNode = new TreeItem<>("Doter Project", rootIcon);

		for(Item item : items){
			TreeItem<String> itemLeaf = new TreeItem<>(item.getCurrentFile().getName());
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
		treeView.setBackground(bg);

		if(theWorkspace.isHasRecent() && theWorkspace.getCurrentDirectory().exists()){
			// it has a directory to make an explorer of.
		} else {
			// it does not have a directory. maybe show a button to open one.
		}
	}*/

}
