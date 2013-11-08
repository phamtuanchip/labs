@echo off
rem
rem $LastChangedDate: 2006-09-12 15:26:43 +0300 (ג, 12 ספטמבר 2006) $ 
rem
rem
rem This batch file builds the J2SE OBEX DEMO.
rem and packages its files in a JAR file appropriately.
rem
if "%OS%" == "Windows_NT" setlocal

set DEMO_BIN=.
set KVEM_HOME=%DEMO_BIN%\..\..\..\..
set KVEM_LIB=%KVEM_HOME%\wtklib

set DEMO_SRC=%DEMO_BIN%\..\src
set DEMO_RES1=%DEMO_BIN%\..\res
set DEMO_RES2=%DEMO_BIN%\..\..\res
set DEMO_CLASSES=%DEMO_BIN%\..\classes
set DEMO_JAR=%DEMO_BIN%\demo.jar

set JAVA_FILES=
set JAVA_FILES=%JAVA_FILES% %DEMO_SRC%\*.java

if not exist ..\res\imagenames.properties (
    echo *** Run this batch file from its location directory only. ***
    goto end
)

set JAVAC=javac
set JAR=jar

if not "%JAVA_HOME%" == "" (
    set JAVAC=%JAVA_HOME%\bin\javac
    set JAR=%JAVA_HOME%\bin\jar
)

echo *** Creating directories... *** 
if not exist %DEMO_CLASSES% md %DEMO_CLASSES% 

set CLASSPATH=%KVEM_LIB%\kvem.jar
set CLASSPATH=%CLASSPATH%;%KVEM_LIB%\kenv.zip
set CLASSPATH=%CLASSPATH%;%KVEM_LIB%\gcf-op.jar
set CLASSPATH=%CLASSPATH%;%DEMO_CLASSES%

echo *** Compiling source files... ***
%JAVAC% -d %DEMO_CLASSES% -classpath %CLASSPATH% %JAVA_FILES%
          
echo *** Jaring class files... ***
%JAR% cvf %DEMO_JAR% -C %DEMO_CLASSES% .

echo *** Jaring resurcess files... ***
%JAR% uvf %DEMO_JAR% -C %DEMO_RES1% .
%JAR% uvf %DEMO_JAR% -C %DEMO_RES2% .\images

:end
if "%OS%" == "Windows_NT" endlocal
rem do a "pause" always
pause
