pipeline {
    agent { docker { image 'maven:3.5.4-jdk-10' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'mvn compile'
                sh 'ls target'
            }
        }
    }
}
