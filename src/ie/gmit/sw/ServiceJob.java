// Package usage declaration
package ie.gmit.sw;

//Library imports
//import javax.servlet.http.Part;


// Declaration of Service Job class
public class ServiceJob {
	// Declaration of private variables
    private Document document; 
	
	// Declaration of Service Job constructor
	public ServiceJob(Document document) {
		super();
		this.setDocument(document);
	}// End of ServiceJob constructor
	
	// Getter for Document
	public Document getDocument() {
		return document;
	}// End of getDocument

	// Setter for Document
	public void setDocument(Document document) {
		this.document = document;
	}// End of setDocument
}// End of ServiceJob