@ECHO OFF
SETLOCAL

SET "BASE_DIR=%~dp0"
IF "%BASE_DIR%"=="" SET "BASE_DIR=."
IF NOT DEFINED MAVEN_BATCH_PAUSE SET MAVEN_BATCH_PAUSE=0
SET "MAVEN_PROJECTBASEDIR=%BASE_DIR%"
SET "WRAPPER_JAR=%BASE_DIR%\.mvn\wrapper\maven-wrapper.jar"
SET "WRAPPER_PROPERTIES=%BASE_DIR%\.mvn\wrapper\maven-wrapper.properties"
SET "WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain"

IF EXIST "%WRAPPER_PROPERTIES%" (
  FOR /F "tokens=1,* delims==" %%A IN ('type "%WRAPPER_PROPERTIES%" ^| findstr /R "^wrapperUrl="') DO (
    IF "%%A"=="wrapperUrl" SET "WRAPPER_URL=%%B"
  )
)

IF NOT EXIST "%WRAPPER_JAR%" (
  IF "%WRAPPER_URL%"=="" (
    ECHO wrapperUrl not set in %WRAPPER_PROPERTIES%
    EXIT /B 1
  )
  POWERSHELL -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'"
  IF ERRORLEVEL 1 (
    ECHO Failed to download Maven Wrapper from %WRAPPER_URL%
    EXIT /B 1
  )
)

IF "%JAVA_HOME%"=="" (
  SET "JAVA_EXE=java"
) ELSE (
  SET "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
)

"%JAVA_EXE%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" -classpath "%WRAPPER_JAR%" %WRAPPER_LAUNCHER% %*
SET "ERROR_CODE=%ERRORLEVEL%"

IF NOT "%MAVEN_BATCH_PAUSE%"=="" IF NOT "%MAVEN_BATCH_PAUSE%"=="0" PAUSE

EXIT /B %ERROR_CODE%
