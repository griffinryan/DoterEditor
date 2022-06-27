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

		fileView.setCellFactory(new Callback<>() {

			public TreeCell<File> call(TreeView<File> tv) {
				return new TreeCell<>() {

					@Override
					protected void updateItem(File item, boolean empty) {
						super.updateItem(item, empty);

						setText((empty || item == null) ? "" : item.getName());
						Font f = new Font("SourceSansPro-Regular.ttf", 14);
						setFont(f);

						/*
						for (int i = 0; i < this.getChildren().size(); i++) {
							File temp = this.getTreeView().getTreeItem(i).getValue();
							if (temp.isDirectory()) {
								setGraphic(folderIcon);
							} else if (temp.isFile()) {
								setGraphic(rootIcon);
							}
						}
						// Set icons with for-loop above.	*/
					}

				};
			}
		});
	}

	public TreeView<File> getFileView() {
		return fileView;
	}
}
