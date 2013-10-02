package lab8;
import java.io.*;

import org.kxml.*;
import org.kxml.io.*;
import org.kxml.kdom.*;
import org.kxml.parser.*;

import javax.microedition.io.*;
import javax.microedition.rms.*;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class AddressDB extends MIDlet{
	private static RecordStore rs = null;

	public void startApp(){
		try {
			rs = RecordStore.openRecordStore("addressbook", true);
			loadXML();
			addAddress("Sandeep Kumar Suman", "Gorakhpur");
			getName(1);
			getAddress(1);
		} catch (RecordStoreException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void addAddress(String Name, String Address)  {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(os);

		try {
			output.writeUTF(Name + "," + Address);
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		byte[] b = os.toByteArray();
		try {
			rs.addRecord(b, 0, b.length);
		} catch (RecordStoreException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static String getName(int index)  {
		int counter = 1;
		int commalocation = 0;
		String name = null;
		try {
			RecordEnumeration enumRec =  rs.enumerateRecords(null, null, false);
			while ((counter <= index) && (enumRec.hasNextElement()))   {
				String strTemp = new String(enumRec.nextRecord());
				commalocation = strTemp.indexOf(',');
				name = strTemp.substring(2, commalocation);
				counter++;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Name::::::::"+name);
		return name;		
	}

	public static String getAddress(int index)  {
		int counter = 1;
		int commalocation = 0;
		String address = null;

		try {
			RecordEnumeration enumRec = rs.enumerateRecords(null, null, false);
			while ((counter <= index) && (enumRec.hasNextElement())){
				String strTemp = new String(enumRec.nextRecord());
				commalocation = strTemp.indexOf(',');
				address = strTemp.substring(commalocation + 1);
				counter++;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Address::::::::"+address);
		return address;
	}

	public static int recordCount(){
		int count = 0;
		try  {
			count = rs.getNumRecords();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return count;
	}

	public void loadXML()  {
		StreamConnection c = null;
		InputStream s = null;
		Attribute att = null;
		String Name = null;
		String Address = null;
		try {
			rs.closeRecordStore();
			rs.deleteRecordStore("addressbook");
			rs = RecordStore.openRecordStore("addressbook", true);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			c = (StreamConnection)Connector.open("http://localhost:8080/AddressBook.xml");
			s = c.openInputStream();

			XmlParser parser = new XmlParser(new InputStreamReader(s));
			Document document = new Document();
			document.parse(parser);
			System.out.println(document);
			Element root = document.getRootElement();
			int children = root.getChildCount();
			
			for(int i=0; i < children; i++) {
				if (root.getType(i) == Xml.ELEMENT) {
					Element el = root.getElement(i);
					int babies = el.getChildCount();
					for(int j=0; j < babies; j++){
						if (el.getType(j) == Xml.ELEMENT) {
							Element elName = (Element)el.getChild(j);
							if (elName.getName().equals("name")){
								att = elName.getAttribute("value");
								Name = att.getValue();
							} else if (elName.getName().equals("address")){
								att = elName.getAttribute("value");
								Address = att.getValue();
							}
						}
					}
					addAddress(Name, Address);
				}
			}
		} catch (IOException err) {
			System.out.println(err);
			err.printStackTrace();
		}
	}
		
	public void pauseApp(){}
	
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}