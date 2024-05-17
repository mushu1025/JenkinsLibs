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

pipelineJob('project-a/192.168.0.2') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
}

job('project-a/192.168.0.3') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
}

job('project-a/192.168.0.4') {
    triggers {
        upstream('TriggerAll', 'SUCCESS')
    }
}
