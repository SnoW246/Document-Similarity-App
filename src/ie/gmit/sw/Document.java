// Package usage declaration
package ie.gmit.sw;

import java.util.*;

//Library imports

// Declaration of Document class
public class Document {
	// Declaration of private variables
	private List shingleList;
	private String documentID;
	private String documentName;
	
	// Declaration of Document Constructor
	public Document(String docID, String name, List shingleList) {
		super();
		this.documentID = docID;
		this.documentName = name;
		this.shingleList = shingleList;
	}// End of Document constructor
	
	// Getter fot Shingle List
	public List getShingleList() {
		return shingleList;
	}// End of getShingleList
	
	// Setter for Shingle List
	public void setShingleList(List shingleList) {
		this.shingleList = shingleList;
	}// End of setShingleList
	
	// Getter for Document ID
	public String getDocumentID() {
		return documentID;
	}// End of getDocumentID
	
	// Setter for Document ID
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}// End of setDocumentID
	
	// Getter for Document Name
	public String getDocumentName() {
		return documentName;
	}// End of getDocumentName
	
	// Setter for Document Name
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}// End of setDocumentName
}// End of Document