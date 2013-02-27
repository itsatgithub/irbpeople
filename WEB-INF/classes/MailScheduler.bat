@echo off

set BASE_PROJECT_DIR=d:\workspace\irbpeople
set FULL_PATH="%BASE_PROJECT_DIR%\WEB-INF\etc";"%BASE_PROJECT_DIR%\WEB-INF\classes";%JAVA_HOME%;.
set BASE_JAVA_LIBS="C:\jdk1.5.0_12\jre\lib\ext";
set PROJECT_LIBS="%BASE_PROJECT_DIR%\WEB-INF\lib";
set TOMCAT_LIBS="d:\apache group\Tomcat 5.5\common\lib";

echo Enviando mails de aviso... (por favor, no cierre esta ventana)
java -Djava.ext.dirs=%BASE_JAVA_LIBS%;%PROJECT_LIBS%;%TOMCAT_LIBS% -cp %FULL_PATH% com.justinmind.MailSystem.StandaloneMailSender MailContractsAboutToExpire
