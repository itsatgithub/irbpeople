package presentation.formbeans.other;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class Action_reportfile_Form extends ActionForm {
    private FormFile reportFile;

    public FormFile getReportFile() {
        return reportFile;
    }

    public void setReportFile(FormFile reportFile) {
        this.reportFile = reportFile;
    }
    
    
    
}
