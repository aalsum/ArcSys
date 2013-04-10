package cs.odu.edu.sum.test;

import java.util.Arrays;

import org.junit.Test;

import cs.odu.edu.sum.ILink;
import cs.odu.edu.sum.LinkElement;
import cs.odu.edu.sum.LinkProcessor;
import cs.odu.edu.sum.OLink;

public class testLinkService {

	@Test
	public void test() {
		//LinkProcessor processor = new LinkProcessor("tn.gov/tsla/history/military/ww1intro.htm","");
		LinkProcessor processor = new LinkProcessor("vancouver2010.com/","");
		LinkElement pList = processor.getLinkList();
	//	System.out.println(pList.getOutLinks());
		
		OLink[] oL = new OLink[pList.getOutLinks().size()];
		
		pList.getOutLinks().toArray(oL);
		Arrays.sort(oL);
		for(OLink o:oL ){
			System.out.println("Outlink: "+o);
		}
		
	//	System.out.println("=================");
		
//		ILink[] iL = new ILink[pList.getInLinks().size()];
//		
//		pList.getInLinks().toArray(iL);
//		Arrays.sort(iL);
//		for(ILink o:iL ){
//			System.out.println("Ilink "+o);
//		}
	}

}
