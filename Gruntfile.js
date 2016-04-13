module.exports = function (grunt) {
    'use strict';

    global.dlc = {};
    global.dlc.CONF = 'conf/';
    global.dlc.PLATFORM_ASSETS = 'ui/assets/platform/';
    global.dlc.DLC_ASSETS = 'ui/assets/dlc/';

    global.libs = {};
    global.libs.BOOTSTRAP_ASSETS = 'ui/bower_components/admin-lte/bootstrap/';
    global.libs.ADMINLTE_ASSETS = 'ui/bower_components/admin-lte/dist/';
    global.libs.JQUERY_ASSETS = 'ui/bower_components/admin-lte/plugins/jQuery/';
    global.libs.ICHECK_ASSETS = 'ui/bower_components/admin-lte/plugins/iCheck/';
    global.libs.INPUTMASK_ASSETS = 'ui/bower_components/admin-lte/plugins/input-mask/';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);

    grunt.registerTask('test', [

    ]);
};
