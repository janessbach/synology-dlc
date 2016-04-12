/**
 * DEPENDENCIES: global
 */
(function (global, $) {
    'use strict';

    $(document).ready(function() {
        initCheckboxToggle();
        initDownloadHandler()
    });

    function initCheckboxToggle() {
        var checkboxToggle = new global.CheckboxToggle({
            'parentSelector': '.download-files'
        });

        checkboxToggle.enable();
    }
    
    function initDownloadHandler() {
        var downloadHandler = DownloadHandler();
        
        $('.dlcDecrypt').on('click', function () {
            var formData = new FormData($('.dlcFileForm')[0]);
            downloadHandler.decryptFile(formData);
        });
    }
    
})(this, this.jQuery);