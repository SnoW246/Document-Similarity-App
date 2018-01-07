// Package usage declaration
package ie.gmit.sw;

// Library imports
import java.io.*;
import java.util.*;
import javax.servlet.http.Part;

// Declaration of Reader class
public class Reader {
	// Declaration & initialisation of private variables
	private List<Shingle> shingleList = new ArrayList<Shingle>();
	private Part section;
	private String docID;
	private String line = null;
	private String[] words = null;
	private Shingle shingle;
	
	public Reader(Part part, String docID) {
		super();
		this.section = part;
		this.docID = docID;
	}// End of Reader function
	
	// Declaration of New Shingle function to to create a new shingle
	public List newShingle() throws IOException{
		// Initialisation of Count variable to see if shingle size gets reached
		int count = 0;
		// Read in from the document
		BufferedReader br = new BufferedReader(new InputStreamReader(section.getInputStream()));
		// Create new shingle of 3 words
		StringBuilder sb = new StringBuilder();
		
		// While control loop until line is not equal to null
		while((line = br.readLine()) != null) {
			// Ignores Commas, Spaces & other punctuation
			words = line.split("\\W+");
			
			// For control loop to go through words array until count is equal to 3
			for(int i = 0; i < words.length; i++) {
				// Increment count
				count++;
				
				// Add new word to the list of words
				sb.append(words[i]);
				// When count is 3 a shingle is created and added to a shingle list
				if(count == 3) {
					// Display the string builder input in the system console for debugging purposes
					System.out.println(sb.toString());
					// Create a new shingle with document ID & hash code
					shingle = new Shingle(this.docID, sb.toString().hashCode());
					// Add shingle to the list of shingles
					shingleList.add(shingle);
					
					// Reset of String builder when shingle is made
					sb.delete(0,  sb.length());
					// Reset of Count, to declare new shingle size to be at null
					count = 0;
				}// End of if
			}// End of for loop
		}// End of while loop
		
		// Return shingle list
		return shingleList;
		
	}// End of newShingle function
}// End of Reader class