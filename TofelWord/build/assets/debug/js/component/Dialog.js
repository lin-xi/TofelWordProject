var Dialog = (function($){

    function create(cont, func){
        var uiDialog = $('<div class="ui-dialog"></div>'),
        content = $('<span class="tips-content"></span>'),
        btnArea = $('<div class="btnArea"><a class="ok-btn"><span class="icon ok"></span></a><a class="cancel-btn"><span class="icon delete"></span></a></div>'),
        triangle = $('<span class="triangle4"></span>'),

        mask = $('<div class="ui-mask"></div>');

        content.append(btnArea);
        uiDialog.append(triangle);
        uiDialog.append(content);
        content.append( $('<span>').html(cont));

        mask.appendTo($('body'));
        uiDialog.appendTo($('body')).fadeIn();

        var host = $(window),
            w = host.width(),
            h = host.height(),
            tw = uiDialog.outerWidth(),
            th = uiDialog.outerHeight(),
            tleft, ttop;
        tleft = (w-tw)/2;
        ttop = (h-th)/2;

        uiDialog.css({left: tleft, top: ttop});

        btnArea.find('.ok-btn').on('click', function(){
            func();
            mask.fadeOut(1000, function(){
                mask.remove();
            });
            uiDialog.fadeOut(1000, function(){
                uiDialog.remove();
            });
        });
        btnArea.find('.cancel-btn').on('click', function(){
            mask.fadeOut(1000, function(){
                mask.remove();
            });
            uiDialog.fadeOut(1000, function(){
                uiDialog.remove();
            });
        });
    }

    return {
        alert: function(cont, func){
            create(cont, func);
        },
        confirm: function(cont, func){
            create(cont, func);
        }
    }

})(jQuery);