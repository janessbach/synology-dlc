/**
 * DEPENDENCIES: global
 */
(function (global, $) {
    'use strict';

    var _opts = {
        'checkboxToggleSelector' : '.checkbox-toggle',
        'parentSelector': undefined
    };

    function CheckboxToggle(opts) {
        _opts = $.extend(_opts, opts);
    }

    CheckboxToggle.prototype.enable = function() {
        $(_opts.checkboxToggleSelector).on('click', function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                $(_opts.parentSelector + ' input[type=checkbox]').iCheck('uncheck');
                $('.fa', this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                $(_opts.parentSelector + ' input[type=checkbox]').iCheck('check');
                $('.fa', this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data('clicks', !clicks);
        });
    };

    global.CheckboxToggle = CheckboxToggle;
})(this, this.jQuery);