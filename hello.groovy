node {
    timestamps{
        echo 'Hello World'
		stage('deleting dir') {
          deleteDir()
		}
		stage('checkout code') {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/vijay20884/groovy.git']]])
        }
		stage('write & type files') {
		 bat 'cd'
         writeFile file: 'sample', text: 'def printRamu() { echo \'Hello World\'}\nreturn this;'
         writeFile file: 'sample1', text: 'Hello Preetham'
         bat 'dir'
         bat 'type sample'
         bat 'type sample1'
		}
        def u = fileExists 'sample'
        if(u) {
            echo 'File -sample- is present'
        } else {
            echo 'File -sample- not present'
        }
        stash excludes: '**sample1', includes: '**sample', name: 'StashSample'
        def v = fileExists 'sample'
        if(v) {
            echo 'File -sample- is present'
        } else {
            echo 'File -sample- not present'
        }
        def w = fileExists 'sample1'
        if(w) {
            echo 'File -sample1- is present'
        } else {
            echo 'File -sample1- not present'
        }
        deleteDir()
        def x = fileExists 'sample'
        if(x) {
            echo 'File -sample- is present'
        } else {
            echo 'File -sample- not present'
        }
        unstash 'StashSample'
        def y = fileExists 'sample'
        if(y) {
            echo 'File -sample- is present'
        } else {
            echo 'File -sample- not present'
        }
        def z = load 'sample'
        z.printRamu()
        withEnv(["a=b"]) {
            def b = 123
            echo '%env.a%'
        }
		echo 'This file is in Git'
    }
}