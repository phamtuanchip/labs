package lab2;

import javax.microedition.rms.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;

public class SortMixedRecordDataTypeExample extends MIDlet implements
                CommandListener {
        private Display display;
        private Alert alert;
        private Form form;
        private Command exit;
        private Command start;
        private RecordStore recordstore = null;
        private RecordEnumeration recordEnumeration = null;
        private MixComparator comparator = null;

        public SortMixedRecordDataTypeExample() {
                display = Display.getDisplay(this);
                exit = new Command("Exit", Command.SCREEN, 1);
                start = new Command("Start", Command.SCREEN, 1);
                form = new Form("Mixed RecordEnumeration");
                form.addCommand(exit);
                form.addCommand(start);
                form.setCommandListener(this);
        }

        public void startApp() {
                display.setCurrent(form);
        }

        public void pauseApp() {
        }

        public void destroyApp(boolean unconditional) {
        }

        public void commandAction(Command command, Displayable displayable) {
                if (command == exit) {
                        destroyApp(true);
                        notifyDestroyed();
                } else if (command == start) {
                        try {
                                recordstore = RecordStore
                                                .openRecordStore("myRecordStore", true);
                        } catch (Exception error) {
                                alert = new Alert("Error Creating", error.toString(), null,
                                                AlertType.WARNING);
                                alert.setTimeout(Alert.FOREVER);
                                display.setCurrent(alert);
                        }
                        try {
                                byte[] outputRecord;
                                String outputString[] = { "Mary", "Bob", "Adam" };
                                int outputInteger[] = { 15, 10, 5 };
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                DataOutputStream outputDataStream = new DataOutputStream(
                                                outputStream);
                                for (int x = 0; x < 3; x++) {
                                        outputDataStream.writeUTF(outputString[x]);
                                        outputDataStream.writeInt(outputInteger[x]);
                                        outputDataStream.flush();
                                        outputRecord = outputStream.toByteArray();
                                        recordstore.addRecord(outputRecord, 0, outputRecord.length);
                                        outputStream.reset();
                                }
                                outputStream.close();
                                outputDataStream.close();
                        } catch (Exception error) {
                                alert = new Alert("Error Writing", error.toString(), null,
                                                AlertType.WARNING);
                                alert.setTimeout(Alert.FOREVER);
                                display.setCurrent(alert);
                        }
                        try {
                                String[] inputString = new String[3];
                                int z = 0;
                                byte[] byteInputData = new byte[300];
                                ByteArrayInputStream inputStream = new ByteArrayInputStream(
                                                byteInputData);
                                DataInputStream inputDataStream = new DataInputStream(
                                                inputStream);
                                StringBuffer buffer = new StringBuffer();
                                comparator = new MixComparator();
                                recordEnumeration = recordstore.enumerateRecords(null,
                                                comparator, false);
                                while (recordEnumeration.hasNextElement()) {
                                        recordstore.getRecord(recordEnumeration.nextRecordId(),
                                                        byteInputData, 0);
                                        buffer.append(inputDataStream.readUTF());
                                        buffer.append(inputDataStream.readInt());
                                        buffer.append("\n");
                                        inputDataStream.reset();
                                }
                                alert = new Alert("Reading", buffer.toString(), null,
                                                AlertType.WARNING);
                                alert.setTimeout(Alert.FOREVER);
                                // display.setCurrent(alert);
                                form.append(buffer.toString());
                                inputDataStream.close();
                                inputStream.close();
                        } catch (Exception error) {
                                alert = new Alert("Error Reading", error.toString(), null,
                                                AlertType.WARNING);
                                alert.setTimeout(Alert.FOREVER);
                                display.setCurrent(alert);
                        }
                        try {
                                recordstore.closeRecordStore();
                        } catch (Exception error) {
                                alert = new Alert("Error Closing", error.toString(), null,
                                                AlertType.WARNING);
                                alert.setTimeout(Alert.FOREVER);
                                display.setCurrent(alert);
                        }
                        if (RecordStore.listRecordStores() != null) {
                                try {
                                } catch (Exception error) {
                                        alert = new Alert("Error Removing", error.toString(), null,
                                                        AlertType.WARNING);
                                        alert.setTimeout(Alert.FOREVER);
                                        display.setCurrent(alert);
                                }
                        }
                }
        }
}

