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
        var downloadHandler = new global.DownloadHandler();
        
        $('.dlcDecrypt').on('click', function () {
            var formData = new FormData($('.dlcFileForm')[0]);
            downloadHandler.decryptFile(formData, createFileEntries);
        });
    }

    function createFileEntries(data) {
        data.forEach(function (element) {
            var tableRow = $('<tr/>')
                .append('<td><input type="checkbox" class="icheckbox_flat-blue"></td>')
                .append('<td><span class="label label-warning">Checking</span></td>')
                .append('<td>' + element.name + '</td>')
                .append('<td>' + element.url + '</td>');

            $('tbody').append(tableRow);
        });
    }
    
})(this, this.jQuery);