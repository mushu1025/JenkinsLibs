String folderNameStr = "$FolderName"
String executeCommands = "$ExecuteCommands"
String remoteIPsStr = "$RemoteIPs"
String delimiter = ','
List<String> remoteIPs = remoteIPsStr.split(delimiter)
println remoteIPs

folder(folderNameStr) {
    displayName(folderNameStr)
    description(folderNameStr)
}

job(folderNameStr+'/TriggerAll') {
}

for (int i = 0; i < remoteIPs.size(); i++) {
    String jobName = remoteIPs.get(i)
    job(folderNameStr+'/'+jobName) {
        triggers {
            upstream('TriggerAll', 'SUCCESS')
        }
        steps {
            sshBuilder {
                siteName('root@'+remoteIPs+':22')
                command(executeCommands)
                // Execute each line in the script individually.
                execEachLine(false)
                hideCommand(false)
            }
        }
    }
}