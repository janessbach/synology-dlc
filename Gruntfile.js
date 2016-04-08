module.exports = function (grunt) {
    'use strict';

    global.dlc = {};
    global.dlc.CONF = 'conf/';
    global.dlc.UI_ASSETS = 'ui/assets/';
    global.dlc.BOOTSTRAP_ASSETS = 'ui/bower_components/admin-lte/bootstrap';
    global.dlc.ADMINLTE_ASSETS = 'ui/bower_components/admin-lte/dist/';
    global.dlc.JQUERY_ASSETS = 'ui/bower_components/admin-lte/plugins/jQuery';
    global.dlc.ICHECK_ASSETS = 'ui/bower_components/admin-lte/plugins/iCheck';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);

    grunt.registerTask('test', [

    ]);
};
