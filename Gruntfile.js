module.exports = function (grunt) {
    'use strict';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);
};
