@echo off
@setlocal

pushd %~dp0..
set   SE_HOME=%CD%
popd

set CLASSPATH=%SE_HOME%/bin/cm-start.jar

if not "%DEBUG%"=="" (
    set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=7878,server=y,suspend=y
)  
java %JAVA_OPTS% com.greenbee.cm.start.JettyMain %*
