package lab8;
import java.io.*;
import org.kxml.*;
import org.kxml.kdom.*;
import org.kxml.parser.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class KXMLExample extends MIDlet {
	XmlParser parser = null;
	Document doc = new Document();
	public String emp = "/employee.xml";

	protected void startApp(){
		try {
			InputStream in = this.getClass().getResourceAsStream(emp);
			InputStreamReader is = new InputStreamReader(in);
			parser = new XmlParser( is );
			doc.parse( parser );
			parser = null;
		} catch (IOException ioe) {
			System.err.println("XML Parsing Error: " + ioe);
			ioe.printStackTrace();
			parser = null;
			doc = null;
			return;
		} 
		Element root = doc.getRootElement();
		int child_count = root.getChildCount();

		for (int i = 0; i < child_count ; i++ ) {
			if (root.getType(i) != Xml.ELEMENT) {
				continue;
			}

			Element element = root.getElement(i);
			if (!element.getName().equals("details")) {
				continue;
			}

			System.out.println("----------[ EMP-DETAILS ]----------");
			int address_item_count = element.getChildCount();

			for (int j = 0; j < address_item_count ; j++) {
				if (element.getType(j) != Xml.ELEMENT) {
					continue;
				}
				Element item = element.getElement(j);
				System.err.println( item.getName() + ": " + item.getText(0));
				item = null;
			}
			System.out.println("-----------------------------------\n");
			element = null;
		}		
	}

	protected void pauseApp(){}

	protected void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}