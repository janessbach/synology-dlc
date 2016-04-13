module.exports = function (grunt) {
    'use strict';
    grunt.config('concat', {

        basic: {
            src: [
                global.libs.JQUERY_ASSETS + '*.min.js',
                global.libs.BOOTSTRAP_ASSETS + 'js/*.min.js',
                global.libs.ICHECK_ASSETS + '*.min.js',
                global.libs.INPUTMASK_ASSETS + 'jquery.inputmask.js',
                global.libs.INPUTMASK_ASSETS + 'jquery.inputmask.extensions.js',
                global.libs.ADMINLTE_ASSETS + 'js/*.min.js'
            ],
            dest: 'public/js/basic.js'
        },

        extra: {
            src: [
                global.dlc.CORE_ASSETS + 'js/*.js',
                global.dlc.DLC_ASSETS + 'js/*.js'
            ],
            dest: 'public/js/extra.js'
        }
    });
};