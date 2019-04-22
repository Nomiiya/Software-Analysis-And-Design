import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class Buenaflor_Mark_C2C {

	public static void main(String[] args) throws FileNotFoundException{
		//begin
		Broker broker = new Broker(); // Our main observer

		// Take in input
		// Get the file
		String filePath = args[0];
		System.out.println("File Path Given: " + filePath);
		Scanner sc = new Scanner(new File(filePath));

		// Find out which option and do the next action required
		// Using broker since that is the observer
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter("\\s*,\\s*");
			String option = currentLine.next();
			System.out.println("Option Given : " + option);

			switch(option.toUpperCase()){
				case("PUBLISH LISTING"):
					// 1 In: Keywords
					String keywords = currentLine.next();
					System.out.println("Keywords: " + keywords);
					// 2 In: Seller ID
					String sellerID = currentLine.next();
					System.out.println("Seller ID: " + sellerID);
					// 3 In: cost
					String cost = currentLine.next();
					System.out.println("Cost: " + cost);
					// 4 In: Description
					String description =" ";
					if(currentLine.hasNext()){
						description = currentLine.next();
						System.out.println("Description: " + description);
					}

					// Add a new seller
					broker.attachSeller(sellerID);
					// Add a new listing
					broker.pushListing(keywords, sellerID, cost, description);
					
					System.out.println("=======================\n\n");
					break;
				case("SUBSCRIBE"):
					break;
				case("UNSUBSCRIBE"):
					break;
				default:
					System.out.println("Error: Option Not Found. Please Input A Proper Option.");
					break; 
			}
		}


		// Write Out put to args[1]
		//String outputName = args[1];
		//System.out.println("Output Name: " + outputName);

		//end
	}

}
