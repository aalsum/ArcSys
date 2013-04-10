package cs.odu.edu.sum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

public class ILink implements Comparable<ILink> {
	
	String uri;
	String aText;
	String type;
	
	@XmlElement
	Set<String> timestamp = new HashSet<String>();
	
	public ILink(){
		String uri="";
		String aText="";
		String type="";
		
	}
	public Set<String> getTimestamp(){
		return this.timestamp;
	}
	public void addTimestamp(String timestamp) {
		this.timestamp.add(timestamp);
	}
	public void addTimestamp(Set<String> timestamp) {
		this.timestamp.addAll(timestamp);
	}
	public void removeTimestamp(String timestamp) {
		this.timestamp.remove(timestamp);
	}

	public ILink(String uri, String aText, String type, Set<String> timestamp) {
		//super();
		
		//System.out.println("Constructor: "+uri);
		this.uri = uri;
		this.aText = aText;
		this.type = type;
		this.timestamp = timestamp;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getaText() {
		return aText;
	}
	public void setaText(String aText) {
		this.aText = aText;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return uri +"\t"+aText+"\t"+type;
	}
	@Override
	public int compareTo(ILink o) {
		return this.uri.compareToIgnoreCase(o.getUri());
	}
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null){
			return false;
		}
		if(this.uri !=null && this.uri.equalsIgnoreCase(((ILink) obj).getUri()) 
				&& this.aText != null && this.aText.equalsIgnoreCase(((ILink) obj).getaText())){
			return true;
		}
		return false;
	}
	
	
}
