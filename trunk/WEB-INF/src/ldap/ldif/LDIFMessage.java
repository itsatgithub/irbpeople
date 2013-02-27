package ldap.ldif;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LDIFMessage {

	// per a la capçalera del missatge
	public static final String KEY_CN = "cn";
	public static final String KEY_OU = "OU";
	public static final String KEY_OU2 = "ou2";
	
	// nom
	public static final String KEY_NAME = "givenName";
	// cognom
	public static final String KEY_SN = "sn";
	// nom complet 
	public static final String KEY_FULLNAME = "fullName";
	// telefon
	public static final String KEY_TEL = "telephoneNumber";
	// email
	public static final String KEY_MAIL = "mail";
	// mobil
	public static final String KEY_MOBILE = "mobile????";
	// foto
	public static final String KEY_PHOTO = "photo?????";
	// position
	public static final String KEY_POSITION = "title";
	// location
	public static final String KEY_LOCATION = "l";
	// unit
	public static final String KEY_UNIT = "irbunit";
	// research_group
	public static final String KEY_RESEARCH = "research????";
	
	public static final String KEY_UID = "uid";
	
	public static final String KEY_UIDNUMBER = "uidNumber";
	
	public static final String KEY_PASSWORD = "userPassword";
	
	public static final String KEY_HOME = "homeDirectory";
	
	public static final String KEY_ou = "ou";
	
	public static final String KEY_IRBUSERCODE = "irbusercode";
	
	
	public final static String TYPE_ADD = "add";
	public final static String TYPE_MODIFY = "modify";
	public final static String TYPE_DELETE = "delete";
	
	private String type;
	private Map<String, String> values;
	
	public LDIFMessage(String type) {
		this.type = type;
		this.values = new HashMap<String, String>();
	}
	
	public void addValue(String key, String value) {
		this.values.put(key, value);
	}
	
	public String getValue(String key) {
		return this.values.get(key);
	}
	
	public String output() {
		
		String output = "";
		
		if(values.size() > 0) {
			
			output = "dn: "+KEY_CN+"="+getValue(KEY_CN)+","+KEY_OU+"="+getValue(KEY_OU);
			if(values.containsKey(KEY_OU2)) {
				output += ","+KEY_OU+"="+getValue(KEY_OU2);
			}
			output += ",O=irbbarcelona\n";
			
			if(type.equals(TYPE_ADD)) {
				
				output += "changetype: add\n";
				
				output += "objectClass: Top\n" +
						"objectClass: inetOrgPerson\n" +
						"objectClass: organizationalPerson\n" +
						"objectClass: Person\n" +
						"objectClass: posixAccount\n" +
						"objectClass: shadowAccount\n" +
						"objectClass: uamPosixUser\n" +
						"objectClass: ndsLoginProperties\n" +
						"objectClass: homeInfo\n";
				
				output += KEY_CN+": "+getValue(KEY_CN)+"\n";
				output += KEY_NAME+": "+getValue(KEY_NAME)+"\n";
				output += KEY_SN+": "+getValue(KEY_SN)+"\n";
				output += KEY_FULLNAME+": "+getValue(KEY_FULLNAME)+"\n";
				output += KEY_ou+": "+getValue(KEY_ou)+"\n";
				output += KEY_UNIT+": "+getValue(KEY_UNIT)+"\n";
				output += KEY_TEL+": "+getValue(KEY_TEL)+"\n";
				output += KEY_IRBUSERCODE+": "+getValue(KEY_IRBUSERCODE)+"\n";
				output += KEY_MAIL+": "+getValue(KEY_MAIL)+"\n";
				output += KEY_POSITION+": "+getValue(KEY_POSITION)+"\n";
				output += KEY_LOCATION+": "+getValue(KEY_LOCATION)+"\n";
				output += "irbaxapta: true\n";
				output += KEY_UID+": "+getValue(KEY_UID)+"\n";
				output += KEY_UIDNUMBER+": "+getValue(KEY_UIDNUMBER)+"\n";
				output += "gidNumber: 99999\n"+
						"loginShell: /bin/bash\n";
				output += KEY_PASSWORD+": "+getValue(KEY_PASSWORD)+"\n";
				output += KEY_HOME+": "+getValue(KEY_HOME)+"\n";
				
				/*** camps que no se com es diuen al LDAP **/
				
//				output += KEY_MOBILE+": "+getValue(KEY_MOBILE)+"\n";
//				output += KEY_PHOTO+":: "+getValue(KEY_PHOTO)+"\n";
//				output += KEY_RESEARCH+": "+getValue(KEY_RESEARCH)+"\n";
				
				/*** **/
				
			} else if(type.equals(TYPE_MODIFY)) {
				
				output += "changetype: modify\n";
				output += "replace: "+KEY_NAME+"\n";
				output += KEY_NAME+": "+getValue(KEY_NAME)+"\n";
				output += "-\n";
				output += "replace: "+KEY_SN+"\n";
				output += KEY_SN+": "+getValue(KEY_SN)+"\n";
				output += "-\n";
				output += "replace: "+KEY_FULLNAME+"\n";
				output += KEY_FULLNAME+": "+getValue(KEY_FULLNAME)+"\n";
				output += "-\n";
				output += "replace: "+KEY_ou+"\n";
				output += KEY_ou+": "+getValue(KEY_ou)+"\n";
				output += "-\n";
				output += "replace: "+KEY_UNIT+"\n";
                output += KEY_UNIT+": "+getValue(KEY_UNIT)+"\n";
                output += "-\n";
				output += "replace: "+KEY_TEL+"\n";
				output += KEY_TEL+": "+getValue(KEY_TEL)+"\n";
				output += "-\n";
				output += "replace: "+KEY_MAIL+"\n";
				output += KEY_MAIL+": "+getValue(KEY_MAIL)+"\n";
//				output += "-\n";
//				output += "replace: "+KEY_MOBILE+"\n";
//				output += KEY_MOBILE+": "+getValue(KEY_MOBILE)+"\n";
//				output += "-\n";
//				output += "replace: "+KEY_PHOTO+"\n";
//				output += KEY_PHOTO+":: "+getValue(KEY_PHOTO)+"\n";
				output += "-\n";
				output += "replace: "+KEY_POSITION+"\n";
				output += KEY_POSITION+": "+getValue(KEY_POSITION)+"\n";
				output += "-\n";
				output += "replace: "+KEY_LOCATION+"\n";
				output += KEY_LOCATION+": "+getValue(KEY_LOCATION)+"\n";
//				output += "-\n";
//				output += "replace: "+KEY_RESEARCH+"\n";
//				output += KEY_RESEARCH+": "+getValue(KEY_RESEARCH)+"\n";
				
				
				
			} else if(type.equals(TYPE_DELETE)) {
				
				output += "changetype: delete\n";
			}
		}
		
		return output;
	}
}