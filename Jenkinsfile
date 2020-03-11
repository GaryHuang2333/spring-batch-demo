pipeline {
    agent any

    stages {
        stage('Compile'){
            steps {
                withMaven(maven : 'maven_3.6.2'){
                    echo "maven info : ${POM_DISPLAYNAME}-${POM_VERSION}"
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                withMaven(maven : 'maven_3.6.2'){
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy') {
            steps {
                withMaven(maven : 'maven_3.6.2'){
                    echo "maven info : ${POM_DISPLAYNAME}-${POM_VERSION}"
                    sh 'mvn deploy'
                }
            }
        }
    }
}