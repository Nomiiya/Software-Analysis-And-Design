import java.util.ArrayList;

public class Broker {

	public ArrayList<Listing> listedItems = new ArrayList<Listing>();
	public ArrayList<Seller> sellerList = new ArrayList<Seller>();
	public ArrayList<Buyer> buyerList = new ArrayList<Buyer>();

	public void attachSeller(String ID) {
		//begin
		Seller sNew = new Seller();
		System.out.println("Attaching Seller");
		sNew.sellerID = ID;
		sellerList.add(sNew);
		//System.out.println(sellerList.get(0).sellerID);
		//end
	}

	public void attachBuyer() {
		//begin
		//end
	}

	public void pushListing(String keywords, String ID, String cost, String description) {
		//begin
		
		//end
	}

	public void notifyBuyers() {
		//begin
		//end
	}

	public void detachSeller() {
		//begin
		//end
	}

	public void detachBuyer() {
		//begin
		//end
	}

}
