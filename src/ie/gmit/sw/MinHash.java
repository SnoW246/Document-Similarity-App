package ie.gmit.sw;

import java.io.IOException;
import java.util.*;

public class MinHash {
	private List<Document> dList = new ArrayList<Document>();
	private Document d;
	private TreeSet<Integer> hasheCodes = new TreeSet<Integer>();
	private List<Integer> newDocs = new ArrayList<Integer>();
	private List<Integer> prevDocs = new ArrayList<Integer>();
	private List<Integer> common = new ArrayList<Integer>();
	private List<Results> resultsList = new ArrayList<Results>();
	private final int MAX_MIN_HASH = 200;
	private double jaccard;
	private Results results;
	
	public MinHash(List<Document> dList, Document d) {
		super();
		this.dList = dList;
		this.d = d;
	}
	
	public List Compute(){
		hasheCodes = generateNumbers();
		newDocs = generateMinHashes(d);
		for(Document doc : dList){
			prevDocs = generateMinHashes(doc);
			common.addAll(newDocs);
			common.retainAll(prevDocs);
			System.out.println("New document size after: "+ newDocs.size());
			System.out.println("Common after change: "+ common.size());
			jaccard = ((double)common.size()) / newDocs.size();
			System.out.println("Jackard is: " + jaccard);
			results = new Results(d.getDocumentName(), doc.getDocumentName(), jaccard);
			resultsList.add(results);
			common.clear();
		}
		return resultsList;
	}
	
	public TreeSet<Integer> generateNumbers(){
		hasheCodes = new TreeSet<Integer>();
		Random r = new Random();
		for(int i=0; i < MAX_MIN_HASH; i++){
			hasheCodes.add(r.nextInt());
		}
		return hasheCodes;
	}
	
	public List<Integer> generateMinHashes(Document doc){
		List<Integer> temp = new ArrayList<Integer>();
		List<Shingle> s = new ArrayList<Shingle>();
		s = doc.getShingleList();
		System.out.println(doc.getDocumentName());
		
		for(Integer hash : hasheCodes){
			int min = Integer.MAX_VALUE;
			for(int i = 0 ; i < s.size(); i++){
				int minHash = s.get(i).getHashCode()^hash;
		        if(minHash < min){
		        	min = minHash;
			    }
			}
			temp.add(min);
		}
		return temp;
	}
}
