// DSL Script to create job1, job2, and job3

pipelineJob('job1') {
    description('Job 1 that triggers Job 2 and Job 3 with its parameters')

    parameters {
        stringParam('PARAM1', 'default_value', 'Parameter 1 to pass to Job 2 and Job 3')
        stringParam('PARAM2', 'default_value', 'Parameter 2 to pass to Job 2 and Job 3')
    }

    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    stages {
                        stage('Trigger Job 2 and Job 3') {
                            steps {
                                build job: 'job2', parameters: [
                                    string(name: 'PARAM1', value: params.PARAM1),
                                    string(name: 'PARAM2', value: params.PARAM2)
                                ]
                                build job: 'job3', parameters: [
                                    string(name: 'PARAM1', value: params.PARAM1),
                                    string(name: 'PARAM2', value: params.PARAM2)
                                ]
                            }
                        }
                    }
                }
            """)
            sandbox()
        }
    }
}

pipelineJob('job2') {
    description('Job 2 that receives parameters from Job 1')

    parameters {
        stringParam('PARAM1', '', 'Parameter 1 from Job 1')
        stringParam('PARAM2', '', 'Parameter 2 from Job 1')
    }

    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    stages {
                        stage('Process Parameters') {
                            steps {
                                echo "Received PARAM1: \${params.PARAM1}"
                                echo "Received PARAM2: \${params.PARAM2}"
                                // Add your job logic here
                            }
                        }
                    }
                }
            """)
            sandbox()
        }
    }
}

pipelineJob('job3') {
    description('Job 3 that receives parameters from Job 1')

    parameters {
        stringParam('PARAM1', '', 'Parameter 1 from Job 1')
        stringParam('PARAM2', '', 'Parameter 2 from Job 1')
    }

    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    stages {
                        stage('Process Parameters') {
                            steps {
                                echo "Received PARAM1: \${params.PARAM1}"
                                echo "Received PARAM2: \${params.PARAM2}"
                                // Add your job logic here
                            }
                        }
                    }
                }
            """)
            sandbox()
        }
    }
}
