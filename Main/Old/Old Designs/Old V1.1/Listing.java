public class Listing {

	private String title;

	private double price;

	private String sellerID;

	private String description;

	private int itemID;

	private boolean hasChanged = false;

	public String getTitle() {
		return this.title;
	}

	public double getPrice() {
		return this.price;
	}

	public int getID() {
		return this.itemID;
	}

	public String getDescription() {
		return this.description;
	}

}
