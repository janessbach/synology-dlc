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
        registerCancelDownloadEvent();
    });

    function initCheckboxToggle() {
        var checkboxToggle = new global.CheckboxToggle({
            'parentSelector': '.dlc-download-files'
        });

        checkboxToggle.enable();
    }

    function registerDecryptEvent(downloadHandler) {
        $('input[name="dlc-file"]').change(function() {
            var formData = new FormData($('.dlc-file-form')[0]);
            downloadHandler.decryptFile(formData, createFileEntries);
            $('.decrypted-dlc-files').modal('show');
            $('.file-spinner__overlay').show();
        });

        $('.decrypt-dlc-file').on('click', function () {
            $('input[name="dlc-file"]').click();
        });
    }

    function createFileEntries(data) {
        data.forEach(function (element) {
            var tableRow = $('<tr class="decrypted-dlc-file"/>')
                .append('<td class="dlc-download-file__checkbox"><input type="checkbox" class="icheckbox_flat-blue"></td>')
                .append('<td><span class="label label-warning">Checking</span></td>')
                .append('<td class="decrypted-dlc-file-name">' + element.name + '</td>')
                .append('<td class="decrypted-dlc-file-url">' + element.url + '</td>');

            $('tbody').append(tableRow);
            $('.file-spinner__overlay').hide();
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

            deleteFileEntries();
            $('.decrypted-dlc-files').modal('hide');
        });
    }

    function registerCancelDownloadEvent() {
        $('.cancel-file-download').each(function() {
            $(this).on('click', function() {
               deleteFileEntries();
            });
        });
    }

    function deleteFileEntries() {
        $('.decrypted-dlc-file').each(function() {
            $(this).remove();
        });
    }

})(this, this.jQuery);