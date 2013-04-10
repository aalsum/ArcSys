package cs.odu.edu.sum;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.catalina.connector.Request;


@Path("/linkQuery")
public class LinkQuery {
	/*@Context
	UriInfo uriInfo;
	@Context
	Request request;
	*/
	String uri;
	
	public LinkQuery(){
		this.uri ="";
	}
	

	public LinkQuery(String uri){
		this.uri = uri;
	}
	

	
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.TEXT_XML,
		MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_OCTET_STREAM})
	public LinkElement sayXMLHello(@QueryParam("uri")String uri, @QueryParam("type")String type) {
		System.out.println(uri);
		LinkProcessor processor = new LinkProcessor(uri,type);
		
		LinkElement pageList = processor.getLinkList();	
		//System.out.println(uri+"\t"+pageList.size());
		return pageList;
	}
	
}
