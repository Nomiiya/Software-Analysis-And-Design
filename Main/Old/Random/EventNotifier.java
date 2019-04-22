//begin
import java.util.Scanner;
import java.util.*;
import java.io.*;
//end

public class EventNotifier {

	private ItemListing listedItems[];
	private Buyer buyerList[];
	private Seller sellerList[];
	Utility utl = new Utility();

	public static void main(String args[]) {
		//begin
		EventNotifier evn = new EventNotifier();
		System.out.println("Hello, in Main.");
		// Call check input forever
		for(;;){
			evn.checkInput();
		}
		//end
	}

	public void checkInput() {
		//begin
		String input = utl.getNextInput().toUpperCase();
		System.out.println("USER INPUT: " + input);
		// Take in the input and then check for which option they chose
		switch(input.toUpperCase()){
			case("PUBLISH LISTING"):
				Buyer nb = createSeller();
				
				break;
			case("SUBSCRIBE"):
				System.out.println("Hello");
				break;
			case("UNSUBSCRIBE"):
				System.out.println("Hello");
				break;
			default:
				System.out.println("Error: Option Not Found. Please Input A Proper Option.");
				break;
		}
		//end
	}

	public Seller createSeller() {
		return null;
		//begin

		//end
	}

	public Buyer createBuyer() {
		//begin
		//end
		return null;
	}

	public void removeSeller(String ID) {
		//begin
		//end
	}

	public void removeBuyer(String ID) {
		//begin
		//end
	}

	public void publishListing(ItemListing item) {
		//begin
		//end
	}

	public void notifyBuyer() {
		//begin
		//end
	}

	public void modifyItem(ItemListing item, Seller seller) {
		//begin
		//end

	}

	public void subscribe(Buyer buyer) {
		//begin
		//end
	}



}
