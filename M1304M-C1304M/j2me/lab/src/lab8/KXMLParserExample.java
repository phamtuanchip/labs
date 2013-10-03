package lab8;
import java.io.*;
import org.kxml.*;
import org.kxml.parser.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class KXMLParserExample extends MIDlet {
	public String emp = "/employee.xml";
	XmlParser parser = null;

	protected void startApp(){
		try{
			parser = new XmlParser(new InputStreamReader(this.getClass().getResourceAsStream(emp)), 300);
			ParseEvent event = null;
			while((event = parser.read()).getType() != Xml.END_DOCUMENT){
				if(event.getType() == Xml.START_TAG){
					String name = event.getName();
					if(name != null && name.equals("details")){
						System.out.println("----------[ EMP-DETAILS ]----------");
						parseAddressTag( parser );
						System.out.println("-----------------------------------\n");
					}
					name = null;
				}
				event = null;
			}
		}catch (IOException ioe){
			System.out.println("XML Parsing Error: " + ioe);
			ioe.printStackTrace();
		}finally{
			parser = null;
		}			
	}

	protected void pauseApp(){}
	
	public void parseAddressTag( XmlParser parser ) throws IOException {
		ParseEvent event = null;
		while ((event = parser.peek()).getType() != Xml.END_DOCUMENT) {
			String name = event.getName();
			int type = event.getType();
			if (type == Xml.END_TAG && name.equals("details")) {
				event = null;
				name = null;
				return;
			}
			event = parser.read();
			if (type != Xml.START_TAG) {
				event = null;
				continue;
			}
			ParseEvent next = parser.read();
			if (next.getType() != Xml.TEXT) {
				event = null;
				next = null;
				continue;
			}
			String text = next.getText();
			System.out.println( name + ": " + text );
			event = null;
			text = null;
			next = null;
		}
	}

	protected void destroyApp(boolean arg){
		notifyDestroyed();
	}
}