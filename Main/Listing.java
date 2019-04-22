public class Listing {

	private String title = " ";

	private String price = " ";

	private String sellerID = " ";

	private String description = " ";

	public void setTitle(String title){
		this.title = title;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public void setSellerID(String ID){
		this.sellerID = ID;
	}

	public void setDescription(String desc){
		this.description = desc;
	}

	public String getTitle(){
		return this.title;
	}

	public String getPrice(){
		return this.price;
	}

	public String getSellerID(){
		return this.sellerID;
	}

	public String getDescription(){
		return this.description;
	}

}
