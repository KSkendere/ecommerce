pipeline {
agent any

stages{

stage ('Checkout Codebase'){

steps{
cleanWS()
checkout scm: [&class: 'GitSCM', branches:[[name:'*/main']],
userRemoteConfigs:[[username: 'KSkendere', password: 'Oskars12', url:'git@github.com:KSkendere/ecommerce.git']]]

}
}

stage ('Build'){

steps{
sh'mkdir lib'
sh 'cd lib/:wget https://repol.maven.org/maven2/org/junit/platform/junit-platform-console-stadalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
sh 'cd src; javac -cp"../lib/junit-platform-console-standalone-1.7.0-all.jar" CountryIT.java Country.java  EcommerceApplication.java'

}
}

stage ('Test'){

steps{

sh 'cd src/ ; java- jar../lib/junit-platform-console-standalone-1.7.0-all.jar -cp"." --select-class CountryIT --reports-dir=reports"'


}
}

stage ('Deploy'){

steps{

sh 'cd src/ ; EcommerceApplication'

}
}


}
}
