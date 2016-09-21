PROJECT_PATH="$PWD"
WAR_FILE_LOCATION=$PROJECT_PATH/target/SpotFinder-1.0-SNAPSHOT.war

if [ -z "$APACHE_TOMCAT_PATH" ]; then
	echo "Export apache tomcat's path to APACHE_TOMCAT_PATH"
	echo "export APACHE_TOMCAT_PATH=<path-to-apache-tomcat>"
	echo "eg. export APACHE_TOMCAT_PATH=~/apache-tomcat-8.0.30"
	exit 1
fi

if [ -z "$APACHE_MAVEN_PATH" ]; then
	echo "Export apache maven's path to APACHE_MAVEN_PATH"
	echo "export APACHE_MAVEN_PATH=<path-to-maven-tomcat>"
	echo "eg. export APACHE_MAVEN_PATH=~/apache-maven-3.3.9"
	exit 1
fi

echo "Using the following path for Apache Tomcat"
echo $APACHE_TOMCAT_PATH

echo ""

echo "Using the following path for Apache Maven"
echo $APACHE_MAVEN_PATH

echo ""

echo "Building Location Finder Project"
${APACHE_MAVEN_PATH}/bin/mvn clean install

echo ""
echo "Writing XML config to "${APACHE_TOMCAT_PATH}/conf

xml_tag='<Context docBase="'
xml_end='" path="" reloadable="true"/>'

echo "${xml_tag}${WAR_FILE_LOCATION}${xml_end}" > "${APACHE_TOMCAT_PATH}/conf/spot-finder-context.xml"

echo ""
echo "Setup is complete.. Ready to deploy"
