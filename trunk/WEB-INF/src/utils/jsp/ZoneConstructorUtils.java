package utils.jsp;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;

import utils.actions.NavigationFunctions;

import bussineslogic.controlers.UseCase;

import utils.jsp.ZoneConstructorUtils;
import com.justinmind.util.navigation.Menu;
import com.justinmind.util.navigation.MenuItem;
import com.justinmind.util.siteorg.SiteOrg;

/**
 * This class is a 'helper' to create the zones (with its buttons).
 * Given a zone name and some information of the current page (where the siteorg can be obtained) the structure of actions are obtained. Some methods to organize and navigate throw the actions are implemented.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class ZoneConstructorUtils {
	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(ZoneConstructorUtils.class);
	
    protected static final int VIEW_LIST_ROW_ACTION_ZONE=1;
    protected static final int VIEW_LIST_LINK_ZONE=2;
    protected static final int LINK_ZONE=3;
    protected static final int SUBMIT_ZONE=4;
	
	/**
	 * Class that contains all the information to create a button that calls an action in a zone
	 * @author Automatika - JustInMind SL
	 */
	public class RefActionButtonData{
		/**
		 * Caption of the Action
		 */
		String caption;
		/**
		 * Concept of the action
		 */
		String concept;
		/**
		 * Submit function to call in case that the button of the action is executed
		 */
		String submitFunctionCall;
		/**
		 * True if the current action is the one represented by the data
		 */
		boolean isCurrent;
		
		/**
		 * Creates a RefActionButtonData given a MenuItem
		 * @param item item with the data
		 */
		private RefActionButtonData(MenuItem item) {
			boolean popup = item.containsParameter("isPopUp");
			
			String currentCode="null";
			
			String newActionObject=getSiteOrg().actionGetParam("obj", item.getCommand(), item.getAction(), getSectorName(), getConsiderOtherSectors());
			String objectsRecievedType=getSiteOrg().actionGetParam("objectsRecievedType", item.getCommand(), item.getAction(), getSectorName(), getConsiderOtherSectors());
			if(objectsRecievedType==null){
				objectsRecievedType=newActionObject;
			}
			
			String require=getSiteOrg().actionGetParam("objectsRecieved", item.getCommand(), item.getAction(), getSectorName(), getConsiderOtherSectors());
			
			
			if(require==null || require.equals("none")){
				require="false";
			}else{
				require="true";
				
				
				String currentActionObject=getActionObj();
				if(objectsRecievedType.equalsIgnoreCase(currentActionObject)){
						try {
							String pageCode=(String)TagUtils.getInstance().lookup(pageContext, getActionMapping().getName(), "mainFormBeanCode", null);
							if(pageCode!=null) currentCode="'"+pageCode+"'";
						} catch (JspException e) {
							log.error(e);
						}
					
					
				}
			}
			
			concept = item.getCon();
			if (concept!=null){
				concept = concept;
			}else concept="";
			
		    String multi = item.getParameter("multi");
	        if(multi == null) multi = "false";
	        
	        submitFunctionCall = null;
	        String destination = "";
	                
	        if(popup)
	        {            	
				destination = NavigationFunctions.getPopUpActionName(item.getLink());        	
	        	submitFunctionCall = "openViewdetailsPopupOperation" + "('" + destination +"',document.forms[0],"+require+","+currentCode+",'"+objectsRecievedType+"');"; 
	        }
	        else
	        {
	        	destination = item.getLink();
	            submitFunctionCall = (multi.equals("true") ? "submitMultipleAction" : "submitAction") + "('" + destination +"',document.forms[0],"+require+","+currentCode+",'"+objectsRecievedType+"');"; 
	        }    
	        isCurrent = (item.getLink().replaceFirst("../","").equals(JspUtils.getCurrentActionName(request)));
	        caption=JspUtils.quote(item.getCaptionButton());
		}

		/**
		 * @return Caption of the Action
		 */
		public String getCaption() {
			return caption;
		}

		/**
		 * @return Concept of the action
		 */
		public String getConcept() {
			return concept;
		}

		/**
		 * @return True if the current action is the one represented by the data
		 */
		public boolean isCurrent() {
			return isCurrent;
		}

		/**
		 * @return Submit function to call in case that the button of the action is executed
		 */
		public String getSubmitFunctionCall() {
			return submitFunctionCall;
		}


	}

	/**
	 * Private class used to iterate over the actions
	 * @author Automatika - JustInMind SL
	 */
	private class ActionIterator implements Iterator<RefActionButtonData>{
		int i;
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return (hasItems() && i < getItems().length);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public RefActionButtonData next() {
			MenuItem item=getItems()[i];
			++i;
			return new RefActionButtonData(item);
		}

		/**
		 * This method is not implemented
		 */
		public void remove() {}	
	}
	
	HttpServletRequest request;
	String zoneName;
	PageContext pageContext;
	
    public static ZoneConstructorUtils getViewListRowActionZone(HttpServletRequest request, PageContext pageContext, String zoneName, Object sentformBean){
    	//return new ZoneConstructorUtils(request, pageContext, VIEW_LIST_ROW_ACTION_ZONE, zoneName, sentObjectName, sentformBean);
    	return new ZoneConstructorUtils(request, pageContext, zoneName);
    }

	/**
	 * Creates a new ZoneConstructorUtils
	 * @param request current request
	 * @param pageContext current pageContext
	 * @param zoneName name of the zone to manage
	 */
	public ZoneConstructorUtils(HttpServletRequest request, PageContext pageContext, String zoneName){
		this.request=request;
		this.zoneName=zoneName;
		this.pageContext=pageContext;
	}

	/**
	 * @return returns an iterator to iterate over the actions of the zone
	 */
	public Iterator<RefActionButtonData> getActionsIterator(){
		return new ActionIterator();
	}
	
	/**
	 * @return returns an iterable of the data of the actions of the zone
	 */
	public Iterable<RefActionButtonData> getActions(){
		return new Iterable<RefActionButtonData>(){
			public Iterator<RefActionButtonData> iterator() {
				return getActionsIterator();
			}
		};
	}
	
	private final MenuItem[] getItems(){
		return getMenu().getItems();
	}
	
	/**
	 * @return Returns true if there are items in the zone
	 */
	public final boolean hasItems(){
		return (getMenu() != null && !getMenu().isEmpty()); 
	}
	
	/**
	 * @return returns the object represented by the action
	 */
	final String getActionObj(){ 
		String sectorName = null;
		boolean considerOtherSectors = true;    
		String objType = getSiteOrg().actionGetParam("obj", getCommandName(), getActionName(), sectorName, considerOtherSectors);
		String actionType=getSiteOrg().actionGetParam("type", getCommandName(), getActionName(), sectorName, considerOtherSectors);
		
		if(objType==null || actionType.equalsIgnoreCase("viewlist")) objType = getSiteOrg().getParameter("");
		
		return objType;
	}
	
	/**
	 * @return returns the current siteorg
	 */
	final SiteOrg getSiteOrg(){
		return (SiteOrg)request.getSession().getAttribute("siteorg");
	}
	
	/**
	 * @return returns the current action mapping
	 */
	final ActionMapping getActionMapping(){
		return (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");
	}
	
	/**
	 * @return returns the current command name
	 */
	final String getCommandName(){
		return NavigationFunctions.getCommandName(getActionMapping());
	}
	
	/**
	 * @return returns the current action name
	 */
	final String getActionName(){
		return NavigationFunctions.getActionName(getActionMapping());
	}
	
	/**
	 * @return returns the menu of the zone
	 */
	final Menu getMenu(){
		String viewId=null;
		Menu menu = getSiteOrg().factoryZone(zoneName, getCommandName(), getActionName(), getSectorName(), getConsiderOtherSectors(), pageContext.getRequest(), viewId);
		return menu;
	}
	
	final boolean getConsiderOtherSectors(){
		return true;
	}
	
	final String getSectorName(){
		return null;
	}
	
	/**
	 * @return Returns the caption of the zone
	 */
	public String getCaption(){
		return getMenu().getCaption();
	}

}
