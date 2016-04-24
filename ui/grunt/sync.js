module.exports = function (grunt) {
    'use strict';
    grunt.config('sync', {
        main: {
            files: [
                {
                    cwd: global.libs.PLATFORM_ASSETS,
                    src: ['**/*.css'],
                    dest: global.base.UI_TMP
                },
                {
                    cwd: global.libs.DLC_ASSETS,
                    src: ['**/*.css'],
                    dest: global.base.UI_TMP
                },
                {
                    cwd: global.libs.BOOTSTRAP_ASSETS,
                    src: ['css/*.min.css', 'fonts/**'],
                    dest: global.base.UI_TMP
                },
                {
                    cwd: global.libs.ADMINLTE_ASSETS,
                    src: ['css/*.min.css', 'css/**/skin-blue.min.css', 'img/**'],
                    dest: global.base.UI_TMP
                },
                {
                    cwd: global.libs.ICHECK_ASSETS,
                    src: ['square/blue.css', 'square/blue.png', 'flat/blue.css', 'flat/blue.png'],
                    dest: global.base.UI_TMP + 'css'
                },
                {
                    cwd: global.base.UI_TMP,
                    src: ['**'],
                    dest: global.base.PUBLIC_DIR
                }
            ]
        }
    })
};