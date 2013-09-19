package lab2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import javax.microedition.rms.RecordComparator;

class MixComparator implements RecordComparator {
    private byte[] comparatorInputData = new byte[300];
    private ByteArrayInputStream comparatorInputStream = null;
    private DataInputStream comparatorInputDataType = null;

    public int compare(byte[] record1, byte[] record2) {
            int record1int, record2int;
            try {
                    int maxlen = Math.max(record1.length, record2.length);
                    if (maxlen > comparatorInputData.length) {
                            comparatorInputData = new byte[maxlen];
                    }
                    comparatorInputStream = new ByteArrayInputStream(record1);
                    comparatorInputDataType = new DataInputStream(comparatorInputStream);
                    comparatorInputDataType.readUTF();
                    record1int = comparatorInputDataType.readInt();
                    comparatorInputStream = new ByteArrayInputStream(record2);
                    comparatorInputDataType = new DataInputStream(comparatorInputStream);
                    comparatorInputDataType.readUTF();
                    record2int = comparatorInputDataType.readInt();
                    if (record1int == record2int) {
                            return RecordComparator.EQUIVALENT;
                    } else if (record1int < record2int) {
                            return RecordComparator.PRECEDES;
                    } else {
                            return RecordComparator.FOLLOWS;
                    }
            } catch (Exception error) {
                    return RecordComparator.EQUIVALENT;
            }
    }

    public void compareClose() {
            try {
                    if (comparatorInputStream != null) {
                            comparatorInputStream.close();
                    }
                    if (comparatorInputDataType != null) {
                            comparatorInputDataType.close();
                    }
            } catch (Exception error) {
            }
    }
}