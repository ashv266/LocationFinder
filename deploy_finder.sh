# For Mac users, make sure that JAVA_HOME it set. You can do this by
# echo $JAVA_HONE
# and you should see a path like below. If you do not, then uncomment
# the following line.
#export JAVA_HOME="/usr/libexec/java_home"

./cleanup.sh

echo ""
echo "Copying new spot-finder webapp to "${APACHE_TOMCAT_PATH}/webapps/spot-finder
sudo cp -r ${PWD}/target/spot-finder ${APACHE_TOMCAT_PATH}/webapps/spot-finder

echo ""
echo "Starting up tomcat"
sh ${APACHE_TOMCAT_PATH}/bin/startup.sh

echo ""
echo "Ready. "
