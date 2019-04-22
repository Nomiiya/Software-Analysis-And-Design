import java.util.*;
import java.io.*;

public class Broker {

	/**
	 * Database for listed items on the C2C
	 */
	public ArrayList<Listing> listedItems = new ArrayList<Listing>();

	/**
	 * Database of all buyers in the C2C system
	 */
	public ArrayList<Buyer> buyerList = new ArrayList<Buyer>();

	/**
	 * Database of all Sellers for the C2C system
	 */
	public ArrayList<Seller> sellerList = new ArrayList<Seller>();

	/**
	 * Database of banned items from the C2C Network
	 */
	public String[] banList = new String[]{"GUN","RIFLE","HANDGUN","SHOTGUN","PISTOL","MUSKET","CANNON","SPIRITS","LIQUOR","BEER","WINE","VODKA","LOCKPICK","POISON","NUKE","NUCLEAR","RADIUM","URANIUM","ASBESTOS"};

	public Utility utility = new Utility();

	/**
	 * Attaches a seller to the broker database. Calls on pushListing and notifyBuyer if applicable.
	 */
	public String attachSeller(String keywords, String ID, String cost, String description) {
		//begin
		String rString = "";
		//Create a temp item for now
		Listing newItem = new Listing();
		newItem.setTitle(keywords);
		newItem.setPrice(cost);
		newItem.setSellerID(ID);
		newItem.setDescription(description);
		//
		boolean banned = false;
		//check if the ID is legal
		ArrayList<String> tempArr = utility.parseKeywords(keywords);
		for(int k = 0; k < tempArr.size(); k++){
			for(int i = 0; i < banList.length; i++){
				if(banList[i].toUpperCase().equals(tempArr.get(k).toUpperCase())){
					banned = true;
				}
			}
		}
		
		// If it's banned do nothing
		if(banned){
				//Banned do nothing
		}
		else{
			//if there are no sellers just add it right away
			if(sellerList.size() == 0){
				Seller nSeller = new Seller();
				nSeller.setSellerID(ID);
				nSeller.createListing(keywords, ID, cost, description);
				sellerList.add(nSeller);
				rString = pushListing(newItem);
			}
			else{
				//Check for a duplicate seller
				boolean duplicate = false;
				int sellerPos = 0;
				for(int i = 0; i < sellerList.size(); i++){
					if(sellerList.get(i).getSellerID().toUpperCase().equals(ID.toUpperCase())){
						duplicate = true;
					}
				}
				
				//If a duplicate just add to his listing, else make a new seller
				if(duplicate){
					sellerList.get(sellerPos).addListing(newItem);
					rString = pushListing(newItem);
				}
				else{
					Seller nSeller = new Seller();
					nSeller.setSellerID(ID);
					nSeller.createListing(keywords, ID, cost, description);
					sellerList.add(nSeller);
					rString = pushListing(newItem);
				}
			}
		}
		return rString;
		//end
	}

	/**
	 * Attaches a buyer to the database
	 */
	public void attachBuyer(String ID, String keywords) {
		//begin
		ArrayList<String> keyword = utility.parseKeywords(keywords);
		// Check if there is already a buyer
		boolean hasBuyer = false;
		if(buyerList.size() == 0){
			Buyer nBuyer = new Buyer();
			nBuyer.subscribe(ID, keyword);
			buyerList.add(nBuyer);
		}
		else{
			// Check if the buyer is already there
			for(int i = 0; i < buyerList.size(); i++){
				if(buyerList.get(i).buyerID.equals(ID)){
					hasBuyer = true;
				}
			}
			if(hasBuyer){
				//If there is already a buyer then just add to the sub list
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
							buyerList.get(i).subscribe(ID, keyword);
						}
					}
				}
			}
			else{
				Buyer nBuyer = new Buyer();
				nBuyer.subscribe(ID, keyword);
				buyerList.add(nBuyer);
			}
		}	
		//end
	}

	/**
	 * Pushes a listing to the listedItems if applicable. Calls on the notifyBuyer function if applicable.
	 */
	public String pushListing(Listing item) {
		//begin
		String rString = "";
		// if the listing is empty just add it
		if(listedItems.size() == 0){
				listedItems.add(item);
				rString = notifyBuyers(item);
		}
		else{// If not empty
			// Check if it's a duplicate item
			boolean isDuplicate = false;
			for(int i = 0; i < listedItems.size(); i++){
				if(listedItems.get(i).getTitle().toUpperCase().equals(item.getTitle().toUpperCase())){
					if(listedItems.get(i).getSellerID().toUpperCase().equals(item.getSellerID().toUpperCase())){
						isDuplicate = true;
					}
				}
			}

			if(isDuplicate){
				// Find the item and replace it with the new item
				for(int i = 0; i < listedItems.size(); i++){
					if(listedItems.get(i).getTitle().toUpperCase().equals(item.getTitle().toUpperCase())){
						if(listedItems.get(i).getSellerID().toUpperCase().equals(item.getSellerID().toUpperCase())){
							listedItems.get(i).setPrice(item.getPrice());
							listedItems.get(i).setDescription(item.getDescription());
							rString = notifyBuyers(listedItems.get(i));
						}
					}
				}
				
			}
			else{
				listedItems.add(item);
				rString = notifyBuyers(item);
			}
			return rString;
		}
		
		return rString;
		//end
	}

	/**
	 * Notifies the proper buyers for the new item listed. Also calls on the buyers if a subscribed item is modified.
	 */
	public String notifyBuyers(Listing item) {
		//begin
		ArrayList<String> kwList = utility.parseKeywords(item.getTitle());
		ArrayList<String> tempList = new ArrayList<String>();
		String buyerWords = "";
		String rString = "";
		if(buyerList.size() > 0){
			for(int i = 0; i < buyerList.size(); i++){ // Go through all the buyers 
				for(int k = 0; k < kwList.size(); k++){ // Go through all the keywords given
					//When the buyer is found notify them
					for(int j = 0; j < buyerList.get(i).subList.size(); j++){
						if(buyerList.get(i).subList.get(j).toUpperCase().equals(kwList.get(k).toUpperCase())){
							tempList.add(kwList.get(k));
						}
					}
				}
				if(tempList.size() != 0){
					for(int p = 0; p < tempList.size(); p++){
						buyerWords += tempList.get(p);
						if(buyerWords != "" ){
							if(p < tempList.size()-1){
								buyerWords += " ";
							}
						}
					}
				}
				if(buyerWords != ""){
					rString = "Sent to " + buyerList.get(i).buyerID
					+": A new listing named " + item.getTitle() 
					+" was just added to yaBe by " + item.getSellerID()
					+ "! The item matches your keyword(s) " + buyerWords + ". "
					+ "It is for sale at $" + item.getPrice();
					System.out.println(rString);
					buyerWords = "";
				}
			}
		}
		return rString;
		//end
	}

	/**
	 * Detaches the seller if the seller has no items being listed.
	 */
	public void detachSeller(String ID) {
		//begin
		for(int i = 0; i < sellerList.size(); i++){
			if(sellerList.get(i).listed.size() == 0){
				sellerList.remove(i);
			}
		}
		//end
	}

	/**
	 * Ubsubscribes a buyer if they wish. Fully detaches the buyer from the database if they are not subscribed to anything.
	 */
	public void detachBuyer(String ID, String keyword) {
		//begin
		ArrayList<String> keywordsList = utility.parseKeywords(keyword);

		if( buyerList.size() == 0){ // check if there are actually no buyers
			//If there are not then do nothing
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
						buyerList.get(i).unsubscribe(keywordsList);
						//If the buyer has no subscribed words just delete them
						if(buyerList.get(i).subList.size() == 0){
							buyerList.remove(i);
						}
					}
				}
			}
			else{
				//do nothing
			}

		}
		//end
	}

}
