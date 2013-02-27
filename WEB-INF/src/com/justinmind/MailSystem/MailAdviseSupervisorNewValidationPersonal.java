package com.justinmind.MailSystem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


import bussineslogic.objects.Personal;
import bussineslogic.objects.Usuario;

import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Role;


public class MailAdviseSupervisorNewValidationPersonal extends AbstractMailToSupervisors {

	private static org.apache.log4j.Category log= org.apache.log4j.Logger.getLogger(MailAbstract.class);

	@Override
	public void createAuditMessage(Personal user, String subject, String email) {
		super.createAuditMessage(null, subject, email);
	}
}
