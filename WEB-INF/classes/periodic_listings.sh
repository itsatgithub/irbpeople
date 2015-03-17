#! /bin/sh

TOMCATDIR=/usr/share/tomcat5.5
LIBDIR=../lib

CLASSPATH=.:$TOMCATDIR/common/lib/servlet-api.jar:$TOMCATDIR/common/lib/jsp-api.jar
CLASSPATH=$CLASSPATH$(find $LIBDIR -name *.jar -exec printf :{} ';')

java -cp $CLASSPATH utils.excel.CustomListExporter

