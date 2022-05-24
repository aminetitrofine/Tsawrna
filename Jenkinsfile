pipeline {
  agent any
  stages {
    stage('log tool version') {
      steps {
        sh '''mvn --verion
git --version
java -version'''
      }
    }

  }
}