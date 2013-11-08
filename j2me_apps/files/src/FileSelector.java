/*
 * File Connection Using JSR 75
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemListener;
import javax.microedition.io.file.FileSystemRegistry;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
/**
 *
 * @author Administrator
 */
public class FileSelector extends List implements CommandListener,FileSystemListener {
	FileSelection fileSelection;
	private Display display;
	// define the file separator
	private final static String FILE_SEPARATOR =(System.getProperty("file.separator") != null) ? System.getProperty("file.separator") : "/";
	private Command open = new Command("Open", Command.OK, 1);
	private String errorMsg = null;
	private Alert alert;
	private Vector rootsList = new Vector();
	private final static String upper_dir = "...";
	private FileConnection currentRoot = null;
	private FileConnection fconn = null;
	private static final int CHUNK_SIZE = 1024;

	FileSelector(FileSelection fileSelection) {
		super("File Browser", List.IMPLICIT);
		this.fileSelection = fileSelection;
		deleteAll();
		addCommand(open);
		setSelectCommand(open);
		setCommandListener(this);
		FileSystemRegistry.addFileSystemListener(FileSelector.this);
		execute();
	}

	public void execute() {
		String initDir = System.getProperty("fileconn.dir");
		loadRoots();
		if (initDir != null) {
			try {
				currentRoot = (FileConnection) Connector.open(initDir, Connector.READ);
				displayCurrentRoot();
			} catch (Exception e) {
				displayAllRoots();
			}
		} else {
			displayAllRoots();
		}
	}

	private void loadRoots() {
		if (!rootsList.isEmpty()) {
			rootsList.removeAllElements();
		}
		try {
			Enumeration roots = FileSystemRegistry.listRoots();
			while (roots.hasMoreElements()) {
				rootsList.addElement(FILE_SEPARATOR + (String) roots.nextElement());
			}
		} catch (Throwable e) {
		}
	}

	private void displayCurrentRoot() {
		try {
			setTitle(currentRoot.getURL());
			deleteAll();
			append(upper_dir, null);
			Enumeration listOfDirs = currentRoot.list("*", false);
			while (listOfDirs.hasMoreElements()) {
				String currentDir = (String) listOfDirs.nextElement();
				if (currentDir.endsWith(FILE_SEPARATOR)) {
					append(currentDir, null);
				} else {
					append(currentDir, null);
				}
			}

			/*Enumeration listOfFiles = currentRoot.list("*.png",false);
                  while(listOfFiles.hasMoreElements()) {
                        String currentFile=(String) listOfFiles.nextElement();
                        if(currentFile.endsWith(FILE_SEPARATOR)) {
                              append(currentFile,null);
                        }
                        else {
                              append(currentFile,null);
                        }
                  }*/
		} catch (IOException e) {
		} catch (SecurityException e) {
		}
	}

	private void displayAllRoots() {
		setTitle("[Roots]");
		deleteAll();
		Enumeration roots = rootsList.elements();
		while (roots.hasMoreElements()) {
			String root = (String) roots.nextElement();
		}
		currentRoot = null;
	}

	private void openSelected() {
		int selectedIndex = getSelectedIndex();
		if (selectedIndex >= 0) {
			String selectedFile = getString(selectedIndex);
			if (selectedFile.endsWith(FILE_SEPARATOR)) {
				try {
					if (currentRoot == null) {
						currentRoot = (FileConnection) Connector.open("file:///" + selectedFile, Connector.READ);
					} else {
						currentRoot.setFileConnection(selectedFile);
					}
					displayCurrentRoot();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (SecurityException e) {
					System.out.println(e.getMessage());
				}
			} else if (selectedFile.equals(upper_dir)) {
				if (rootsList.contains(currentRoot.getPath() + currentRoot.getName())) {
					displayAllRoots();
				} else {
					try {
						currentRoot = (FileConnection) Connector.open("file://" + currentRoot.getPath(), Connector.READ);
						displayCurrentRoot();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				String url = currentRoot.getURL() + selectedFile;
				byteConvert(url, selectedFile);
			}
		}
	}

	public void stop() {
		if (currentRoot != null) {
			try {
				currentRoot.close();
			} catch (IOException e) {
			}
		}
	}

	public void byteConvert(String url, String filename) {
		try {
			FileConnection fileConn = (FileConnection) Connector.open(url, Connector.READ);
			InputStream fis = fileConn.openInputStream();
			long overallSize = fileConn.fileSize();
			int length = 0;
			byte[] filedata = new byte[0];
			while (length < overallSize) {//converting the selected file to bytes
				byte[] data = new byte[CHUNK_SIZE];
				int readAmount = fis.read(data, 0, CHUNK_SIZE);
				byte[] newFileData = new byte[filedata.length + CHUNK_SIZE];
				System.arraycopy(filedata, 0, newFileData, 0, length);
				System.arraycopy(data, 0, newFileData, length, readAmount);
				filedata = newFileData;
				length += readAmount;
			}
			fis.close();
			fileConn.close();
			//here u can write a code to connect with the server for sending the selected file
		} catch (Exception e) {
			//showAlert(e.getMessage());
		} finally {
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == open) {
			openSelected();
		}
	}

	public void rootChanged(int state, String rootNmae) {
	}
}