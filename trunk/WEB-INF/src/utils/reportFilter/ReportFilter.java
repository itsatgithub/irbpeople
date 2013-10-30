package utils.reportFilter;

import java.util.Vector;

import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Usuario;

/**
 * This class is a nested-form-bean which contains the information of
 * configurator of a list (filter, pagination and orther).
 * 
 * @author Automatika - JustInMind SL
 */
public class ReportFilter {

	public static void filterFieldsByProfile(Usuario user, Vector<String> names, Vector<String> labels) {
		String[] fieldsToSkip = { "professional_end_date" };
		if (true || UserUtils.checkRole(user, UseCase.IRBPEOPLE_GRANT_ROLE_NAME) || UserUtils.checkRole(user, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)) {
			for (int i = 0; i < fieldsToSkip.length; i++) {
				int index = names.indexOf(fieldsToSkip[i]);
				if (index != -1) {
					names.remove(index);
					labels.remove(index);
				}
			}
		}
	}
}
