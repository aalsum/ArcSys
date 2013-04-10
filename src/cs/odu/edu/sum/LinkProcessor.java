package cs.odu.edu.sum;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.SuperCfResult;
import me.prettyprint.cassandra.service.template.SuperCfTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

import org.archive.url.WaybackURLKeyMaker;
import org.archive.util.Base32;


public class LinkProcessor {

	Keyspace ksp=null;
	String uri;
	String type="";
	
	public LinkProcessor(){
		ksp = getKeyspace();

	}
	public LinkProcessor(String uri, String type) {
		this();
		this.uri = uri;
		
		
		this.type="";
		}

	public LinkElement getLinkList() {

		ArrayList<SHARecord> shaList = getSHAList();
		
		
		
		
		return getLinkList(shaList);
	}
	private LinkElement getLinkList(ArrayList<SHARecord> shaList) {
		
		//SHA + URI
		
		LinkElement l = new LinkElement(uri);
		
		SuperCfTemplate<String, String, String> outlinkCF = CassndraCF.getOutlinkRecord(ksp);
		SuperCfTemplate<String, String, String> inlinkCF = CassndraCF.getInlinkRecord(ksp);
		ColumnFamilyTemplate<String, String> linkIdCF = CassndraCF.getLinkRecord(ksp);
		SuperCfTemplate<String, String, String> shaIdCF = CassndraCF.getShaIdRecord(ksp);
		
		
		for(SHARecord s: shaList){
			
			String [] timestampArray = new String[s.getTimestamp().size()];
			s.getTimestamp().toArray(timestampArray);
			
			//Search for all the outlink related to this sha
			SuperCfResult< String, String, String> outLinkRes = outlinkCF.querySuperColumns(s.getSha());
			//System.out.println("query sha: "+s.getSha());
			if(outLinkRes.hasResults()){
				
				Collection<String> linkIdList = outLinkRes.getSuperColumns();
				
				for(String linkId : linkIdList){
					
					ColumnFamilyResult< String, String> linkRes =  linkIdCF.queryColumns(linkId);
					if(linkRes.hasResults()){
						
						OLink oLink = new OLink(linkRes.getString("linkhref"), outLinkRes.getString(linkId,"aText"), outLinkRes.getString(linkId,"type"),new HashSet<String>(s.timestamp));						
						//System.out.println("Outlink: "+oLink);
						if(l.outLink.contains(oLink)){
							//if this link exists in outlink list (add timestamp)
							int oLinkIndex = l.outLink.indexOf(oLink);
							l.outLink.get(oLinkIndex).addTimestamp(s.timestamp);
						} else {
							//else create link with variables and add it to the outlink list.
							l.outLink.add(oLink);	
						}
						
						//p.addOLink(oLink);
					}
					
					
				}
				//p.setText(res.getString("content"));
				//System.out.println("aa"+res.getString("content"));
				
			}
		}
		
		
		String uriId = convertURIToId(uri);
		//System.out.println(uriId);
		SuperCfResult< String, String, String> inLinkRes = inlinkCF.querySuperColumns("["+uriId);
		
		
		
		//ColumnFamilyResult<String, String > res = shaRecord.queryColumns(p.getSha());
		
		if(inLinkRes.hasResults()){
			
			Collection<String> inLinkShaList = inLinkRes.getSuperColumns();
			
			
			
			for(String inLinkSha : inLinkShaList){
				
				//System.out.println("Inlink SHA: "+inLinkSha );
				SuperCfResult< String, String, String> shaIdRes =  shaIdCF.querySuperColumns(inLinkSha);
					
					if(shaIdRes.hasResults()){
						
						
						Collection<String> timelist = shaIdRes.getSuperColumns();
						
						String tmpDateTime = "";
						
						for(String tmpStr: timelist){
							tmpDateTime = tmpStr;
							break;
						}
						 HashSet<String> dtSet = new HashSet<String>();
						 
						 dtSet.addAll(timelist);
						// System.out.println("uri "+shaIdRes.getString(tmpDateTime,"uri"));
						ILink iLink = new ILink(shaIdRes.getString(tmpDateTime,"uri"),inLinkRes.getString( inLinkSha,"aText"),"href",dtSet);
						if(l.getInLinks().contains(iLink)){
							//if this link exists in outlink list (add timestamp)
							int iLinkIndex = l.getInLinks().indexOf(iLink);
							l.getInLinks().get(iLinkIndex).addTimestamp(dtSet);
						} else {
							//else create link with variables and add it to the outlink list.
							l.getInLinks().add(iLink);	
						}
						//System.out.println(iLink);
					}
			}
			
		}
	
		
		return l;
	}
	private String convertURIToId(String uriText) {
		 WaybackURLKeyMaker km = new WaybackURLKeyMaker();
	
        String linkKey = km.makeKey(uriText);
    	String linkMD5;
    	
	
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest;
	
			thedigest = md.digest(linkKey.getBytes("UTF8"));
			linkMD5 = Base32.encode(thedigest);
			return linkMD5;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
		
			
	}
	private ArrayList<SHARecord> getSHAList() {
		if(ksp==null){
			return null;
		}
		
		System.out.println(uri);
		 SuperCfTemplate<String , String, String>  cdxRecord = CassndraCF.getCDXRecord(ksp);
		 SuperCfResult< String, String, String>  res = cdxRecord.querySuperColumns(uri);

			ArrayList<SHARecord> shaList = new ArrayList<SHARecord>();

		 if(res.hasResults()){
				Collection<String> timestamps = res.getSuperColumns();
				for(String t : timestamps){
					
					SHARecord l = null;
					l= new SHARecord();
					
					String sha = res.getString(t,"sha");
					l.setSha(sha);
				//	System.out.println(t+"\t"+sha);
					if(shaList.contains(l)){
						//System.out.println("existed "+sha);
						int index = shaList.indexOf(l);
						shaList.get(index).addTimestamp(t);
					} else {
						//System.out.println("new "+sha);
						l.setUri(uri);
						l.addTimestamp(t);
						shaList.add(l);
					}
				}
		 }
		return shaList;
	}
	

	private Keyspace getKeyspace()	{
		
		Cluster myCluster = HFactory.getOrCreateCluster("IA Cluster","localhost:9160");
		Keyspace ksp = HFactory.createKeyspace("cdxKeyspace", myCluster);
//		Keyspace ksp = HFactory.createKeyspace("olympics", myCluster);

	
		return ksp;
	}
}
