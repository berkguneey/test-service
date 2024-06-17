pipeline {
    agent any

    environment {
        DOCKER_HOST = 'tcp://host.docker.internal:2376'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_IMAGE = 'berkguney/api-gateway'
    }

    tools {
        maven 'maven'
    }

    stages {
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
        stage('Dockerize') {
            steps {
                script {
                    def version = sh(script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout", returnStdout: true).trim()
                    def dockerImageTag = "${DOCKER_IMAGE}:${version}"
                    echo "Building Docker image ${dockerImageTag}"

                    sh 'docker build -t ${dockerImageTag} .'

                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                        sh "docker push ${dockerImageTag}"
                        sh 'docker logout'
                    }
                }
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
