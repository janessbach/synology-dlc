module.exports = function (grunt) {
    'use strict';
    grunt.config('sync', {
        main: {
            files: [
                {
                    cwd: global.libs.BOWER_COMPONENTS,
                    src: [
                        'scripts.js'
                    ],
                    dest: 'public/js/'
                },
                {
                    cwd: global.dlc.PLATFORM_ASSETS,
                    src: [
                        '**/*.css'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.dlc.DLC_ASSETS,
                    src: [
                        '**/*.css'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.libs.BOOTSTRAP_ASSETS,
                    src: [
                        'css/*.min.css',
                        'fonts/**'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.libs.ADMINLTE_ASSETS,
                    src: [
                        'css/*.min.css',
                        'css/**/skin-blue.min.css',
                        'img/**'
                    ],
                    dest: 'public/'
                },
                {
                    cwd: global.libs.ICHECK_ASSETS,
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