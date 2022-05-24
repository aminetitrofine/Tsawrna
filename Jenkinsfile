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

    stage('Build with Maven') {
      steps {
        sh 'mvn compile test package'
      }
    }

    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: 'Hey it worked')
      }
    }

  }
}