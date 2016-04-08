module.exports = function (grunt) {
    'use strict';
    grunt.config('sync', {
        main: {
            files: [
                {
                    cwd: global.dlc.UI_ASSETS,
                    src: ['**/*.min.js'],
                    dest: 'public/'
                },
                {
                    cwd: global.dlc.UI_ASSETS,
                    src: ['**/*.min.css'],
                    dest: 'public/'
                },
                {
                    cwd: global.dlc.BOOTSTRAP_ASSETS,
                    src: [
                        'css/*.min.css',
                        'js/*.min.js',
                        'fonts/**'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.dlc.ADMINLTE_ASSETS,
                    src: [
                        'css/*.min.css',
                        'css/**/skin-blue.min.css',
                        'js/*.min.js',
                        'img/**'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.dlc.JQUERY_ASSETS,
                    src: ['**/*.min.js'],
                    dest: 'public/js'
                },
                {
                    cwd: global.dlc.ICHECK_ASSETS,
                    src: ['**/*.min.js'],
                    dest: 'public/js'
                },
                {
                    cwd: global.dlc.ICHECK_ASSETS,
                    src: [
                        'square/blue.css',
                        'square/blue.png',
                        'flat/blue.css',
                        'flat/blue.png'
                    ],
                    dest: 'public/css'
                }
                
            ]
        }
    })
};