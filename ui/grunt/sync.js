module.exports = function (grunt) {
    'use strict';
    grunt.config('sync', {
        main: {
            files: [
                {
                    cwd: global.dlc.CONF,
                    src: ['**/**'],
                    dest: 'public/img/'
                }
            ]
        }
    })
};