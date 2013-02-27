package utils.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utils.Persistent;

/**
 * This class contains static functions that are used in actions that load/save information of BOAdders.
 * 
 * @author Automatika - JustInMind SL
 */
public class BOAdderFunctions {
	public static final String BOADDER_CODE_PREFIX="_boAdderProvisional_"; 
	
	/**
	 * Returns the list of the objects created in the boadder given a list of items returned by a BOADDER
	 * @param newList list of the items returned by the BOADDER
	 * @return list of the items created in the BOADDer
	 */
	public static List obtainNewObjects(List newList){
		List result=new ArrayList();
		
		//serach for new objects
		for(Iterator i=newList.iterator(); i.hasNext();){
			Persistent newObject=(Persistent) i.next();
			if(((String)newObject.getCode()).startsWith(BOADDER_CODE_PREFIX)){
				//new object
				result.add(newObject);
			}
		}	
		return result;
	}
	
	/**
	 * Returns a list of the items deleted in a BOADDER given a list of items in a BOADDER and the original list. 
	 * @param newList list of the items returned by the BOADDer
	 * @param currentList list of the origial items
	 * @return list of the deleted items in the BOADDer
	 */
	public static List obtainRemovedObjects(List newList, List currentList){
		List result=new ArrayList();
		
		//serach for new objects
		for(Iterator i=currentList.iterator(); i.hasNext();){
			Persistent currentObject=(Persistent) i.next();
			boolean found=false;
			for(Iterator j=newList.iterator(); j.hasNext();){
				Persistent newObject=(Persistent) j.next();
				if(currentObject.getCode().equals(newObject.getCode())){
					found=true; 
					break;
				}
			}
			if(!found){
				result.add(currentObject);
			}
		}	
		return result;
	}
	
	public static String generateCode(){
		return BOAdderFunctions.BOADDER_CODE_PREFIX+(new Timestamp(System.currentTimeMillis())).toString();
	}
}
