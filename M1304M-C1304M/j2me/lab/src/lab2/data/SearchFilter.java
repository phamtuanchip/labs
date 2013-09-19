package lab2.data;
import javax.microedition.rms.RecordFilter;


public class SearchFilter implements RecordFilter {
	private String searchText = null; 	
	public SearchFilter(String string) {
		searchText = string ;
	}



	public boolean matches(byte[] candidate) { 		
		String str = new String(candidate).toLowerCase(); 		
		if (searchText != null && str.indexOf(searchText) != -1) // Look for a match 		
			return true; 		else 			
				return false;
	}


	public void searchFilterClose() {
		// TODO Auto-generated method stub

	}


}
