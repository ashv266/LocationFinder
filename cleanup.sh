
echo ""
echo "Shutting down tomcat (if running in background)"
${APACHE_TOMCAT_PATH}/bin/shutdown.sh

echo ""
echo "Removing old spot-finder webapp"
sudo rm -rf ${APACHE_TOMCAT_PATH}/webapps/spot-finder
