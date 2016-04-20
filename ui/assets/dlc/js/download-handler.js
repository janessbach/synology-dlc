/**
 * DEPENDENCIES: global
 */
(function (global, $) {
    'use strict';

    var _opts = {
        'uploadUrl': '/api/dlc/decrypt',
        'startDownloadUrl': '/api/downloads/add'

    };

    function DownloadHandler(opts) {
        _opts = $.extend(_opts, opts);
    }

    DownloadHandler.prototype.decryptFile = function (formData, succesFn) {
        $.ajax({
            url: _opts.uploadUrl,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            dataType: 'json',
            success: function (data) {
                succesFn(data);
            }
        });
    };

    DownloadHandler.prototype.startDownload = function (formData) {
        $.ajax({
            url: _opts.startDownloadUrl,
            type: 'POST',
            data: formData,
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                console.log(data);
            }
        })
    };


    global.DownloadHandler = DownloadHandler;
    
})(this, this.jQuery);
