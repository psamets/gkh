pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/psamets/gkh.git'
            }
        }
        stage('Build') {
            steps {
                // Команды сборки проекта, например, Gradle
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                // Команды тестирования проекта
                sh './gradlew test'
            }
        }
        stage('Deploy') {
            steps {
                // Команды деплоя проекта на удаленный сервер
                //sh 'scp -r build/libs/* user@server:/path/to/deploy'
                // Команды деплоя проекта на текущий локальны сервер
                sh 'cp -r build/libs/* /path/to/deploy'
            }
        }
    }
    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
