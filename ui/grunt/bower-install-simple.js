module.exports = function (grunt) {
    'use strict';
    grunt.config('bower-install-simple', {
        options: {
            cwd: "./ui",
            directory: "./bower_components"
        },
        dev: {
            options: {
                production: false
            }
        }
    });
};