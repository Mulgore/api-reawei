mvn clean package -Dmaven.test.skip -Prelease
scp -r target/root.war api@123.206.181.24:/home/api/jetty/webapps/