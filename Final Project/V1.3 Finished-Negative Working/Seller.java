import java.util.*;
import java.io.*;

public class Seller {

	private String sellerID = " ";

	public ArrayList<Listing> listed = new ArrayList<Listing>();

	public Broker broker;

	private Listing[] listing;

	/**
	 * creates a new listing, called upon when there is a new seller coming into the database.
	 */
	public void createListing(String keywords, String ID, String cost, String description) {
		Listing newItem = new Listing();
		newItem.setTitle(keywords);
		newItem.setPrice(cost);
		newItem.setSellerID(ID);
		newItem.setDescription(description);
		this.listed.add(newItem);
	}

	/**
	 * Adds a new Listing to the listed items that this seller has. Called upon if this seller already existed and is just getting another listing added upon the database.
	 */
	public void addListing(Listing Item) {
		// Check if duplicate first
		boolean isDuplicate = false;
		for(int i = 0; i < listed.size(); i++){
			if(listed.get(i).getTitle().toUpperCase().equals(Item.getTitle().toUpperCase())){
				isDuplicate = true;
			}
		}

		if(isDuplicate){
			for(int k = 0; k < listed.size(); k++){
				if(listed.get(k).getTitle().toUpperCase().equals(Item.getTitle().toUpperCase())){
					listed.get(k).setPrice(Item.getPrice());
					listed.get(k).setDescription(Item.getDescription());
				}
			}
		}
		else{
			this.listed.add(Item);
		}
	}

	public void setSellerID(String ID){
		this.sellerID = ID;
	}

	public String getSellerID(){
		return this.sellerID;
	}
}
