pipeline{

agent any
tools{
maven 'myMaven'
jdk 'myJava'}
stages{
stage('Test'){
steps{
bat 'mvnw clean test'
}
}
stage('Build'){
steps{
bat 'mvnw -Dmaven.test.failure.ignore=true install'
}
post{
success{junit 'target/surefire-reports/**/*.xml'
}}
}


}

}