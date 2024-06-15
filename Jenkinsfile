pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the source code...'
                git url: 'https://github.com/berkguneey/test-service.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Deploy steps here, örneğin bir sunucuya dosya kopyalama
                // scp, rsync veya bir deployment script kullanabilirsiniz.
                // Örnek:
                // sh 'scp target/your-app.jar user@server:/path/to/deploy'
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
