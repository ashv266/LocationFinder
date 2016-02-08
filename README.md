# Location Finder
This application reverse geocodes coordinates to return the State within which the coordinates are located.
The U.S. States and their borders are pre-loaded into a reference json file. The application creates areas per State 
from this file. It then uses these pre-computed areas to find the State in which the input coordinates fall.

## Pre-requisites
 
-<strong> JDK version 7 or higher. </strong>

	Link: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

-<strong> Apache Tomcat 6 or higher </strong> (8 is preferable, but the application should run with version 6 as well).

	Link: http://tomcat.apache.org/download-80.cgi
	Instructions: https://tomcat.apache.org/tomcat-8.0-doc/appdev/installation.html

-<strong> Apache Maven 3 or higher. </strong>

	Link: https://maven.apache.org/download.cgi

## Setup Instructions
- Download the binaries mentioned above and install
- For Maven and Tomcat: Unzipping the binary zip files should suffice
- export APACHE_TOMCAT_PATH=<path-to-apache>  ## Example: <code>export APACHE_TOMCAT_PATH=~/apache-tomcat-8.0.30 </code>
- export APACHE_MAVEN_PATH=<path-to-maven>  ## Example: <code>export APACHE_TOMCAT_PATH=~/apache-maven-3.3.9</code>
- Run the setup script as <code>./setup.sh</code>
- Once setup is complete, run the deployment script <code>./deploy_finder.sh </code>
- Tomcat should be running in the backgroud and you can see make requests to the deployed service
- Run the Curl Query ## Example: <code>curl -d "longitude=-77.036133&latitude=40.513799" http://localhost:8080/location-finder </code>

## Notes:
###Tomcat
In case Tomcat does not deply correctly, apache-tomcat/logs/catalina.out should provide more information on the failure.

###Maven
Make sure that JDK is installed correctly. You should be able to query the
version of maven without errors:
<code>
$ <path-to-maven>/bin/mvn --version
</code>

The application can also be accessed via a REST API on the path:
http://localhost:8080/location-finder/longitude/{longitude}/latitude/{latitude}

If you see permission denied while running any scripts, change the
permission of the script as follows:
<code>chmod 755 script-name.sh</code>

## Additional Info
Project Location Finder was tested on Mac OSX 10.11.2
