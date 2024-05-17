folder('project-a') {
    displayName('Project A')
    description('Folder for project A')
}

job('project-a/TriggerAll') {
}

pipelineJob('project-a/192.168.0.1') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
}

job('project-b/192.168.0.1') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
    steps {
        sshBuilder {
            siteName('root@192.168.3.119:22')
            command('ls  -trl')
            // Execute each line in the script individually.
            execEachLine(false)
            hideCommand(false)
        }
    }
}

job('project-b/192.168.0.2') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
    steps {
        sshBuilder {
            siteName('root@192.168.3.119:22')
            command('ls  -trl')
            // Execute each line in the script individually.
            execEachLine(false)
            hideCommand(false)
        }
    }
}