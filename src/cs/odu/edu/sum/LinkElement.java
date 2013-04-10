package cs.odu.edu.sum;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LinkElement implements Comparable<LinkElement> {

	
	
	protected String uri="";
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	

	//@XmlElement
	//ArrayList<String> timestamp = new ArrayList<String>();
	@XmlElement
	ArrayList<OLink> outLink = new ArrayList<OLink>();
	@XmlElement
	ArrayList<ILink> inLink = new ArrayList<ILink>();

	public ArrayList<OLink> getOutLinks(){
		return this.outLink;
	}
	public void addOLink(OLink oLink) {
		this.outLink.add(oLink);
	}
	
	public void removeOLink(OLink oLink) {
		this.outLink.remove(oLink);
	}
	public ArrayList<ILink> getInLinks(){
		return this.inLink;
	}
	public void addILink(ILink iLink) {
		this.inLink.add(iLink);
	}
	
	public void removeILink(ILink iLink) {
		this.inLink.remove(iLink);
	}

	
	
	public LinkElement(String uri){
		this.uri = uri;
	}
	public LinkElement(){
		this.uri = "default";
	}
	/*
	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}*/
	/*
	public ArrayList<String> getTimestamp(){
		return this.timestamp;
	}
	public void addTimestamp(String timestamp) {
		this.timestamp.add(timestamp);
	}
	
	public void removeTimestamp(String timestamp) {
		this.timestamp.remove(timestamp);
	}
*/

	
	@Override
	public int compareTo(LinkElement p) {
		
		return p.getUri().compareTo(uri);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		LinkElement p = (LinkElement) obj;
		return p.getUri().equals(uri);
		
	}
	@Override
	public String toString() {
		String str = uri+"\n";
		
		for(OLink o: outLink){
			str= str +"\t"+o+"\n";
		}
		return str;
	}


	
}
