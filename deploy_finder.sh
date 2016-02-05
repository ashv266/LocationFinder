sudo rm -rf /opt/apache-tomcat-8.0.30/webapps/location-finder
sudo cp -r /Users/aishwaryasivaraman/Documents/projects/LocationFinder/target/location-finder /opt/apache-tomcat-8.0.30/webapps/location-finder
sudo cp /Users/aishwaryasivaraman/.m2/repository/com/finder/LocationFinder/1.0-SNAPSHOT/LocationFinder-1.0-SNAPSHOT.war /opt/base/webapps/location-finder/.
sh /opt/apache-tomcat-8.0.30/bin/startup.sh
