(function($){
	var cache = {};

	function M(){
	}

	M.navigate = function(pageName){
		var g = cache;
		var app = $('.app-box'),
			cur = $(g.current),
			page = $(pageName);

		var width = cur.width();
		page.css('-webkit-transform', 'translate('+width+'px, 0px)');
		page.show();
		
		g.current = pageName;
		cur.animate({translate: '-'+width+'px, 0'}, 1000, 'ease', function(){
			cur.hide();
		});
		
		page.animate({translate: '0px, 0px'}, 1000, 'ease', function(){
		});
		

	};

	M.render = function(template, data){
        return template.replace(/\{\{([^\}\}]+)\}\}/g, function(s0, s1){
            return (data[s1]==undefined || data[s1]==null) ? '' : data[s1];
        });
	};

	function _init(){
		var g = cache;
		var pbs = $('.page-box'), len = pbs.length;
		g.current = '#'+pbs.eq(0).get(0).id;
		$(g.current).show();
	}

	function _initPageHeader(){
		$('.page-header').each(function(index, item){
			$(item).find('a').on('click', function(e){
				var tar = e.currentTarget;
				if(tar.nodeName.toLowerCase() == 'a'){
					var href = $(tar).attr('href');
					M.navigate(href);
					e.preventDefault();
					e.stopPropagation();
				}
			});
		});
	}

	$('body').ready(function(e){
		_init();
		_initPageHeader();
	});

	window.M = M;
})(Zepto);