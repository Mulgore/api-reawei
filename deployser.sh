mvn clean package -Dmaven.test.skip -Prelease
scp -r target/root.war root@123.206.181.24:/home/api/jetty/webapps/