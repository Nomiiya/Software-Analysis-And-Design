import java.util.*;
import java.io.*;

public class Utility {

	/**
	 * Helper Function:
	 *    Parses a string of keywords for ease of access.
	 */
	public ArrayList<String> parseKeywords(String Keywords) {
		//begin
		ArrayList<String> kwList = new ArrayList<String>();
		Scanner sc = new Scanner(Keywords);
		sc.useDelimiter(" ");
		while(sc.hasNext()){
			kwList.add(sc.next());
		}
		return kwList;
		//end
	}

	/**
	 * Helper Function: Helps check out an arrayList
	 */
	public boolean hasDuplicateSeller(ArrayList<Seller> list, String sellerID) {
		// begin
		boolean hasDuplicate = false;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getSellerID().equals(sellerID)){
				hasDuplicate = true;
			}
		}
		return hasDuplicate;
		//end
	}

}
