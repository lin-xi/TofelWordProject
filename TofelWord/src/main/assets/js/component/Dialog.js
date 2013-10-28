var Dialog = (function($){

    function create(cont, func){
        var uiDialog = $('<div class="ui-dialog"></div>'),
        content = $('<span class="tips-content"></span>'),
        btnArea = $('<div class="btnArea"><a class="ok-btn">确定</a></div>'),
        triangle = $('<span class="triangle4"></span>'),

        mask = $('<div class="ui-mask"></div>');

        content.append(btnArea);
        uiDialog.append(triangle);
        uiDialog.append(content);
        content.append( $('<span>').html(cont));

        mask.appendTo($('body'));
        uiDialog.appendTo($('body'));

        var host = $(window),
            w = host.width(),
            h = host.height(),
            tw = uiDialog.width(),
            th = uiDialog.height(),
            tleft, ttop;
        tleft = (w-tw)/2;
        ttop = (h-th)/2;

        uiDialog.css({left: tleft, top: ttop});

        btnArea.find('.ok-btn').on('click', function(){
            if(func){
                func();
            }
            mask.remove();
            uiDialog.remove();
        });
        btnArea.find('.cancel-btn').on('click', function(){
            mask.remove();
            uiDialog.remove();
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

})(Zepto);