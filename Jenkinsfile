pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
    
    post {
        failure {
            echo 'Build or tests failed!'
        }
        success {
            echo 'Build and tests passed successfully!'
        }
    }
}
