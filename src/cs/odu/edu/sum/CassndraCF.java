package cs.odu.edu.sum;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.SuperCfTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftSuperCfTemplate;
import me.prettyprint.hector.api.Keyspace;

public class CassndraCF {

	public static SuperCfTemplate<String , String, String>  getCDXRecord(Keyspace ksp){
		SuperCfTemplate<String , String, String> cdxRecord = 
				new ThriftSuperCfTemplate<String , String, String>(ksp, 
						"cdxRow", StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
						//"cdxRecord", StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
		return cdxRecord;
		
	}

	public static SuperCfTemplate<String , String, String>  getInlinkRecord(Keyspace ksp){
		SuperCfTemplate<String , String, String> cdxRecord = 
				new ThriftSuperCfTemplate<String , String, String>(ksp, 
						"inlink", StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
		return cdxRecord;
		
	}

	public static ColumnFamilyTemplate<String, String> getLinkRecord(Keyspace ksp){
		ColumnFamilyTemplate<String, String> linkRecord = 
	            new ThriftColumnFamilyTemplate<String, String>(ksp,
	                                                           "link", 
	                                                           StringSerializer.get(),        
	                                                           StringSerializer.get());
		return linkRecord;
	
	}

	public static SuperCfTemplate<String , String, String>  getOutlinkRecord(Keyspace ksp){
		SuperCfTemplate<String , String, String> cdxRecord = 
				new ThriftSuperCfTemplate<String , String, String>(ksp, 
						"outlink", StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
		return cdxRecord;
		
	}

	public static SuperCfTemplate<String , String, String> getShaIdRecord(Keyspace ksp) {
		SuperCfTemplate<String , String, String> shaRow = 
				new ThriftSuperCfTemplate<String , String, String>(ksp, 
						"shaRow", StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
		return shaRow;
	}

	public static ColumnFamilyTemplate<String, String> getSHARecord(Keyspace ksp){
		ColumnFamilyTemplate<String, String> shaRecord = 
	            new ThriftColumnFamilyTemplate<String, String>(ksp,
	                                                           "shaRecord", 
	                                                           StringSerializer.get(),        
	                                                           StringSerializer.get());
		return shaRecord;
	
	}

}
