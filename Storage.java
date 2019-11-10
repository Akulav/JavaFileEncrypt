package application;

import java.io.File;

public class Storage {

	public File file = new File("");

	public void set(File file) {
		this.file = file;
	}

	public File get() {
		return this.file;
	}

}
