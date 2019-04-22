import java.util.ArrayList;
public class Seller {

	public String sellerID = " ";

	private Broker broker;

	private Listing[] listing;

	public Listing createListing(String keywords, String ID, String cost, String description) {
		Listing newItem = new Listing();
		newItem.title = keywords;
		newItem.price = cost;
		newItem.sellerID = ID;
		newItem.description = description;
		return newItem;
	}

	public void modifyItem(Listing item, Seller seller) {

	}

}
