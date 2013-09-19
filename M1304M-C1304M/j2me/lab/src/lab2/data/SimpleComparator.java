package lab2.data;

public class SimpleComparator
    implements javax.microedition.rms.RecordComparator {
  public int compare(byte[] rec1, byte[] rec2) {
    int limit = Math.min(rec1.length, rec2.length);

    for (int index = 0; index < limit; index++) {
      if (rec1[index] < rec2[index])
        return PRECEDES;
      else if (rec1[index] > rec2[index])
        return FOLLOWS;
    }
    return EQUIVALENT;
  }
}