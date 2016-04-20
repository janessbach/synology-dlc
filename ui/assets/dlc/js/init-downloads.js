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
        registerDeleteFilesEvent();
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
            var urls = getSelectedFileElements().map(function(element) {
                var $element = $(element);
                var fileName = $element.find('.decrypted-dlc-file-name').text();
                var fileUrl = $element.find('.decrypted-dlc-file-url').text();
                return {name: fileName, url: fileUrl};
            });

            downloadHandler.startDownload(JSON.stringify(urls));
        });
    }

    function registerDeleteFilesEvent() {
        $('.delete-files').on('click', function() {
            getSelectedFileElements().map(function(element) {
                $(element).remove();
            });
        })
    }

    function getSelectedFileElements() {
        return $('tr.decrypted-dlc-file').toArray().filter(function(element) {
            var checkbox = $(element).find('input[type=checkbox]');
            return !!checkbox && checkbox.is(':checked');
        });
    }

})(this, this.jQuery);