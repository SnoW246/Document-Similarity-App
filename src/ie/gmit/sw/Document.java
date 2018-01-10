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
	public Document(String ID, String name, List list) {
		super();
		this.documentID = ID;
		this.documentName = name;
		this.shingleList = list;
	}// End of Document constructor
	
	// Getter for Shingle List
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
	public void setDocumentID(String ID) {
		this.documentID = ID;
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