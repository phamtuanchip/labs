package lab3;

import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class AccessUrl extends MIDlet{
	private Display display;
	String url = "http://www.roseindia.net/hello.txt";

	public AccessUrl(){
		display = Display.getDisplay(this);
	}

	public void startApp(){
		try{
			connection(url);
		} catch (IOException e){
			System.out.println("IOException " + e);
			e.printStackTrace();
		}
	}
	
	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}

	void connection(String url) throws IOException{
		StreamConnection sc = null;
		InputStream is = null;
		StringBuffer buffer = new StringBuffer();
		TextBox access;
		try{
			sc = (StreamConnection)Connector.open(url);
			is = sc.openInputStream();
			int chars;
			while((chars = is.read()) != -1){
				buffer.append((char) chars);
			}
			System.out.println(buffer.toString());
			access = new TextBox("Access Text", buffer.toString(), 1024, 0);
		}finally{
			if(is != null){
				is.close();
			}
			if(sc != null){
				sc.close();
			}
		}
		display.setCurrent(access);
	}
}