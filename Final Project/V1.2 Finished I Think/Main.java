import java.util.Scanner;
import java.io.*;
import java.lang.Object;
import java.util.ArrayList;
public class Main {

	public static Buyer buyer = new Buyer();

	public static Seller seller = new Seller();

	public static Broker broker = new Broker();

	public static void main(String[] args) throws FileNotFoundException, IOException{
		//begin
		// Take in input
		// Get the file
		String filePath = args[0];
		Scanner sc = new Scanner(new File(filePath));
	
		String outputName = args[1];
		String lastOutput = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
		// Find out which option and do the next action required
		// Using broker since that is the observer
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter("\\s*,\\s*");
			String option = currentLine.next();

			switch(option.toUpperCase()){
				//===== Publish Listing =======
				case("PUBLISH LISTING"):
					// 1 In: Keywords
					String keywords = currentLine.next();
					// 2 In: Seller ID
					String sellerID = currentLine.next();
					// 3 In: cost
					String cost = currentLine.next();
					// 4 In: Description
					String description =" ";
					if(currentLine.hasNext()){
						description = currentLine.next();
					}

					//Make the broker Make a seller and a create a listing
					lastOutput = broker.attachSeller(keywords, sellerID, cost, description);
					
					if(lastOutput != ""){
						writer.append(lastOutput);
						writer.append("\n");
					}
					else{

					}
					break;
				//===========================
				//===========================
				case("SUBSCRIBE"):
					// Get the inputs
					String buyerID = currentLine.next();
					String subWords = currentLine.next();
					
					// Tell broker that there is a subscribe command
					broker.attachBuyer(buyerID, subWords);
					break;
				case("UNSUBSCRIBE"):
					// Get the inputs
					buyerID = currentLine.next();
					subWords = currentLine.next();

					// Tell broker that there is a detach command
					broker.detachBuyer(buyerID, subWords);
					break;
				default:
					break; 
			}
		}
		
		writer.close();

		//end
	}

}
