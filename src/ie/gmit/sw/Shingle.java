// Package usage declaration
package ie.gmit.sw;

// Declaration of Shingle class
public class Shingle {
	private String docID;
	private int hashCode;

	// Declaration of Shingle function that takes 2 parameters (Document ID & Hash Code)
	public Shingle(String docID, int hashCode) {
		super();
		this.setDocID(docID);
		this.setHashCode(hashCode);
	}// End of Shingle function
	
	// Getter for HashCode 
	public int getHashCode() {
		return hashCode;
	}// End of getHashCode

	// Setter for HashCode
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}// End of setHashCode

	// Getter for Document ID
	public String getDocID() {
		return docID;
	}// End of getDocID
	
	// Setter for Document ID
	public void setDocID(String docID) {
		this.docID = docID;
	}// End of setDocID
}// End of Shingle class