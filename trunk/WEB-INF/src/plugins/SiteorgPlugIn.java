package plugins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.Session;

import utils.Pair;
import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.UsuarioExisteException;
import bussineslogic.excepciones.UsuarioNoActivoException;
import bussineslogic.objects.Usuario;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;
import com.justinmind.usermanagement.exception.EntityNotFoundException;
import com.justinmind.usermanagement.exception.IdentifierException;
import com.justinmind.usermanagement.exception.PermissionPriorityException;
import com.justinmind.util.siteorg.SiteOrgManager;

public class SiteorgPlugIn implements PlugIn {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(SiteorgPlugIn.class);
    
    private String baseConfigDir;
    
    private String logsDir;
    
	public static final String ADMIN_ID="admin";
	public static final String ADMIN_PASS="admin";
    
    public void destroy() {
        
    }

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        
        SiteOrgManager siteOrgManager = null;
        try
        {
            // Limpiamos el SiteOrgManager, vaciado de todas las caches.
            SiteOrgManager.clean();    
            
            // Obtenemos el siteOrgManager.
            siteOrgManager = SiteOrgManager.singleton();   
            
            // . Forzamos producción si lo tenemos que hacer.
            //   Descomentar para forzar uso en producción.
            //   siteOrgManager.forceToProduction();
            
            // . Forzamos la adición del rol por defecto.
            //   Esto implicará que hay un rol público, que será el rol por defecto
            //   Además se puede cargar el rol por defecto indicando 'null' en el 
            //   nombre del rol. Todo el resto de roles también tendrán los servicios
            //   del rol público.
            siteOrgManager.defaultRoleIsAdded();            
            
            // 1. Indicamos el directorio donde esta el fichero con los parametros de configuracion (configDirectoryForSiteOrg)
            //    y el directorio donde localizar 'site.xml' (appDirectoryForSiteOrg).   ´
            //    'site.xml' es el fichero de descripcion de toda la estructura del site.
            siteOrgManager.config(servlet.getServletContext().getRealPath(baseConfigDir),
                    servlet.getServletContext().getRealPath(baseConfigDir),
                    logsDir);

            // 2. Iniciamos SiteOrg.
            siteOrgManager.init();
            
            String logProperties = servlet.getServletContext().getRealPath("/") + "WEB-INF/etc/log4j.properties";
            System.out.println("LoggingServlet Initialized using file :" + logProperties);
//             Initialize Properties for All Servlets
            PropertyConfigurator.configure(logProperties);
            
        }
        catch(Exception e)
        {
            log.error(e);
        }
        
        // Carga roles en la base de datos.
        if (siteOrgManager!=null && siteOrgManager.isRolesLoadedOK()){
            
            HibernateUtil.getSession();
            
            //Should dump the loaded roles into the database here
            UserManagement um = null;
            try {
                um = UserManagement.singleton();
            } catch (PermissionPriorityException e2) {
                e2.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            String[] names = siteOrgManager.getRolesManager().getRoleNames();
            String[] descs = siteOrgManager.getRolesManager().getRoleDescs();
            
            for(int i=0; i<names.length; i++)
                log.info("Rol en fichero. Nombre: " + names[i] + ", descripción: " + descs[i]);
            
            log.info("Deleting obsolete roles...");
            
            log.info("Try to obtain roles list...");
            List currentroles = um.getRoles(); // um.getRoles(hms);
            log.info("Roles list obtained.");
            List todelete = new ArrayList();
            Iterator it = currentroles.iterator();
            while(it.hasNext()){
                Role r = (Role)it.next();
                String rolename = r.getId();
                boolean trobat = false;
                int i = 0;
                while(i<names.length && !trobat){
                    trobat = false;
                    if(names[i].equalsIgnoreCase(rolename)){
                        trobat = true;
                        // todelete.add(r);
                    }
                    i++;
                }
                if (!trobat) {
                    todelete.add(r);
                }
            }
            
            it = todelete.iterator();
            while(it.hasNext()){
                Role r = (Role)it.next();
                r.removeAllUsers();
                log.info("Try to delete '" + r.getId() + "' role...");
                HibernateUtil.getSession().delete(r);
                log.info("Role '" + r.getId() + "' deleted");
            }
            
            log.info("Obsolete roles deleted!");
            
            log.info("Dumping roles into the database...");
            
            for (int i=0; i<names.length; i++){

                HibernateUtil.beginTransaction();
                try{
                    
                    // Role r = um.getRole(names[i], hms);
                    Role r = um.getRole(names[i]);
                    
                    log.info("Role " + i + " not dumped, it already exists: " + names[i]);    
                    
                }catch(EntityNotFoundException e){
                    // um.newRole(names[i], descs[i], hms);
                    try {
                        um.newRole(names[i], descs[i]);
                    } catch (IdentifierException e1) {
                        e1.printStackTrace();
                    }
                    log.warn("Role " + i + " dumped: " + names[i]);
                }finally{
                    HibernateUtil.commitTransaction();
                }
            }
            HibernateUtil.closeSession();
        }
        //insertData();
    }

    public void setBaseConfigDir(String baseConfigDir) {
        this.baseConfigDir = baseConfigDir;
    }

    public void setLogsDir(String logsDir) {
        this.logsDir = logsDir;
    }
    
    /**
     * In case that no active users exist in the database, this method created one per role, with the name and password equal to the role name, and the role assigned to it.
     */
    public void insertData(){
    	//This functions is not used currently. Only used after generation
    	
		try {
			HibernateUtil.getSession();
			HibernateUtil.beginTransaction();
			
			Pair<Integer, List<Usuario>> users=UseCaseFacade.ObtenerUsuariosActivos("", new ListConfigurator());
			if (users.first.intValue()==0) {
				for(String roleName:SiteOrgManager.singleton().getRolesManager().getBasicRoleNames()){
					
					if(SiteOrgManager.singleton().getRolesManager().getRole(roleName).isDefault()) continue;
					
					Usuario user = new Usuario();
					user.setPassword(roleName.getBytes());
					user.setUsername(roleName);
					user = UseCaseFacade.CrearUsuario(user);
					HibernateUtil.commitTransaction();
					HibernateUtil.closeSession();

					HibernateUtil.getSession();
					HibernateUtil.beginTransaction();
					user = UseCaseFacade.AltaUsuario(user.getCode(), user.getCode(), user.getActivationCode());

					UseCaseFacade.AddRol(user.getCode(), user, roleName);
					HibernateUtil.commitTransaction();
				}
			}
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			log.error(e);
		}
		finally{
			HibernateUtil.closeSession();
		}
    }

}
