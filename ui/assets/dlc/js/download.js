/**
 * DEPENDENCIES: global
 */
(function (global, $) {
    'use strict';

    var _opts = {
        'uploadUrl': '/api/decrypt'
    };

    function DownloadHandler(opts) {
        _opts = $.extend(_opts, opts);
    }

    DownloadHandler.prototype.decryptFile = function (formData) {
        $.ajax({
            url: _opts.uploadUrl,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            dataType: 'json',
            success: function (data) {
                parse(data);
            }
        });
    };
    
    DownloadHandler.prototype.parse = function (data) {
        var self = this;
        data.forEach(function (element) {
            self.createFileEntry(element);
        });    
    };
    
    DownloadHandler.prototype.createFileEntry = function () {
        var tableRow = $('<tr/>')
            .append('<td><input type="checkbox" class="icheckbox_flat-blue"></td>')
            .append('<td><span class="label label-warning">Checking</span></td>')
            .append('<td>' + element.name + '</td>')
            .append('<td>' + element.url + '</td>');

        $('tbody').append(tableRow);
    };

    global.DownloadHandler = DownloadHandler;
    
})(this, this.jQuery);
