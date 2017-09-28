 mvn clean package -Dmaven.test.skip -Prelease
 scp -r target/root.war apiserver@123.206.181.24:/home/apiserver/builds/
