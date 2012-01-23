@REM
@REM This script must be executed before publishing the servlets to the server in Eclipse !!!
@REM

@mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:copy-dependencies -DoutputDirectory="WebContent/WEB-INF/lib"
