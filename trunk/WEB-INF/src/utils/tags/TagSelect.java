package utils.tags;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.OptionsCollectionTag;
import org.apache.struts.taglib.html.SelectTag;

import utils.actions.NavigationFunctions;
import utils.userUtils.UserUtils;

import com.justinmind.util.navigation.Menu;
import com.justinmind.util.navigation.MenuItem;
import com.justinmind.util.siteorg.SiteOrg;

/**
 * Custom tag which is used to show an html input (for a form) to select a Business object.
 * 
 * @author Automatika - JustInMind SL
 */
public class TagSelect extends SelectTag {
	/**
	 * Complete the processing of the tag. The nested tags here will restore all
	 * the original value for the tag itself and the nesting context.
	 * 
	 * @return int to describe the next step for the JSP processor
	 * @throws JspException
	 *             for the bad things JSP's do
	 */

	/** Atributos para permitir la creación de nuevos elementos */
	String zona;
	public String newOption;
	public String newOptionvalue;
	private String newOptionlabel;
	
	private String required="false";

	public int doEndTag() throws JspException {
		int i = super.doEndTag();		
		
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);
		JspWriter out = pageContext.getOut();
		try {
			render(out, apps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
		
	}

	protected void render(JspWriter out, ResourceBundle apps) throws Exception {
		if (zona != null && !zona.equals("")) {
			String action = getDestinationAction(zona);
			
			if(action!=null){
				String varName = "selec"
					+ Integer.toString((new Random()).nextInt(10000));
				
				String addText=apps.getString("selection.add");
				
				/**
				 * Render the javascript that enables user interaction and set the
				 * language.
				 */
				out.println("<SCRIPT LANGUAGE=\"JavaScript\" >");
				out.println("var " + varName + " = new FieldPopup();");
				out.println("</SCRIPT>");
				
				out.println("<input type=\"image\" onClick=\"" + varName + ".goToActionPopUp('" + action + ".do', this.form['" + getProperty() + "'], null,'" + varName + "');" + " return false;\" TITLE=\"" + addText + "\" NAME=\"" + varName + "\" ID=\"" + varName
						+ "\" src=\"../images/CATEGORY/action-type/addElement.gif\" style=\"position:relative;top:0;left:0;border:0;\">");
			}
		}
	}

	private String getDestinationAction(String zone) {

		String destinationAction = null;

		Menu menu = null;

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		SiteOrg siteOrg = (SiteOrg) request.getSession()
				.getAttribute("siteorg");

		ActionMapping am = (ActionMapping) request
				.getAttribute("org.apache.struts.action.mapping.instance");

		// TODO: en todos los sitios en los que se recuperen las zonas hay que
		// obtener la command bien
		String commandName = NavigationFunctions.getCommandName(am);
		String actionName = NavigationFunctions.getActionName(am);
		String viewId = "eim";

		String sectorName = null;
		boolean considerOtherSectors = true;
		menu = siteOrg.factoryZone(zone, commandName, actionName, sectorName,
				considerOtherSectors, pageContext.getRequest(), viewId);

		String objType = siteOrg.actionGetParam("objType", commandName,
				actionName, sectorName, considerOtherSectors);
		if (objType == null || objType.equalsIgnoreCase(""))
			objType = siteOrg.getParameter("genericObjectName");
		if (menu != null && !menu.isEmpty()) {
			MenuItem[] items = menu.getItems();

			for (int j = 0; j < items.length; j++) {
				MenuItem item = items[j];
				destinationAction = NavigationFunctions.getPopUpActionName(item
						.getLink());
			}
		}

		return destinationAction;

	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Override
	public int doStartTag() throws JspException {
		int result=super.doStartTag();
		
		Object bean = TagUtils.getInstance().lookup(pageContext, name, null);
        if (bean == null) {
            JspException e =
                new JspException(messages.getMessage("getter.bean", name));
                
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }
        
        Object property=null;
        String label ="";
        newOption = "";
		try {
        	int separation=getProperty().lastIndexOf(".");
        	String propertyName=getProperty().substring(0, separation);
        	String codeName=getProperty().substring(separation+1);
        	property=BeanUtilsBean.getInstance().getPropertyUtils().getNestedProperty(bean,propertyName);
        	
        	newOptionvalue=BeanUtils.getProperty(property, codeName);
        	newOptionlabel=property.toString();
        	if(newOption!=null){
        		newOption = "<option value=\""+newOption+"\" SELECTED>"+label+"</option>";
        	}        	
		} catch (Exception e) {
			JspException e1 = new JspException(e);
            throw e1;
		}
		return result;
	}

	public String getNewOptionlabel() {
		return newOptionlabel;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
		
}
