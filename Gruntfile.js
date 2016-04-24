module.exports = function (grunt) {
    'use strict';

    global.base = {};
    global.base.BOWER_COMPONENTS = 'ui/bower_components/';
    global.base.CONF             = 'conf/';
    global.base.NODE_MODULES     = 'node_modules/';
    global.base.PUBLIC_DIR       = 'public/';
    global.base.UI               = 'ui/';
    global.base.UI_TMP           = 'ui/tmp/';

    global.libs                  = {};
    global.libs.ADMINLTE_ASSETS  = global.base.BOWER_COMPONENTS + 'admin-lte/dist/';
    global.libs.BOOTSTRAP_ASSETS = global.base.BOWER_COMPONENTS + 'admin-lte/bootstrap/';
    global.libs.JQUERY_ASSETS    = global.base.BOWER_COMPONENTS + 'admin-lte/plugins/jQuery/';
    global.libs.ICHECK_ASSETS    = global.base.BOWER_COMPONENTS + 'admin-lte/plugins/iCheck/';
    global.libs.INPUTMASK_ASSETS = global.base.BOWER_COMPONENTS + 'admin-lte/plugins/input-mask/';
    global.libs.WEBSOCKET_ASSETS = global.base.BOWER_COMPONENTS + 'jquery-graceful-websocket/';

    global.libs.DLC_ASSETS       = global.base.UI               + 'assets/dlc/';
    global.libs.PLATFORM_ASSETS  = global.base.UI               + 'assets/platform/';

    grunt.initConfig({pkg: grunt.file.readJSON('package.json')});

    grunt.loadTasks('ui/grunt');

    require('load-grunt-tasks')(grunt);

    grunt.registerTask('dist', ['bower-install-simple', 'concat', 'sync']);

};
