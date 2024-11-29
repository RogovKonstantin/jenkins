pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'jenkins-app-image'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: '64841f38-338d-4e86-8248-a89b5bb8c573', url: 'https://github.com/RogovKonstantin/jenkins.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh '''
                chmod +x ./mvnw
                ./mvnw clean package -DskipTests
                '''
            }
        }

        stage('Verify Artifact') {
            steps {
                sh '''
                echo "Checking if artifact exists..."
                ls -l target/
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker stop jenkins-app || true
                docker rm jenkins-app || true
                docker run -d --name jenkins-app -p 9000:9000 ${DOCKER_IMAGE}:${DOCKER_TAG}
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed.'
        }
    }
}
