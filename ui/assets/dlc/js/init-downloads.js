/**
 * DEPENDENCIES: global
 */
(function (global, $) {
    'use strict';

    $(document).ready(function() {
        initCheckboxToggle();
        var downloadHandler = new global.DownloadHandler();
        registerDecryptEvent(downloadHandler);
        registerStartDownloadEvent(downloadHandler);
    });

    function initCheckboxToggle() {
        var checkboxToggle = new global.CheckboxToggle({
            'parentSelector': '.download-files'
        });

        checkboxToggle.enable();
    }

    function registerDecryptEvent(downloadHandler) {
        $('.decrypt-dlc-file').on('click', function () {
            var formData = new FormData($('.dlcFileForm')[0]);
            downloadHandler.decryptFile(formData, createFileEntries);
        });
    }

    function createFileEntries(data) {
        data.forEach(function (element) {
            var tableRow = $('<tr class="decrypted-dlc-file"/>')
                .append('<td><input type="checkbox" class="icheckbox_flat-blue"></td>')
                .append('<td><span class="label label-warning">Checking</span></td>')
                .append('<td class="decrypted-dlc-file-name">' + element.name + '</td>')
                .append('<td class="decrypted-dlc-file-url">' + element.url + '</td>');

            $('tbody').append(tableRow);
        });
    }

    function registerStartDownloadEvent(downloadHandler) {
        $('.start-file-download').on('click', function() {
            var urls = [];

            $('tr.decrypted-dlc-file').each(function() {
                var $element = $(this);
                var checkbox = $element.find('input[type=checkbox]');

                if (checkbox.is(':checked')) {
                    var fileName = $element.find('.decrypted-dlc-file-name').text();
                    var fileUrl = $element.find('.decrypted-dlc-file-url').text();
                    urls.push({name: fileName, url: fileUrl});
                }
            });

            downloadHandler.startDownload(JSON.stringify(urls));
        });
    }

})(this, this.jQuery);