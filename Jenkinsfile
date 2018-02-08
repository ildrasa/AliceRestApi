// Powered by Infostretch 

timestamps {

node () {

	stage ('alice - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'a5e25869-0c8b-44a5-a230-4523b1cee0e7', url: 'https://github.com/ildrasa/AliceRestApi']]]) 
	}
	stage ('alice - Build') {
 			// Maven build step
	withMaven(maven: 'maven', mavenOpts: '-Dmaven.test.failure.ignore=true') { 
 			if(isUnix()) {
 				sh "mvn -e clean compile test package install deploy  " 
			} else { 
 				bat "mvn -e clean compile test package install deploy  " 
			} 
 		}		// Shell build step
sh """ 
cd ${WORKSPACE}/src/assembly
# bring war in the docker context
cp ${WORKSPACE}/target/Alice_restAPI-1.1-SNAPSHOT.war ./alice.war

# build docker image 
docker build -t 127.0.0.1:5000/alice:SNAPSHOT .

# push image in the docker registry
docker push 127.0.0.1:5000/alice:SNAPSHOT 
 """ 
	}
	stage ('alice integration test - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'a5e25869-0c8b-44a5-a230-4523b1cee0e7', url: 'https://github.com/ildrasa/AliceIntegrationTests']]]) 
	}
	stage ('alice integration test - Build') {
 			// Shell build step
sh """ 
# login into docker
docker login 127.0.0.1:5000 -u jenkins -p jenkins

# pull alice snapshot image
docker pull 127.0.0.1:5000/alice:SNAPSHOT

# run container alice from image alice:SNAPSHOT
docker run -d -p 127.0.0.1:5001:8080  --name alice 127.0.0.1:5000/alice:SNAPSHOT 
 """		// Maven build step
	withMaven(maven: 'maven', mavenOpts: '-Dmaven.test.failure.ignore=true') { 
 			if(isUnix()) {
 				sh "mvn -e compile test " 
			} else { 
 				bat "mvn -e compile test " 
			} 
 		}		// Shell build step
sh """ 
# stop container alice
docker stop alice
sleep 1
# remove alice container
docker rm alice
sleep 1 
 """ 
	}
}
}