pipeline{

agent any
tools{
maven 'myMaven'
jdk 'myJava'}
stages{
stage('Test'){
steps{
bat 'mvn clean test'
}
}
stage('Build'){
steps{
bat 'mvn -Dmaven.test.failure.ignore=true install'
}
post{
success{junit 'target/surefire-reports/**/*.xml'
}}
}


}

}