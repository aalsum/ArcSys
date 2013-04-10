package cs.odu.edu.sum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

public class OLink implements Comparable<OLink>  {
	String href;
	String aText;
	String type;
	
	@XmlElement
	Set<String> timestamp = new HashSet<String>();
	
	public OLink(){
		
	}
	public Set<String> getTimestamp(){
		return this.timestamp;
	}
	public void addTimestamp(String timestamp) {
		this.timestamp.add(timestamp);
	}
	public void addTimestamp(ArrayList<String> timestamp) {
		this.timestamp.addAll(timestamp);
	}
	public void removeTimestamp(String timestamp) {
		this.timestamp.remove(timestamp);
	}

	public OLink(String href, String aText, String type, Set<String> timestamp) {
		super();
		
		
		this.href = href;
		this.aText = aText;
		this.type = type;
		this.timestamp = timestamp;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
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
		return href +"\t"+aText+"\t"+type;
	}
	@Override
	public int compareTo(OLink o) {
		return this.href.compareToIgnoreCase(o.getHref());
	}
	@Override
	public boolean equals(Object obj) {
		
		return this.href.equalsIgnoreCase(((OLink) obj).getHref()) 
				&& this.aText.equalsIgnoreCase(((OLink) obj).getaText())
				&& this.type.equalsIgnoreCase(((OLink) obj).getType());
	}
	
}
