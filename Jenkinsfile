pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'jenkinslabappimage'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'd8119c82-f6c1-4a8a-ba4a-5d7e173b160e', url: 'https://github.com/dimapartov/JenkinsLab.git', branch: 'master'
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
                docker stop jenkinslabapp || true
                docker rm jenkinslabapp || true
                docker run -d --name jenkinslabapp -p 8081:8081 ${DOCKER_IMAGE}:${DOCKER_TAG}
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