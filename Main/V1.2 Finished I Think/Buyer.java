import java.util.*;
import java.io.*;

public class Buyer {

	public String buyerID = " ";

	public ArrayList<String> subList = new ArrayList<String>();

	public Broker broker;

	private Listing[] listing;

	/**
	 * subscribes this buyer to the keywords given. Also intializes the proper variables if it's a new buyer.
	 */
	public void subscribe(String ID, ArrayList<String> list) {
		//begin
		if(this.buyerID != " "){
			this.subList.addAll(subList.size(), list);
		}
		else{
			this.buyerID = ID;
			this.subList.addAll(subList.size(), list);
		}
		//end
	}

	/**
	 * unsubscribes this buyer from any keywords given.
	 */
	public void unsubscribe(ArrayList<String> list) {
		//begin
		ArrayList<String> keywordsList = list;

		for(int i = 0; i < keywordsList.size(); i++){ // check for all the given words
			for(int k = 0; k < this.subList.size(); k++){
				if(this.subList.get(k).toUpperCase().equals(keywordsList.get(i).toUpperCase())){
					this.subList.remove(k);
				}
			}
		}
		//end
	}

}
