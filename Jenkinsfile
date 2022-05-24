pipeline {
  agent any
  stages {
    stage('log tool version') {
      parallel {
        stage('log tool version') {
          steps {
            sh '''mvn --verion
git --version
java -version'''
          }
        }

        stage('') {
          steps {
            fileExists 'POM.xml'
          }
        }

      }
    }

    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: 'Hey it worked')
      }
    }

  }
}