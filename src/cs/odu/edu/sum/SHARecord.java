package cs.odu.edu.sum;

import java.util.ArrayList;

public class SHARecord implements Comparable<SHARecord>{
	String uri;
	String sha;
	ArrayList<String> timestamp;
	public SHARecord() {
		super();
		timestamp = new ArrayList<String>();
	}
	public ArrayList<String> getTimestamp(){
		return this.timestamp;
	}
	public void addTimestamp(String timestamp) {
		this.timestamp.add(timestamp);
	}
	
	public void removeTimestamp(String timestamp) {
		this.timestamp.remove(timestamp);
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public SHARecord(String uri, String sha, ArrayList<String> timestamp) {
		//super();
		this.uri = uri;
		this.sha = sha;
		this.timestamp = timestamp;
	}
	public SHARecord(String uri, String sha) {
		this();
		this.uri = uri;
		this.sha = sha;
	}
	@Override
	public String toString() {
		String str =  "SHARecord [uri=" + uri + ", sha=" + sha + ", timestamp=";
		
		for(String s: timestamp){
			str+=s+",";
		}
		str+="]";
		return str;
				 
	}
	@Override
	public int compareTo(SHARecord s) {
		return s.getSha().compareTo(sha);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		SHARecord p = (SHARecord) obj;
		return p.getSha().equals(sha);
		
	}

}
