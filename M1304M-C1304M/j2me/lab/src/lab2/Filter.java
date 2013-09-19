package lab2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import javax.microedition.rms.RecordFilter;

class Filter implements RecordFilter
{
  private String search = null;
  private ByteArrayInputStream inputstream = null;
  private DataInputStream datainputstream = null;
  public Filter(String search)
  {
    this.search = search.toLowerCase();
  }
  public boolean matches(byte[] suspect)
  {
    String string = new String(suspect).toLowerCase();
    if (string!= null && string.indexOf(search) != -1)
      return true;
    else
      return false;
  }
  public void filterClose()
  {
    try
    {
      if (inputstream != null)
      {
        inputstream.close();
      }
      if (datainputstream != null)
      {
        datainputstream.close();
      }
    }
    catch ( Exception error)
    {
    }
  }
}