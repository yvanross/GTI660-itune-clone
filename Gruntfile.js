module.exports = function(grunt) {
    // load all tasks in grunt file
    require('load-grunt-tasks')(grunt); // npm install --save-dev load-grunt-tasks

    // configuration
    grunt.initConfig({
        shell: {
            options: {
                stderr: false
            },
            target: {
                command: 'ls'
            },
            mvnbuild: {
                // build shell command
                command: function(projectDir, className) {
                    var format = require("string-template");
                    return format("cd {0} && mvn clean -f {1} -Dtest={2} test", [
                        projectDir,
                        projectDir + '/pom.xml',
                        className
                    ]);
                }
            }
        }
    });

    // grunt task to watch for java changes and execute them accordingly.
    grunt.registerTask('mvnwatch', function(folderName) {
        if (folderName && grunt.file.exists(folderName) && grunt.file.isDir(folderName)) {
            grunt.event.on('watch', function(action, filepath) {
                //var finder = require('fs-finder'), path = require('path');
                //var pomFiles = finder.in(path.dirname(filepath)).lookUp().findFiles('pom.xml'),
                var regex = /(.*)\/(.*)\.java/,
                    matchedClassName = filepath.match(regex);
                if (matchedClassName && matchedClassName.length === 3) {
                    var className = matchedClassName[2];
                    grunt.task.run("shell:mvnbuild:" + folderName + ":" + className);
                } else {
                    grunt.fail.fatal("Unable to get classname from file. ");
                }
            });
            // provide the watch configuration at runtime.
            grunt.extendConfig({
                watch: {
                    all: {
                        files: [folderName + "/**/*.java"],
                        options: {
                            nospawn: true
                        }
                    }
                }
            });
            // run the watch task
            grunt.task.run('watch');
        } else {
            grunt.fail.fatal("Provided folder \"" + folderName + "\" does not exist. ");
        }
    });

    grunt.registerTask('default', ['shell']);
};