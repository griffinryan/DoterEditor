package com.griffinryan.doter.editor;

import com.griffinryan.doter.gui.DoterMenu;
import eu.mihosoft.monacofx.Document;
import eu.mihosoft.monacofx.MonacoFX;

public class CodeEditor extends EditorTool {

	private MonacoFX monaco;
	private Workspace codeWorkspace;

	public CodeEditor(Workspace codeWorkspace){

		if(codeWorkspace.isHasRecent()){
			this.monaco = new MonacoFX();
			this.monaco.getEditor().setCurrentTheme("vs-dark");
			this.monaco.getEditor().setCurrentLanguage(codeWorkspace.getFileExtension());

			/*	Set document to last opened file. */
			Document document = new Document();
			document.setText();
			this.monaco.getEditor().setDocument();
		} else {
			this.monaco = new MonacoFX();
			this.monaco.getEditor().setCurrentTheme("vs-dark");
		}


	}

	public MonacoFX getMonaco() {
		return monaco;
	}

	public void setMonaco(MonacoFX monaco) {
		this.monaco = monaco;
	}
}
