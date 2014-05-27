package utils.reportFilter;

import java.util.Arrays;
import java.util.Vector;

import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Usuario;

public class ReportFilter {

	public static void filterFieldsByProfile(Usuario user, Vector<String> names, Vector<String> labels) {
		String[] fieldsToSkipList = { /*"professional_end_date"*/};
		String[] labelsToSkipList = { "Compensation", "Benefit" };
		Vector<String> fieldsToSkip = new Vector<String>(Arrays.asList(fieldsToSkipList));
		Vector<String> labelsToSkip = new Vector<String>(Arrays.asList(labelsToSkipList));

		if (UserUtils.checkRole(user, UseCase.IRBPEOPLE_GRANT_ROLE_NAME) || UserUtils.checkRole(user, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)) {
			for (int i = 0; i < names.size(); i++) {
				if (fieldsToSkip.contains(names.get(i))) {
					names.remove(i);
					labels.remove(i);
					continue;
				}
				String label = labels.get(i);
				for (String labelToSkip : labelsToSkip) {
					if (label.startsWith(labelToSkip)) {
						names.remove(i);
						labels.remove(i);
						break;
					}
				}
			}

		}
	}
}
