job('NodeJS example') {
    scm {
        git('git://github.com/aldredb/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Aldred')
            node / gitConfigEmail('aldred@mailinator.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('NodeJS-12') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install")
    }
}
