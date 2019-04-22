import java.util.*;
public class Utility {

	private Broker broker;

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

	public boolean hasDuplicateSeller(ArrayList<Seller> list, String sellerID){
		// begin
		boolean hasDuplicate = false;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).sellerID.equals(sellerID)){
				hasDuplicate = true;
			}
		}
		return hasDuplicate;
		//end
	}
}
