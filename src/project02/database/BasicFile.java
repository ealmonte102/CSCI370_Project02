package project02.database;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicFile {
	private Path path;
	public final static int MAX_FILE_PATH = 255;
	
	public BasicFile(String path) {
		setPath(path);
	}
	
	public String getFilename() {
		String fileName = path.getFileName().toString();
		if(fileName.indexOf('.') > 0) {
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		}
		return fileName;
	}
	
	public String getExtension() {
		String fileName = path.getFileName().toString();
		if(fileName.indexOf('.') > 0) {
			return fileName.substring(fileName.lastIndexOf('.') + 1);
		}
		return "";
	}
	
	public String getPath() {
		return path.toString();
	}

	public final void setPath(String path) {
		this.path = Paths.get(path);
	}
}