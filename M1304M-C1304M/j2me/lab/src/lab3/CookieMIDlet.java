package lab3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
  
public class CookieMIDlet extends MIDlet implements CommandListener{
	private Display display;
	private Form form;
	private Command exit, logon;
	private String cookie = null;
	private RecordStore rs = null;  
	static final String REC_STORE = "CookieMIDlet";
	private String url = "http://localhost:8080/J2MEServlet/";

	public void startApp(){
		display = Display.getDisplay(this);
		exit = new Command("Exit", Command.EXIT, 1);
		logon = new Command("Logon", Command.SCREEN, 2);
		form = new Form("");
		form.addCommand(exit);
		form.addCommand(logon);
		form.setCommandListener(this);
		openRecord();   
		readCookie();
		display.setCurrent(form);
	}    
	
	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		closeRecord();
		notifyDestroyed();
	}

	public void openRecord(){
		try{
			rs = RecordStore.openRecordStore(REC_STORE, true);
		}catch(Exception e){}
	}

	public void closeRecord(){
		try{
			rs.closeRecordStore();
		}catch(Exception e){}
	}

	public void writeRecord(String str){
		byte[] rec = str.getBytes();
		try{
			rs.addRecord(rec, 0, rec.length);
		}catch(Exception e){}
	}

	public void readCookie(){
		try{
			byte[] recData = new byte[25]; 
			int len;

			if(rs.getNumRecords() > 0){
				if (rs.getRecordSize(1) > recData.length){
					recData = new byte[rs.getRecordSize(1)];
				}
				len = rs.getRecord(1, recData, 0);
				cookie = new String(recData);
			}
		} catch(Exception e){}
	}

	private void httpConnect() throws IOException{
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		HttpConnection con = null;    

		try{
			con = (HttpConnection) Connector.open(url);
			con.setRequestMethod(HttpConnection.GET);
					
			if(cookie != null){
				con.setRequestProperty("cookie", cookie);
			}
			System.out.println("Client cookie: " + cookie);      
			if(con.getResponseCode() == HttpConnection.HTTP_OK){
				String server_cookie = con.getHeaderField("set-cookie");        
				System.out.println("server cookie: " + server_cookie);
				if(server_cookie != null){
					writeRecord(server_cookie);
					cookie = server_cookie;
					form.append("First visit\n");          
					form.append("Client : " + cookie + "\n");
				}else{
					is = con.openInputStream();
					int length = (int) con.getLength();
					String str;
					if(length != -1){
						byte serverData[] = new byte[length];
						is.read(serverData);
						str = new String(serverData);
					}else{
						bos = new ByteArrayOutputStream();       
						int ch;
						while((ch = is.read()) != -1){
							bos.write(ch);
						}
						str = new String(bos.toByteArray());
					}
					form.append("Last access:\n" + str + "\n");                   
				}
			} else {
				System.out.println("Response code : " + con.getResponseCode());      
			}
		}finally{
			if(is != null){
				is.close();
			}
			if(bos != null){
				bos.close();
			}
			if(con != null){
				con.close();
			}
		}
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if(label.equals("Exit")){
			destroyApp(true);			
		}else if(label.equals("Logon")){
			try{
				httpConnect();     
			}catch(Exception e){}
		}
	}
}