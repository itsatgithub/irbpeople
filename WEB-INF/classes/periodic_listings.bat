@ECHO OFF

SET TOMCATDIR=d:/apache group/tomcat 5.5
SET LIBDIR=../lib

REM needed to overcome weird loop behavior
REM in conjunction with variable expansion
SETLOCAL enabledelayedexpansion

REM construct classpath of separate jars
SET CLASSPATH=.;%TOMCATDIR%/common/lib/servlet-api.jar;%TOMCATDIR%/common/lib/jsp-api.jar;

FOR %%F IN (%LIBDIR%/*.jar) DO (
  SET CLASSPATH=!CLASSPATH!;%LIBDIR%/%%F%
)

REM echo %CLASSPATH%

java -cp "%CLASSPATH%" utils.excel.CustomListExporter

