pipeline {
    agent { docker { image 'maven:3.5.4-jdk-10' } }
    stages {
        stage('Init') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
        success {
            sh 'rm -rf /opt/tomcat/hermes/*'
            sh 'cp target/*.war /opt/tomcat/hermes/'
        }
    }
}
