@echo off
rem
rem $LastChangedDate: 2006-09-12 15:26:43 +0300 (ג, 12 ספטמבר 2006) $ 
rem

rem
rem This script is derived from 'emulator' one.
rem One may track the changes in 'emulator' script
rem and apply them to this one.
rem

if "%OS%" == "Windows_NT" setlocal

set KVEM_HOME=

set DEMO_JAR=demo.jar

set javapathtowtk=

if not "%KVEM_HOME%" == "" goto checkLocation (
    echo "You must set KVEM_HOME to the installation directory of the WTK"
    goto end
)

:checkLocation

if not exist ..\res\imagenames.properties (
    echo *** Run this batch file from its location directory only. ***
    goto end
)

if not exist %DEMO_JAR% (
    echo *** You should build the %DEMO_JAR% first. ***
    goto end
)


set DEMO_BIN=.
set DEMO_LIB=%DEMO_BIN%\%DEMO_JAR%

set KVEM_LIB=%KVEM_HOME%\wtklib

set CLASSPATH=%KVEM_LIB%\kvem.jar
set CLASSPATH=%CLASSPATH%;%KVEM_LIB%\kenv.zip
set CLASSPATH=%CLASSPATH%;%KVEM_LIB%\gcf-op.jar
set CLASSPATH=%CLASSPATH%;%DEMO_LIB%

%javapathtowtk%java -Dkvem.home="%KVEM_HOME%" -Djava.library.path=%KVEM_HOME%\bin -cp %CLASSPATH% ObexDemoMain

:end
if "%OS%" == "Windows_NT" endlocal

rem do a "pause" always
pause
