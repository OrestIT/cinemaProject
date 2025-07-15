pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8' // або назва, яку ти вказав у Global Tool Configuration
        jdk 'JDK 17'        // якщо використовуєш tool installation
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-org/your-repo.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build успішний'
        }
        failure {
            mail to: 'devops@company.com',
                 subject: 'Jenkins build failed',
                 body: 'Перевір лог білду в Jenkins.'
        }
    }
}
