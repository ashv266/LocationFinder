sudo rm -rf /opt/apache-tomcat-6.0.44/webapps/location-finder
sudo cp -r /Users/asivaraman/Documents/local_repos/personal_repos/LocationFinder/target/location-finder /opt/apache-tomcat-6.0.44/webapps/location-finder
sudo cp /Users/asivaraman/.m2/repository/com/finder/LocationFinder/1.0-SNAPSHOT/LocationFinder-1.0-SNAPSHOT.war /opt/rovi_base/webapps/location-finder/.
sh /opt/apache-tomcat-6.0.44/bin/startup.sh
