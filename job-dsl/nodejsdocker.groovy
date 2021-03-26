job('NodeJS Docker example') {
    scm {
        git('git://github.com:aldredb/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
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
        dockerBuildAndPublish {
            repositoryName('aldredb/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
