package lab2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import javax.microedition.rms.RecordFilter;

class FilterMix implements RecordFilter
{
  private String search = null;
  
  private ByteArrayInputStream inputstream = null;
  private DataInputStream datainputstream = null;
  public FilterMix(String searchcriteria)
  {
    search = searchcriteria;
  }
  public boolean matches(byte[] suspect)
  {
    String string = null;
    try
    {
      inputstream = new ByteArrayInputStream(suspect);
      datainputstream = new DataInputStream(inputstream);
      string = datainputstream.readUTF();
    }
    catch (Exception error)
    {
      return false;
    }
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
   catch (Exception error)
   {
   }
  }
}