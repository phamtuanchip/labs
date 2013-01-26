@echo off


REM * Copyright (c) 2007, Sun Microsystems, Inc.
REM *
REM * All rights reserved.
REM *
REM * Redistribution and use in source and binary forms, with or without
REM * modification, are permitted provided that the following conditions
REM * are met:
REM *
REM *  * Redistributions of source code must retain the above copyright
REM *    notice, this list of conditions and the following disclaimer.
REM *  * Redistributions in binary form must reproduce the above copyright
REM *    notice, this list of conditions and the following disclaimer in the
REM *    documentation and/or other materials provided with the distribution.
REM *  * Neither the name of Sun Microsystems nor the names of its contributors
REM *    may be used to endorse or promote products derived from this software
REM *    without specific prior written permission.
REM *
REM * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
REM * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
REM * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
REM * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
REM * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
REM * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
REM * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
REM * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
REM * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
REM * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
REM * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

REM
REM $LastChangedDate: 2007-04-05 12:56:19 +0300 (ה, 05 אפריל 2007) $ 
REM

rem Should have the following variables set:
rem
rem  set JAVA_HOME=<path to J2SDK installation root>
rem  set TOMCAT_HOME=<path to jwsdp-1.2 installation root> 

set PATH=%TOMCAT_HOME%/bin;%TOMCAT_HOME%/jaxrpc/bin;%JAVA_HOME%/bin;%PATH%

rem check variables correctness
if ""%TOMCAT_HOME%""=="""" goto no_tomcat
if not exist %TOMCAT_HOME%\jaxrpc\bin\wscompile.bat goto no_tomcat2
if ""%JAVA_HOME%""=="""" goto no_java
if not exist %JAVA_HOME%/bin/javac.exe goto no_java2

mkdir WEB-INF
mkdir WEB-INF\classes
echo running javac.exe
javac.exe -d WEB-INF\classes -classpath %TOMCAT_HOME%\jaxrpc\lib\jaxrpc-api.jar;%TOMCAT_HOME%\common\lib\servlet-api.jar src\serverscript\Implementation.java src\serverscript\Interface.java 
if NOT %ERRORLEVEL%==0 goto error

mkdir tmp_src 
echo running wscompile.bat
call wscompile.bat -gen:server -d WEB-INF\classes -keep -model WEB-INF\model.gz  -s tmp_src -f:wsi -f:documentliteral -classpath WEB-INF\classes src\config.xml
if NOT %ERRORLEVEL%==0 goto error

copy src\web.xml WEB-INF
copy src\jaxrpc-ri.xml WEB-INF
echo running jar.exe
jar.exe -cf serverscript.jar WEB-INF
echo running wsdeploy.bat
call wsdeploy.bat serverscript.jar -o serverscript.war

goto exit

:no_tomcat
 echo TOMCAT_HOME variable is not set
:no_tomcat2
 echo TOMCAT_HOME should point to JWSDP-1.2 installation
 echo You can download it from:
 echo http://java.sun.com/webservices/downloads/webservicespack.html
 goto error

:no_java
 echo JAVA_HOME variable is not set
:no_java2
 echo JAVA_HOME should point to J2SDK installation
:error
:exit
 pause
