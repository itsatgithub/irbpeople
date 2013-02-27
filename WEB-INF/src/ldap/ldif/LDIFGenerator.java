package ldap.ldif;

import java.util.List;

import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;
import bussineslogic.controlers.UseCase;
import bussineslogic.excepciones.InternalException;
import bussineslogic.objects.Organization_unit;
import bussineslogic.objects.Personal;
import bussineslogic.objects.PersonalPhoto;
import bussineslogic.objects.Professional;
import bussineslogic.objects.Unit;
import bussineslogic.objects.Usuario;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class LDIFGenerator {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(LDIFGenerator.class);
    
	/** funcio que genera un fitxer LDIF per a la creació d'un usuari al LDAP
	 * 
	 * @param professional
	 * @return LDIF
	 * @throws InternalException */
	
	public static String generateCreateLDIF(Professional professional) throws InternalException {
		
	    try
	    {
	        if(professional!=null) {

	            Personal personal = professional.getProfessional_personal();

	            // obrtenim el valors necessaris per al missatge

	            Unit u = professional.getProfessional_unit();
	            Organization_unit ou = u.getOrganization_unit();

	            String[] ous = getOUs(ou, u);

	            String name = personal.getName();
	            String sn = personal.getSurname1();
	            String cn = getCN(name, sn, personal.getSurname2());
	            String tel = professional.getPhone();
	            String email = professional.getEmail();

	            String mobile = professional.getMobile_long();
	            String location = professional.getLocation() != null ? professional.getLocation().getLocationcode() : "";
	            if(location==null || location.equals("")) location = "---";
	            String position = professional.getPosition() != null ? professional.getPosition().getDescription() : "";
	            String unit = u.getDescription();
	            String research_group = professional.getResearch_group() != null ? professional.getResearch_group().getDescription() : "";

	            String uid = cn;
	            String uidNumber = personal.getPersonalcode();
	            String home = "/home/"+cn;
	            String oudesc = ou.getDescription();
	            String irbusercode = personal.getPersonalcode();

	            // per a enviar la foto utilitzem la seva codificaio en base64
	            //			String foto = "";
	            //			if(personal.getPhoto() != null)
	            //				foto = Base64.encode(personal.getPhoto().getPhoto());

	            //			Usuario user = UseCase.getUsuario(personal.getUsercode());

	            //			// obtemim el password
	            //			String password = "";
	            //			if(user!=null)
	            //			// en el password indiquem l'algoritme d'encriptacio
	            //			// en el nostre cas es MD5 (suposem que funcionaria)
	            //				password = "{MD5}"+user.getPassword();
	            //			
	            //			// si el personal no te usuari associat li posem el password per defecte
	            //			else 
	            String password = "{DES}Q8WNlAKTBHANQ";

	            // creem el misstge LDIF amb el tipus
	            LDIFMessage msg = new LDIFMessage(LDIFMessage.TYPE_ADD);

	            // anem afegin els valors al missatge
	            msg.addValue(LDIFMessage.KEY_CN, cn);
	            msg.addValue(LDIFMessage.KEY_OU, ous[0]);
	            if(ous.length == 2)
	                msg.addValue(LDIFMessage.KEY_OU2, ous[1]);
	            msg.addValue(LDIFMessage.KEY_NAME, name);
	            msg.addValue(LDIFMessage.KEY_FULLNAME, name+" "+sn);
	            msg.addValue(LDIFMessage.KEY_SN, sn);
	            msg.addValue(LDIFMessage.KEY_TEL, tel);
	            msg.addValue(LDIFMessage.KEY_MAIL, email);
	            msg.addValue(LDIFMessage.KEY_MOBILE, mobile);
	            msg.addValue(LDIFMessage.KEY_LOCATION, location);
	            msg.addValue(LDIFMessage.KEY_POSITION, position);
	            msg.addValue(LDIFMessage.KEY_UNIT, unit);
	            msg.addValue(LDIFMessage.KEY_RESEARCH, research_group);
	            //			msg.addValue(LDIFMessage.KEY_PHOTO, foto);
	            msg.addValue(LDIFMessage.KEY_UID, uid);
	            msg.addValue(LDIFMessage.KEY_UIDNUMBER, uidNumber);
	            msg.addValue(LDIFMessage.KEY_PASSWORD, password);
	            msg.addValue(LDIFMessage.KEY_HOME, home);
	            msg.addValue(LDIFMessage.KEY_ou, oudesc);
	            msg.addValue(LDIFMessage.KEY_IRBUSERCODE,irbusercode);

	            // generem el missatge
	            return msg.output();

	        }
	    }
	    catch(Exception e)
	    {
	        log.error("Error en generateCreateLDIF. Probablemente alguna propiedad es null", e);
	        
	    }
	    return null;
	}
	
	/** funcio que genera un fitxer LDIF per a la modificació d'un usuari al LDAP
	 * 
	 * @param old
	 * @param personal
	 * @param personalPhoto 
	 * @return LDIF
	 * @throws InternalException
	 */
	public static String generateModifyLDIF(Professional old,
			Professional professional, Personal personal, PersonalPhoto personalPhoto) throws InternalException {
		
	    try
	    {
	        if(professional == null)
	            return null;

	        Personal personal_old = professional.getProfessional_personal();

	        if(personalPhoto!=null && personal_old.getPhoto()!=null && personalPhoto.equals(personal_old.getPhoto()))
	            return null;

	        if(personal!=null && personal.getName().equals(personal_old.getName())
	                && personal.getSurname1().equals(personal_old.getSurname1())
	                && personal.getSurname2().equals(personal_old.getSurname2())
	                && personal.getPhone().equals(personal_old.getPhone())) {

	            return null;
	        }

	        // comprovem si ha canviat algun camp que hem d'actulitzar sino no fem
	        // res
	        if (old!=null && professional.getEmail().equals(old.getEmail())
	                && professional.getMobile_long().equals(old.getMobile_long())
	                && professional.getLocation().equals(old.getLocation())
	                && professional.getPosition().equals(old.getPosition())
	                && professional.getProfessional_unit().equals(old.getProfessional_unit())
	                && professional.getResearch_group().equals(old.getResearch_group())
	                && professional.getPhone().equals(old.getPhone())
	        )
	        {

	            return null;
	        }

	        if(personal==null) personal = personal_old;

	        Unit u = professional.getProfessional_unit();
	        Organization_unit ou = u.getOrganization_unit();

	        String[] ous = getOUs(ou, u);

	        String name = personal.getName();
	        String sn = personal.getSurname1();
	        String cn = getCN(name, sn, personal.getSurname2());
	        String tel = professional.getPhone();
	        String email = professional.getEmail();

	        String mobile = professional.getMobile_long();
	        String location = professional.getLocation().getLocationcode();
	        String position = professional.getPosition().getDescription();
	        String unit = u.getDescription();
	        String research_group="";

	        try
	        {
	            research_group = professional.getResearch_group().getDescription();
	        }
	        catch(Exception e)
	        {

	        }

	        String foto = "";
	        if (personalPhoto != null) 
	            foto = Base64.encode(personalPhoto.getPhoto());

	        else if (personal.getPhoto() != null) {
	            PersonalPhoto ph = personal.getPhoto();
	            foto = Base64.encode(ph.getPhoto());
	        }

	        LDIFMessage msg = new LDIFMessage(LDIFMessage.TYPE_MODIFY);

	        msg.addValue(LDIFMessage.KEY_CN, cn);
	        msg.addValue(LDIFMessage.KEY_OU, ous[0]);
	        if (ous.length == 2)
	            msg.addValue(LDIFMessage.KEY_OU2, ous[1]);
	        msg.addValue(LDIFMessage.KEY_ou, ou.getDescription());
	        msg.addValue(LDIFMessage.KEY_NAME, name);
	        msg.addValue(LDIFMessage.KEY_FULLNAME, name + " " + sn);
	        msg.addValue(LDIFMessage.KEY_SN, sn);
	        msg.addValue(LDIFMessage.KEY_TEL, tel);
	        msg.addValue(LDIFMessage.KEY_MAIL, email);
	        msg.addValue(LDIFMessage.KEY_MOBILE, mobile);
	        msg.addValue(LDIFMessage.KEY_LOCATION, location);
	        msg.addValue(LDIFMessage.KEY_POSITION, position);
	        msg.addValue(LDIFMessage.KEY_UNIT, unit);
	        msg.addValue(LDIFMessage.KEY_RESEARCH, research_group);
	        //		msg.addValue(LDIFMessage.KEY_PHOTO, foto);

	        return msg.output();
	    }
	    catch(Exception e)
	    {
	        log.error("Error en generateModifyLDIF. Probablemente alguna propiedad es null.", e);
	        return null;
	    }
	}


	/** funcio que genera un fitxer LDIF per a l'esborrat d'un usuari al LDAP
	 *  
	 * @param professional
	 * @return LDIF*/
	
	public static String generateDeleteLDIF(Professional professional) {
		try
		{
		    if(professional!=null) {

		        Personal personal = professional.getProfessional_personal();

		        Unit u = professional.getProfessional_unit();
		        Organization_unit ou = u.getOrganization_unit();

		        String[] ous = getOUs(ou, u);

		        String name = personal.getName();
		        String sn = personal.getSurname1();
		        String cn = getCN(name, sn, personal.getSurname2());

		        LDIFMessage msg = new LDIFMessage(LDIFMessage.TYPE_DELETE);

		        msg.addValue(LDIFMessage.KEY_CN, cn);
		        msg.addValue(LDIFMessage.KEY_OU, ous[0]);
		        if(ous.length == 2)
		            msg.addValue(LDIFMessage.KEY_OU2, ous[1]);

		        return msg.output();
		    }
		}
		catch(Exception e)
		{
		    log.error("Error en generateDeleteLDIF. Probablemente alguna propiedad es null.", e);
		}
		return null;
	}
	
	// retorna la OU o les OU's 
	private static String[] getOUs(Organization_unit ou, Unit u) {
		
	    try
	    {
	        String oudesc = ou.getDescription();
	        String udesc = u.getDescription();

	        if(oudesc.equals("Research Programmes")) {
	            String[] res = new String[2];
	            res[1] = "respro";

	            if(udesc.equals("Cell & Developmental Biology")) {
	                res[0] = "celdevbio";
	            } else if(udesc.equals("Structural & Computational Biology")) {
	                res[0] = "strcombio";
	            } else if(udesc.equals("Molecular Medicine")) {
	                res[0] = "molmed";
	            } else if(udesc.equals("Chemistry & Molecular Pharmacology")) {
	                res[0] = "chemolpha";
	            } else if(udesc.equals("Oncology")) {
	                res[0] = "oncolo";
	            }
	            return res;

	        } else if(oudesc.equals("Core Facilities")) {
	            String[] res = new String[1];
	            res[0] = "corfac";
	            return res;

	        } else if(oudesc.equals("Administration")) {
	            String[] res = new String[1];
	            res[0] = "admini";
	            return res;

	            // no s'hauria de donar, segons el php....
	        } else {
	            String[] res = new String[1];
	            res[0] = oudesc;
	            return res;
	        }
	    }
	    catch(Exception e)
	    {
	        log.error("Error en getOU. Probablemente no se ha encontrado Unit u Organization_unit", e);
	        return new String[]{};
	    }
	}
	
	/**
	 * Retorna la CN (segons els casos especials indicats al fitxer php)
	 * 
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @return
	 */
	private static String getCN(String name, String surname1, String surname2) {
		
		String cn = name.substring(0, 1).toLowerCase() + surname1.toLowerCase();
		
		if(cn.equals("mlopez") || cn.equals("mmoreno"))
			cn += surname2.toLowerCase();
		
		return cn;
	}
}
