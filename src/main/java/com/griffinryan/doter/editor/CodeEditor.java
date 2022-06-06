package com.griffinryan.doter.editor;

import eu.mihosoft.monacofx.MonacoFX;

public class CodeEditor {

	private MonacoFX monaco;

	public CodeEditor(){
		this.monaco = new MonacoFX();
		this.monaco.getEditor().getDocument().setText(
				"""
						#include <stdio.h>
						int main() {
						   // printf() displays the string inside quotation
						   printf("Hello, World!");
						   return 0;
						}""");

		this.monaco.getEditor().setCurrentLanguage("java");
		this.monaco.getEditor().setCurrentTheme("vs-dark");
	}

	public MonacoFX getMonaco() {
		return monaco;
	}

	public void setMonaco(MonacoFX monaco) {
		this.monaco = monaco;
	}
}
