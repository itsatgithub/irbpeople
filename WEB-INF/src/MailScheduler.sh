#!/bin/sh
#

BASE_PROJECT_DIR=/usr/share/tomcat5/webapps/irbpeople
FULL_PATH="%BASE_PROJECT_DIR%/WEB-INF/etc";"%BASE_PROJECT_DIR%/WEB-INF/classes";%JAVA_HOME%;.
BASE_JAVA_LIBS="/usr/lib/jvm/java/jre/lib/ext";
PROJECT_LIBS="%BASE_PROJECT_DIR%/WEB-INF/lib";
TOMCAT_LIBS="/usr/share/tomcat5/common/lib";

echo "Enviando mails de aviso... (por favor, no cierre esta ventana)"
exec java -Djava.ext.dirs=%BASE_JAVA_LIBS%;%PROJECT_LIBS%;%TOMCAT_LIBS% -cp %FULL_PATH% com.justinmind.MailSystem.StandaloneMailSender MailContractsAboutToExpire
