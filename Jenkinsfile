pipeline {
    agent any

   tools {
      maven "maven_3.6.2"
   }

    stages {
        stage('Compile'){
            steps {
                script {
                    def pom = readMavenPom file: "pom.xml"
                    env.PROJECT_NAME = pom.artifactId
                    env.POM_VERSION = pom.version
                }
                echo "pom info1 : ${PROJECT_NAME}-${POM_VERSION}"
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                    echo "test complete"
                    //sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                    echo "deploy complete"
                    //sh 'mvn deploy'
            }
        }
    }
}