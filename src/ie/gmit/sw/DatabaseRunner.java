package ie.gmit.sw;

import java.util.*;
import java.io.*;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentActivationSupport;
import com.db4o.ta.TransparentPersistenceSupport;

import xtea_db4o.XTEA;
import xtea_db4o.XTeaEncryptionStorage;

public class DatabaseRunner implements Database{
	private ObjectContainer db = null;
	private List<Document> dList = new ArrayList<Document>();
	private Reader r;
	private List<Shingle> shingles = new ArrayList<Shingle>();
	private Document d;

	public DatabaseRunner() throws IOException {
		System.out.println("in db40 DATABASE");
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		//Real lazy. Saves all the config commented out below
		config.common().add(new TransparentActivationSupport()); 
		//Lazier still. Saves all the config commented out below
		config.common().add(new TransparentPersistenceSupport()); 
		config.common().updateDepth(7); //Propagate updates
		
		// Encryption
		config.file().storage(new XTeaEncryptionStorage("password", XTEA.ITERATIONS64));

		// Open a local database. 
		// Use Db4o.openServer(config, server, port) for full client / server
		//  Need to add it
		db = Db4oEmbedded.openFile(config, "documents.data");
		dList = getDocuments();
		
		if(dList.size() == 0) {
			init(); //Populate files in directory and put into db4o
		}
		showDocuments();
	}

	private void init() throws IOException {
		System.out.println("in init");
		int i = 0;
		// Find existing files in the directory
		File dir = new File("C:/Users/Adrian/Documents/Workspace/DocumentSimilarityApp/Files/");
		// loop through folder, put files into shingles and document objects, save it to database
		for (File file : dir.listFiles()) {
	   	   i++;
		   r = new Reader(file, "r" + i);
		   shingles = r.newShingle();
		   d = new Document("r" + i, file.getName(), shingles);
		   // Save 
		   dList.add(d);
		}
		addFilesToDatabase();
	}
	
	private void addFilesToDatabase(){
		for(Document d: dList)
		{
			// Adds document objects to database
			db.store(d);
		}
		// Commits the transaction
		db.commit();
	}
	
	public void addDocumentsToDatabase(Document d) {
		db.store(d);
		db.commit();	
	}
	
	public void showDocuments()
	{
		//An ObjectSet is a specialised List for storing results
		ObjectSet<Document> documents = db.query(Document.class);
		for (Document document : documents) {
			System.out.println("Document: " + document.getDocumentName() + "\t -->Database Object ID: " + db.ext().getID(document));
			db.commit();
		}
	}
	
	public void closeDB()
	{
		db.close();
	}
	
	public List<Document> getDocuments()
	{
		List<Document> temp = new ArrayList<Document>();
		ObjectSet<Document> documents = db.query(Document.class);
		System.out.println(" In get documents "+ documents.size());
		for (Document document : documents) {
			temp.add(document);
			db.commit();
		}
		return temp;	
	}
	
	public static void main(String[] args) throws IOException
	{
		new DatabaseRunner();
	}
}