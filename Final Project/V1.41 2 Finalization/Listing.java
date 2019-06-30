public class Listing {

	private String title = " ";

	private String price = " ";

	private String sellerID = " ";

	private String description = " ";

	public void setTitle(String title){
		//begin
		this.title = title;
		//end
	}

	public void setPrice(String price){
		//begin
		this.price = price;
		//end
	}

	public void setSellerID(String ID){
		//begin
		this.sellerID = ID;
		//end
	}

	public void setDescription(String desc){
		//begin
		this.description = desc;
		//end
	}

	public String getTitle(){
		//begin
		return this.title;
		//return null;
		//end
	}

	public String getPrice(){
		//begin
		return this.price;
		//return null;
		//end
	}

	public String getSellerID(){
		//begin
		return this.sellerID;
		//return null
		//end
	}

	public String getDescription(){
		//begin
		return this.description;
		//return null
		//end
	}

}
