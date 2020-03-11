pipeline {
    agent any

    tools {
        maven "maven_3.6.2"
    }

    parameters {
        string(name: 'greetings', defaultValue: 'Hello World', description: 'Say Hello ?')
    }

    stages {

        stage('Compile'){
            steps {

                script {
                    def pom = readMavenPom file: "pom.xml"
                    env.PROJECT_NAME = pom.artifactId
                    env.POM_VERSION = pom.version
                    //env.input_param1 = ${input_param}
                }

                echo "Greetings with ${params.greetings}"
                echo "pom info1 : ${PROJECT_NAME} of ${POM_VERSION}"
                echo "input_param1 : ${input_param1}"
                echo "input_param2 : ${input_param}"
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