package utils.jsp;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import utils.customguardation.controller.CustomRequestProcessor;

/**
 * Static utils used in JSPs
 * 
 * @author Automatika - JustInMind SL
 */
public class JspUtils {

	//public static String PROJECT_PATH = "/irb";
	public static String PROJECT_NAME = "irb";
	public static String SERVER_PATH = "";
	
	
	public static String getProjectPath(HttpServletRequest request){
		return request.getContextPath();
	}
	
	/**
	* Returns the current action path
	* @param request current request
	* @return returns the current action path
	**/
	public static String getCurrentAction(HttpServletRequest request){
		return ((ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance")).getPath()+".do";
	}

	/**
	* Returns the current action name
	* @param request current request
	* @return returns the current action name
	**/	
	public static String getCurrentActionName(HttpServletRequest request){
		String result;
		result = ((ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance")).getPath();
		if (result.indexOf("/")==0) result=result.substring(1);
		return result;
	}	

	/**
	* Returns the current formbean name
	* @param request current request
	* @return returns the current formbean name
	**/		
	public static String getCurrentFormBeanName(HttpServletRequest request){
		return ((ActionMapping)request.getAttribute("org.apache.struts.action.mapping.instance")).getName();	
	}
	
	/**
	* Returns an string with metacharacters quoted in HTML.
	* @param string string to modify
	* @return string in html form
	**/
    public static String quote(String string) {
    	return quote(string, true);
    }
	
	/**
	* Returns an string with metacharacters quoted in HTML.
	* @param string string to modify
	* @return string in html form
	**/
    public static String quote(String string, boolean transformBR) {
	//        ecano: all strings from the db should pass through this
	//              function, so we seize the opportunity to remove nulls as well.         
        if(string == null) return ""; 

        StringBuffer sb = new StringBuffer(string.length());
        // true if last char was blank
        boolean lastWasBlankChar = false;
        int len = string.length();
        char c;
        for (int i = 0; i < len; i++)
            {
            c = string.charAt(i);
            if (c == ' ') {
                // blank gets extra work,
                // this solves the problem you get if you replace all
                // blanks with &nbsp;, if you do that you loss 
                // word breaking
                if (lastWasBlankChar) {
                    lastWasBlankChar = false;
                    sb.append("&nbsp;");
                    }
                else {
                    lastWasBlankChar = true;
                    sb.append(' ');
                    }
                }
            else {
                lastWasBlankChar = false;
                //
                // HTML Special Chars
                if (c == '"')                          
                    sb.append("&quot;");                
              else if (c == '\'')
                  sb.append("&#39;");
                else if (c == '&')
                    sb.append("&amp;");
                else if (c == '<')
                    sb.append("&lt;");
                else if (c == '>')
                    sb.append("&gt;");
                else if (c == '\n' && transformBR)
                    // Handle Newline
                    sb.append("<br>");
                else {
                    int ci = 0xffff & c;
                    if (ci < 160 )
                        // nothing special only 7 Bit
                        sb.append(c);
                    else {
                        // Not 7 Bit use the unicode system
                        sb.append("&#");
                        sb.append(new Integer(ci).toString());
                        sb.append(';');
                        }
                    }
                }
            }
        return sb.toString();
    }
  
	/**
	* Returns an string with metacharacters quoted in Java Script.
	* @param string string to modify
	* @return string in Java Script form
	**/
	public static String quoteJS(String string) {
	//    ecano: all strings from the db should pass through this
	//        function, so we seize the opportunity to remove nulls as well.      
      if(string == null) return ""; 
      
      StringBuffer sb = new StringBuffer(string.length());
      // true if last char was blank
      boolean lastWasBlankChar = false;
      int len = string.length();
      char c;

      for (int i = 0; i < len; i++)
          {
          c = string.charAt(i);
          if (c == ' ') {
              // blank gets extra work,
              // this solves the problem you get if you replace all
              // blanks with &nbsp;, if you do that you loss 
              // word breaking
              if (lastWasBlankChar) {
                  lastWasBlankChar = false;
                  sb.append("&nbsp;");
                  }
              else {
                  lastWasBlankChar = true;
                  sb.append(' ');
                  }
              }
          else {
              lastWasBlankChar = false;
              //
              // HTML Special Chars
              if (c == '"')                          
                  sb.append("&quot;");                
              else if (c == '\'')
                  sb.append("\\\'");
              else if (c == '&')
                  sb.append("&amp;");
              else if (c == '<')
                  sb.append("&lt;");
              else if (c == '>')
                  sb.append("&gt;");
              else if (c == '\n')
                  // Handle Newline
                  sb.append("<br>");
              else {
                  int ci = 0xffff & c;
                  if (ci < 160 )
                      // nothing special only 7 Bit
                      sb.append(c);
                  else {
                      // Not 7 Bit use the unicode system
                      sb.append("&#");
                      sb.append(new Integer(ci).toString());
                      sb.append(';');
                      }
                  }
              }
          }
      return sb.toString();
  }
	
    public static String getCurrentHelp(HttpServletRequest request){
    	String currentAction=JspUtils.getCurrentActionName(request);
    	ResourceBundle apps = ResourceBundle.getBundle("helpFiles");
    	
    	try{
    		return apps.getString(currentAction);
    	} catch (MissingResourceException e){
    		return null;
    	}
    	
    }
    
    public static boolean isObtentionPhaseActive(HttpServletRequest request){
    	return (Boolean)request.getAttribute(CustomRequestProcessor.ATT_OBTENTION_PHASE_ACTIVE);
    }
}