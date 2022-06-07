package com.griffinryan.doter.editor;

import eu.mihosoft.monacofx.Document;
import eu.mihosoft.monacofx.MonacoFX;

import java.io.File;

public class CodeEditor extends EditorTool {

	private final MonacoFX monaco;

	public CodeEditor(Workspace theWorkspace){

		if(theWorkspace.isHasRecent()){
			this.monaco = new MonacoFX();
			this.monaco.getEditor().setCurrentTheme("vs-dark");
			this.monaco.getEditor().setCurrentLanguage(theWorkspace.getFileExtension());

			/* parse last opened file for MonacoFX editor node. */
			File toParse = new File(theWorkspace.getFileLocation());
			String parsed = this.saveFileToString(toParse);

			/*	Set document to last opened file. */
			Document document = new Document();
			document.setText(parsed);
			this.monaco.getEditor().setDocument(document);
		} else {

			/* create a blank MonacoFX editor node. */
			this.monaco = new MonacoFX();
			this.monaco.getEditor().setCurrentTheme("vs-dark");
		}


	}

	public MonacoFX getMonaco() {
		return monaco;
	}
}
