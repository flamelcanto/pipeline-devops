/*

    forma de invocación de método call:

    def ejecucion = load 'script.groovy'
    ejecucion.call()

*/

def call(){
pipeline {
    agent any
    environment {
        NEXUS_USER_VAR      = credentials('NEXUS-USER')
        NEXUS_USER_PASS_VAR = credentials('NEXUS-PASS')
    }
    parameters {
        choice(
            name:'compileTool',
            choices: ['Maven', 'Gradle'],
            description: 'Seleccione herramienta de compilacion'
        )
    }
    stages {
        stage("Pipeline"){
            steps {
                script{
                  switch(params.compileTool)
                    {
                        case 'Maven':
           //                 def ejecucion = load 'maven.groovy'
           //                 ejecucion.call()
				maven.call()
                        break;
                        case 'Gradle':
           //                 def ejecucion = load 'gradle.groovy'
           //                 ejecucion.call()
				gradle.call()
                        break;
                    }
                }
            }
        }
    }
}
}
return this;
