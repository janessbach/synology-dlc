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
    
    global.DownloadHandler = DownloadHandler;
    
})(this, this.jQuery);
