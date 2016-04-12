module.exports = function (grunt) {
    'use strict';

    global.dlc = {};
    global.dlc.CONF = 'conf/';
    global.dlc.CORE_ASSETS = 'ui/assets/core';
    global.dlc.DOWNLOADS_ASSETS = 'ui/assets/downloads';
    global.dlc.BOOTSTRAP_ASSETS = 'ui/bower_components/admin-lte/bootstrap';
    global.dlc.ADMINLTE_ASSETS = 'ui/bower_components/admin-lte/dist/';
    global.dlc.JQUERY_ASSETS = 'ui/bower_components/admin-lte/plugins/jQuery';
    global.dlc.ICHECK_ASSETS = 'ui/bower_components/admin-lte/plugins/iCheck';
    global.dlc.INPUTMASK_ASSETS = 'ui/bower_components/admin-lte/plugins/input-mask';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);

    grunt.registerTask('test', [

    ]);
};
