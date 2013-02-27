package plugins;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.justinmind.helper.composition.common.types.TypesManager;
import com.justinmind.helper.composition.list.ListComposition;
import com.justinmind.helper.composition.list.ListCompositionFactory;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;

public class ListCompositionPlugIn implements PlugIn {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(ListCompositionPlugIn.class);
    
    private String objectfieldsPath="/WEB-INF/etc/objectfields"; //valor por defecto
    private String reportsPath="/WEB-INF/etc/reportfields";     //valor por defecto
    private String typesPath="/WEB-INF/etc/types";
    private String operatorsPath="/WEB-INF/etc/operators";
    
    public void destroy() {
        

    }

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        
        try
        {
            //Inicialización del sistema de listas para los viewlists.
            ListCompositionFactory lcf = new ListCompositionFactory();
            ListComposition lc = lcf.createListComposition("lc1", servlet.getServletContext().getRealPath(objectfieldsPath));
            
            servlet.getServletContext().setAttribute("lcf",lcf);
            
            
            //Inicialización del sistema de reports para el filtro.
            TypesManager typesManager = new TypesManager(servlet.getServletContext().getRealPath(typesPath),
                    servlet.getServletContext().getRealPath(operatorsPath));
            
            ReportCompositionFactory rcf = new ReportCompositionFactory();
            
            ReportComposition rc =
                rcf.createReportComposition("irb_rrhh",
                    servlet.getServletContext().getRealPath(reportsPath),
                    typesManager);
             
            
            servlet.getServletContext().setAttribute("rcf",rcf);
            
        }
        catch(Exception e)
        {
            log.error(e);
        }
            
        

    }

    public void setObjectfieldsPath(String objectfieldsPath) {
        this.objectfieldsPath = objectfieldsPath;
    }

    public void setReportsPath(String reportsPath) {
        this.reportsPath = reportsPath;
    }

    public void setOperatorsPath(String operatorsPath) {
        this.operatorsPath = operatorsPath;
    }

    public void setTypesPath(String typesPath) {
        this.typesPath = typesPath;
    }

}
