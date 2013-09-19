package lab2;

import javax.microedition.rms.RecordComparator;

class StringComparator implements RecordComparator
{
  public int compare(byte[] record1, byte[] record2)
  {
    String string1 = new String(record1), 
               string2= new String(record2);
    int comparison = string1.compareTo(string2);
    if (comparison == 0)
      return RecordComparator.EQUIVALENT;
    else if (comparison < 0)
      return RecordComparator.PRECEDES;
    else
      return RecordComparator.FOLLOWS;
  }
}