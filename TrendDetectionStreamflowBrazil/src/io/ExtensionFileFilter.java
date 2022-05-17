package io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter {
	
	private String extension;
	private String description;
	
	public ExtensionFileFilter(String extension, String description){
		if (!extension.startsWith(".")) extension = "." + extension;
		this.extension = extension;
		this.description = description;
	}

	public boolean accept (File f){
		return f.getName().toLowerCase().endsWith(this.extension)
			|| f.isDirectory();
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getExtension(){
		return this.extension;
	}

}
