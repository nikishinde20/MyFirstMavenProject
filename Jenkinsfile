pipeline {
  agent any
  options { timestamps(); ansiColor('xterm') }
  tools { jdk 'JDK11'; maven 'Maven-3.9' }
  environment {
    MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
  }
  stages {
    stage('Checkout') {
      steps {
        // If your job is "Pipeline script from SCM", Jenkins does checkout for you.
        // If using inline pipeline, uncomment the next line and set your repo URL/branch.
        // git branch: 'main', url: 'https://github.com/<user>/<repo>.git', credentialsId: '<cred-id>'
        echo "SCM checkout done by Jenkins"
      }
    }
    stage('Build') {
      steps {
        // Windows:
        bat 'mvn -v'
        bat 'mvn -B -DskipTests clean install'
        // Linux/Mac alternative:
        // sh 'mvn -B -DskipTests clean install'
      }
    }
    stage('Test') {
      steps {
        // Headless browser is best for CI. Ensure your tests use headless Chrome.
        bat 'mvn -B test'
        // sh 'mvn -B test'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'        // JUnit/TestNG XMLs
      archiveArtifacts artifacts: 'target/**/*.html, target/**/*.json, target/**/*.png', fingerprint: true, onlyIfSuccessful: false
    }
  }
}
