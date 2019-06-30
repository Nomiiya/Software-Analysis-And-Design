import java.util.*;
import java.io.*;

public class Broker {

	public ArrayList<Listing> listedItems = new ArrayList<Listing>();

	public ArrayList<Buyer> buyerList = new ArrayList<Buyer>();

	public ArrayList<Seller> sellerList = new ArrayList<Seller>();

	public Utility utility = new Utility();

	public void attachSeller(String ID) {
		//begin
		//if there are no sellers just add it right away
		if(sellerList.size() == 0){
			
		}

		Seller newSeller = new Seller();
		newSeller.sellerID = ID;
		if(!utility.hasDuplicateSeller(sellerList, ID)){
			sellerList.add(newSeller);
		}
		else{
		}
		//end
	}

	public void attachBuyer(String ID, String keywords) {
		//begin
		ArrayList<String> keyword = utility.parseKeywords(keywords);
		// Check if there is already a buyer
		boolean hasBuyer = false;
		if(buyerList.size() == 0){
			Buyer nBuyer = new Buyer();
			nBuyer.buyerID = ID;
			nBuyer.subList.addAll(nBuyer.subList.size(), keyword);
			buyerList.add(nBuyer);
			//System.out.println(buyerList.get(0).subList);
		}
		else{
			for(int i = 0; i < buyerList.size(); i++){
				if(buyerList.get(i).buyerID.equals(ID)){
					hasBuyer = true;
				}
			}
			if(hasBuyer){
				//If there is already a buyer then just add to the sub list
				boolean isThere = false;
				for(int i = 0; i < buyerList.size(); i++){
					if(buyerList.get(i).buyerID.equals(ID)){ // If it is the buyer then check if there is already the word there
						boolean isSubbed = false;
						for(int k = 0; k < keyword.size(); k++){ // go through all given words
							for(int j = 0; j < buyerList.get(i).subList.size(); j++){
								if(buyerList.get(i).subList.get(j).toUpperCase().equals(keyword.get(k).toUpperCase())){
									isSubbed = true;
								}
							}
						}
						if(isSubbed){

						}
						else{
							buyerList.get(i).subList.addAll(buyerList.get(i).subList.size(), keyword);
						}
						//System.out.println(buyerList.get(i).subList);
					}
				}
			}
			else{
				Buyer nBuyer = new Buyer();
				nBuyer.buyerID = ID;
				nBuyer.subList = keyword;
				buyerList.add(nBuyer);
			}
		}	
			
		
		//end
	}

	public String pushListing(Listing item) {
		//begin
		// Check if it's a duplicate item
		//if()
		String rString = " ";
		boolean isDuplicate = false;
		for(int i = 0; i < listedItems.size(); i++){
			if(listedItems.get(i).title.toUpperCase().equals(item.title.toUpperCase())){
				//System.out.println("HEEEEEEEEEEEE");
				isDuplicate = true;
			}
		}

		if(isDuplicate){
			//System.out.println("HEEEEEEEEEEEEdsadasd");
			// Find the item and replace it with the new item
			for(int i = 0; i < listedItems.size(); i++){
				if(listedItems.get(i).title.toUpperCase().equals(item.title.toUpperCase())){
					if(listedItems.get(i).sellerID.toUpperCase().equals(item.sellerID.toUpperCase())){
						listedItems.get(i).price = item.price;
						listedItems.get(i).description = item.description;
						rString = notifyBuyers(listedItems.get(i));
					}
				}
			}
			
		}
		else{
			//System.out.println("asdfadsafHEEEEEEEEEEEEdsadasd");
			listedItems.add(item);
			rString = notifyBuyers(item);
		}
		return rString;

		//end
	}

	public String notifyBuyers(Listing item) {
		//begin
		ArrayList<String> kwList = utility.parseKeywords(item.title);
		String buyerWords = " ";
		String rString = " ";
		if(buyerList.size() > 0){
			for(int i = 0; i < buyerList.size(); i++){ // Go through all the buyers 
				for(int k = 0; k < kwList.size(); k++){ // Go through all the keywords given
					//When the buyer is found notify them
					for(int j = 0; j < buyerList.get(i).subList.size(); j++){
						if(buyerList.get(i).subList.get(j).toUpperCase().equals(kwList.get(k).toUpperCase())){
							buyerWords += kwList.get(k);
							buyerWords += " ";
						}
					}
				}
				if(buyerWords != " "){
					rString = "Sent to " + buyerList.get(i).buyerID
					+": A new listing named " + item.title 
					+" was just added to yaBe by " + item.sellerID
					+ "! The item matches your keyword(s)" + buyerWords + ". "
					+ "It is for sale at $" + item.price;
					System.out.println(rString);
					buyerWords = " ";
				}
			}
		}
		return rString;
		//end
	}

	public void detachSeller(String ID) {
		//begin

		//end
	}

	public void detachBuyer(String ID, String keywords) {
		//begin
		ArrayList<String> keywordsList = utility.parseKeywords(keywords);

		if( buyerList.size() == 0){ // check if there are actually no buyers
			//System.out.println("ERROR: No Buyers In List");
		}
		else{
			boolean isBuyer = false;
			// Check for the buyer 
			for(int i = 0; i < buyerList.size(); i++){
				if(buyerList.get(i).buyerID.equals(ID)){
					isBuyer = true;
				}
			}
			if(isBuyer){
				for(int i = 0; i < buyerList.size(); i++){ // Find the  buyer
					if(buyerList.get(i).buyerID.equals(ID)){ // If it's the buyer continue
						for(int k = 0; k < keywordsList.size(); k++){ // check for all the given words
							for(int j = 0; j < buyerList.get(i).subList.size(); j++){
								if(buyerList.get(i).subList.get(j).toUpperCase().equals(keywordsList.get(k).toUpperCase())){
									buyerList.get(i).subList.remove(j);
								}
							}	
						}
						//System.out.println(buyerList.get(i).subList);
					}
				}
			}
			else{
				//System.out.println("ERROR: NO BUYER [" + ID + "] KNOWN.");
			}

		}
		//end
	}

}
