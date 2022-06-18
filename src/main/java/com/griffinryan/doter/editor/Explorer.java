package com.griffinryan.doter.editor;

import javafx.scene.control.TreeView;

public class Explorer extends EditorTool {

	private TreeView<String> finder;

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
