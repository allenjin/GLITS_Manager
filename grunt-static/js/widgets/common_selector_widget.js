;(function ($) {
    $.CommonSelector = function (options, element) {
        this.$el = $(element);
        this._init(options);
    };
    $.CommonSelector.defaults = {
        data: [],
        id_fun: function (item) {
            return item.id;
        },
        val_fun: function (item) {
            return item.name;
        },
        callback: function(item){
        },
        isSingle: true,
        title: '提示',
    };
    $.CommonSelector.prototype = {
        _init: function (options) {
            this.options = $.extend(true, {}, $.CommonSelector.defaults, options);
            this._config();
            this._initEvent();
        },
        _config: function () {
            this.$sModal = $('<div class="modal fade" tabindex="-1" role="dialog"></div>').appendTo($('body'));
            this.$modalDialog = $('<div class="gl-modal modal-dialog" role="document"></div>').appendTo(this.$sModal);
            this.$modalContent = $('<div class="modal-content"></div>').appendTo(this.$modalDialog);
            this.$modalHeader = $('<div class="modal-header"><h4 class="modal-title">' + this.options.title + '</h4></div>').appendTo(this.$modalContent);
            this.$modalBody = $('<div class="modal-body"></div>').appendTo(this.$modalContent);
            //this.$modalFooter = $('<div class="modal-footer"></div>').appendTo(this.$modalContent);
            this.$modalBody.append('<form><div class="input-group"><span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span><input type="text" class="form-control" id="inputSearch"></div></form>');
            this.$searchInput = this.$sModal.find('#inputSearch');
            this.$resultWrapper = $('<div class="selector-result"></div>').appendTo(this.$modalBody);
            this.$resultList = $('<div class="list-group"></div>').appendTo(this.$resultWrapper);
            this._reset();
        },
        _createItems: function (data) {
            var self = this;
            $.each(data, function (index, item) {
                self.$resultList.append('<a href="#" class="list-group-item" data-id="' +
                    self.options.id_fun(item) + '">' +
                    self.options.val_fun(item) + '</a>');
            });
        },
        _initEvent: function () {
            var self = this;
            this.$el.on('click', function (event) {
                event.preventDefault();
                self.$sModal.modal();
                self.$searchInput.focus();
            });
            this.$sModal.find('#inputSearch').keyup(function () {
                var value = $(this).val();
                if (value.length > 0) {
                    self._filter(value);
                } else {
                    self._reset();
                }
            });
            this.$resultList.on('click', 'a.list-group-item', function (e) {
                e.preventDefault();
                var itemId = $(this).attr('data-id');
                var itemVal = $(this).text();
                self.options.callback({id: itemId, val: itemVal});
                self.$sModal.modal('hide');
            });

        },
        _filter: function (value) {
            this.$resultList.find('a.list-group-item').each(function () {
                var itemVal = $(this).text();
                if (itemVal.indexOf(value) < 0) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });
        },
        _reset: function () {
            this.$resultList.empty();
            this._createItems(this.options.data);
        }
    };

    $.fn.CommonSelector = function (options) {
        if (typeof options == 'string') {
            var args = Array.prototype.slice.call(arguments, 1);
            this.each(function () {
                var instance = $.data(this, 'CommonSelector');
                if (!instance) {
                    glLogger("cannot call methods on CommonSelector prior to initialization; " +
                        "attempted to call method '" + options + "'", 'error');
                    return;
                }
                if (!$.isFunction(instance[options]) || options.charAt(0) === "_") {
                    glLogger("no such method '" + options + "' for BaysPicker instance", 'error');
                    return;
                }
                instance[options].apply(instance, args);
            });
        } else {
            this.each(function () {
                var instance = $.data(this, 'CommonSelector');
                if (instance) {
                    instance._init();
                } else {
                    $.data(this, 'CommonSelector', new $.CommonSelector(options, this));
                }
            });
        }
        return this;
    };
})(jQuery);