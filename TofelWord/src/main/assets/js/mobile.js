(function($){
	var cache = {}, events = {};
	cache.process = false;

	function M(){
	}

	M.navigate = function(pageName, param){
		var g = cache;
		var app = $('.app-box'),
			cur = $(g.current),
			page = $(pageName);
		
		if(!g.process){
			g.process = true;

			var width = cur.width();
			page.css('-webkit-transform', 'translate('+width+'px, 0px)');
			page.show();
		
			g.current = pageName;
			cur.animate({translate: '-'+width+'px, 0'}, 1000, 'ease', function(){
				cur.hide();
			});
		
			page.animate({translate: '0px, 0px'}, 1000, 'ease', function(){
				g.process = false;
				M.emit('navigate', pageName, param);
			});

	        var url = location.href.split('#')[0] + pageName;
			var state = {
	        	title: pageName,
	        	url: url
	        };
	        history.pushState(state, pageName, url);
	    }
	};

	M.setTitle = function(title){
		var g = cache;
		var app = $('.app-box'),
			cur = $(g.current);

		cur.find('.page-header h3').html(title);
	};

	M.render = function(template, data){
        return template.replace(/\{\{([^\}\}]+)\}\}/g, function(s0, s1){
            return (data[s1]==undefined || data[s1]==null) ? '' : data[s1];
        });
	};

	M.on = function(type, fn){
		if(!events[type]){
			events[type] = [];
		}
		var handlers = events[type];
		handlers.push(fn);
	};

	M.emit = function(type){
		var args = Array.prototype.slice.call(arguments, 1);
		var fn = events[type];
		if(fn){
			var i=0; len=fn.length;
			for(; i<len; i++){
				fn[i].apply(this, args);
			}
		}
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

	function _initPageFoot(){
		var max = $(window).height();
		$('.page-content').height(max-$('.page-header').height()-$('.page-foot').height());

		var menu = $('.menu'), indicator = $('<div class="indicator"></div>');
		menu.parent().append(indicator);

		menu.delegate('.menu-item', 'click', function(e){
			var tar = $(e.currentTarget), pos = tar.offset();
			indicator.animate({left:pos.left-5}, 1000, 'swing');
		});

	};


	$('body').ready(function(e){
		_init();
		_initPageHeader();
		_initPageFoot();
	});

	window.addEventListener('popstate', function(e){
  		if (history.state){
    		var state = e.state;
    		if(state && state.title){
    			M.navigate(state.title);
    		}
  		}
	}, false);

	window.M = M;
})(Zepto);