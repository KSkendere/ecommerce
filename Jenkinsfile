pipeline {
agent any

stages{

// stage ('Checkout Codebase'){
//
// steps{
// cleanWS()
// checkout scm: [&class: 'GitSCM', branches:[[name:'*/main']],
// userRemoteConfigs:[[username: 'KSkendere', password: 'Oskars12', url:'git@github.com:KSkendere/ecommerce.git']]]
//
// }
// }

stage ('Build'){

steps{
bat'mkdir lib'
bat 'cd lib/:wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-stadalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
bat 'cd src; javac -cp"../lib/junit-platform-console-standalone-1.7.0-all.jar" CountryIT.java Country.java  EcommerceApplication.java'

}
}

stage ('Test'){

steps{

bat 'cd src/ ; java- jar../lib/junit-platform-console-standalone-1.7.0-all.jar -cp"." --select-class CountryIT --reports-dir=reports"'


}
}

stage ('Deploy'){

steps{

bat 'cd src/ ; java EcommerceApplication'



}
}


}
}
