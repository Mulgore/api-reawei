 mvn clean package -Dmaven.test.skip -Prelease
 scp -r target/root.war apiserver@120.27.219.12:/home/apiserver/builds/
