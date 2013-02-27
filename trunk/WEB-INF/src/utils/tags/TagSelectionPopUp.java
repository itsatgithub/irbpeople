package utils.tags;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.HiddenTag;
import org.apache.struts.taglib.html.TextTag;

import utils.actions.NavigationFunctions;
import utils.userUtils.UserUtils;

import com.justinmind.util.navigation.Menu;
import com.justinmind.util.navigation.MenuItem;
import com.justinmind.util.siteorg.SiteOrg;

/**
 * Custom tag which is used to show an html input (for a form) to select a Business object by a selecion pop-up.
 * 
 * @author Automatika - JustInMind SL
 */
public class TagSelectionPopUp extends TextTag {
	String url;
	String codeProperty;
	String objectFiled;
	
	String onSelectionButtonClick="";
	
	/** Atributos para permitir la creación de nuevos elementos */
	String zona;
	
	String selectText;
	String removeText;
	String addText;
	
	public TagSelectionPopUp(){
				
	}
	
	@Override
	public int doStartTag() throws JspException {
		doDisabled=true;
		doReadonly=true;
		return super.doStartTag();
	}
	
	protected void prepareFocusEvents(StringBuffer handlers) {
		handlers.append(" disabled=\"disabled\"");
		super.prepareFocusEvents(handlers);

    }

	/**
	 * Complete the processing of the tag. The nested tags here will restore all
	 * the original value for the tag itself and the nesting context.
	 * 
	 * @return int to describe the next step for the JSP processor
	 * @throws JspException
	 *             for the bad things JSP's do
	 */
	public int doEndTag() throws JspException {
		int i = super.doEndTag();
		/**
		 * Get date pattern from properties file.
		 */
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);
		
		selectText=apps.getString("selection.select");
		removeText=apps.getString("selection.clear");
		addText=apps.getString("selection.add");

		JspWriter out = pageContext.getOut();
		try {
			render(out, apps, request);
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		
		//add hidden
		HiddenTag hidden=new HiddenTag();
		hidden.setPageContext(pageContext);
		hidden.setParent(getParent());
		hidden.setProperty(getHiddenProperty());
		hidden.doStartTag();
		hidden.doEndTag();

		return i;
	}


	private void render(JspWriter out, ResourceBundle apps, HttpServletRequest request) throws Exception {
		

		/**
		 * If date as parameter, transform it to human date type.
		 */
		String varName = "selec"
				+ Integer.toString((new Random()).nextInt(10000));

		/**
		 * Render the javascript that enables user interaction and set the
		 * language.
		 */
		out.println("<SCRIPT LANGUAGE=\"JavaScript\" >");
		out.println("var " + varName + " = new FieldPopup();");
		out.println("</SCRIPT>");

		out.println("<input type=\"image\" onClick=\""
						+onSelectionButtonClick
						+varName
						+ ".selectTarget('"+ url+ "',"
						+"this.form['"+ getProperty()+"'],"
						+"this.form['"+ getHiddenProperty()+ "'],'"
						+ varName+"',"
						+ "'"+getObjectFiled()+"');"
						+" return false;\" TITLE=\""
						+ selectText
						+ "\" NAME=\""
						+ varName
						+ "\" ID=\""
						+ varName
						+ "\" src=\"../images/CATEGORY/action-type/popup.gif\" style=\"position:relative;top:0;left:0;border:0;\">");
		
		out.println("<input type=\"image\" onClick=\""
					    +onSelectionButtonClick
						+"this.form['"+ getProperty()+"'].value='';"
						+"this.form['"+ getHiddenProperty()+ "'].value='';"
						+" return false;\" TITLE=\""
						+ removeText
						+ "\" NAME=\""
						+ varName
						+ "\" ID=\""
						+ varName
						+ "\" src=\"../images/CATEGORY/action-type/clear.gif\" style=\"position:relative;top:0;left:0;border:0;\">");
		
		if(zona!=null && !zona.equals("")){
			
			String action = getDestinationAction(zona);	
			if(action!=null){
				out.println("<input type=\"image\" onClick=\""
						+onSelectionButtonClick
						+varName
						+ ".goToActionPopUp('"+ action + ".do', this.form['"+ getProperty()+"'], this.form['"+ getHiddenProperty()+ "'],'"
						+ varName+"');"
						+" return false;\" TITLE=\""
						+ addText
						+ "\" NAME=\""
						+ varName
						+ "\" ID=\""
						+ varName
						+ "\" src=\"../images/CATEGORY/action-type/addElement.gif\" style=\"position:relative;top:0;left:0;border:0;\">");
			}
		}

	}

	
	private String getDestinationAction(String zone){
		
		String destinationAction = null;
		
		Menu menu = null; 

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		SiteOrg siteOrg = (SiteOrg)request.getSession().getAttribute("siteorg");
		
		ActionMapping am = (ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance");
		
		// TODO: en todos los sitios en los que se recuperen las zonas hay que obtener la command bien
		String commandName = NavigationFunctions.getCommandName(am);
		String actionName  	= NavigationFunctions.getActionName(am);
		String viewId      	= "eim";

		String sectorName = null;
		boolean considerOtherSectors = true;      
		menu = siteOrg.factoryZone(zone, commandName, actionName, sectorName, considerOtherSectors, pageContext.getRequest(), viewId);

		String objType = siteOrg.actionGetParam("objType", commandName, actionName, sectorName, considerOtherSectors);
		if(objType==null || objType.equalsIgnoreCase("")) objType = siteOrg.getParameter("genericObjectName");
		if(menu != null && !menu.isEmpty())
		{
		MenuItem[] items = menu.getItems();

			for(int j=0; j < items.length; j++)
			{
				MenuItem item=items[j];    			          						        
				destinationAction = NavigationFunctions.getPopUpActionName(item.getLink());
			}
		}
		
		return destinationAction;
		        
	}
		
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String  getHiddenProperty() {
		return super.getProperty()+"."+getCodeProperty();
	}
	
	public String getCodeProperty(){
		return codeProperty;
	}

	public void setCodeProperty(String codeProperty) {
		this.codeProperty = codeProperty;
	}

	public String getObjectFiled() {
		return objectFiled;
	}

	public void setObjectFiled(String objectFiled) {
		this.objectFiled = objectFiled;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getOnSelectionButtonClick() {
		return onSelectionButtonClick;
	}

	public void setOnSelectionButtonClick(String onSelectionButtonClick) {
		this.onSelectionButtonClick = onSelectionButtonClick;
	}
}
