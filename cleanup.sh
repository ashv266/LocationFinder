
echo ""
echo "Shutting down tomcat (if running in background)"
${APACHE_TOMCAT_PATH}/bin/shutdown.sh

echo ""
echo "Removing old location-finder webapp"
sudo rm -rf ${APACHE_TOMCAT_PATH}/webapps/location-finder
