(function($){
	var cache = {}, events = {};
	cache.process = false;

	function M(){
	}

	M._navigatePage = function(pageName, param){
		var g = cache;
		var app = $('.app-box'),
			cur = $(g.current),
			page = $(pageName);

		if(cur == pageName) return;

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
				M.emit('navigate', pageName, param);
			});

			setTimeout(function(){
				g.process = false;
			}, 1000)
		}
	};

	M.navigate = function(link){
        location.href = location.href.split('#')[0] + link;
	};

	M.setHead = function(obj){
		var g = cache;
		$('.page-header h3').html(obj.title);
		if(obj.leftIcon){
			$('.page-header i:first-child').removeClass().addClass(obj.leftIcon);
			$('.page-header a:first-child').attr('href', obj.leftHref);
		}
		if(obj.rightIcon){
			$('.page-header i:last-child').removeClass().addClass(obj.rightIcon);
			$('.page-header a:last-child').attr('href', obj.rightHref);
		}
	};

	M.setFoot = function(html, fn){
		var context = $('.page-foot');
		$(context.children()[0]).hide();
		context.append(html);
		if(fn){
			fn(context);
		}
	};
	M.resetFoot = function(){
		var context = $('.page-foot');
		$.each(context.children(), function(index, item){
			if(index>0){
				$(item).remove();
			}else{
				$(item).show();
			}
		});
	}

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

		var max = $(window).height();
		$('.page-content').height(max-$('.page-header').height()-$('.page-foot').height());

		g.current = '#index';
		M.navigate(g.current);
	}

	function _initPageHeader(){
		$('.page-header').delegate('a', 'click', function(e){
			var tar = e.currentTarget;
			var href = $(tar).attr('href');
			M.navigate(href);
			e.preventDefault();
			e.stopPropagation();
		});
	}

	function _initPageFoot(){
		var menu = $('.menu'), indicator = $('.indicator');

		menu.delegate('.menu-item', 'click', function(e){
			var tar = $(e.currentTarget), pos = tar.offset();
			indicator.animate({left:pos.left-5}, 1000, 'swing');
		});

	};


	$('body').ready(function(e){
		_initPageHeader();
		_initPageFoot();
		_init();
	});

	window.addEventListener('hashchange', function(){
		console.log(location.hash);
		M._navigatePage(location.hash);
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