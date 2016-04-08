module.exports = function (grunt) {
    'use strict';

    global.dlc = {};
    global.dlc.CONF = 'conf/';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);

    grunt.registerTask('test', [

    ]);
};
