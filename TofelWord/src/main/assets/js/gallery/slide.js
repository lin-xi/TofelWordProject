var Slide = (function(argument) {

	var IMG_WIDTH = 76, 
		IMG_HEIGHT = 60, 
		MARGIN = 20,
		EXTRA_HEIGHT = 120;


	function Slide(node, pagesize, data){

		this.cache = {};
		var cache = this.cache; //缓存
		cache.current = 0; //当前显示的图片index
		cache.total = 0;  //图片总数
		
		this.node = $(node);
		this.data = data;
		this.pageSize = pagesize || 3;

		//初始化
		this._init();
	}

	$.extend(Slide.prototype, {
		//初始化
		_init: function(){

			var h_template = '<div class="ui-gallerySlide"><div class="gs-horizontal"><span class="content-wraper"><span class="gsContent"></span></span></div><div class="gsPage"></div></div>';
			var self = this, node = self.node, cache = self.cache, data = self.data;
			
			var gs, sc, cnt, vw = node.width(), vh, imgW, imgH;

			vh = IMG_HEIGHT + EXTRA_HEIGHT;
			node.height(vh);
			
			//横向模式
			gs = $(h_template);
			gs.appendTo(node);
			sc = $('.content-wraper', gs);
			cnt = $('.gsContent', gs);

			sc.css({left: 0, top: 0});

			var tw = vw, th = vh-EXTRA_HEIGHT;
			//cnt.height(IMG_HEIGHT);
			imgH = vh;      //图片外围高度
			imgW = vw/3;    //图片外围宽度

			cache.totalCount = Math.floor(tw/imgW);  //页面中显示缩略图的总数
			var totalWidth = cache.totalCount*imgW;  //总宽度

			cache.current = 0; //当前图片 索引
			cache.imgW = imgW;
			cache.imgH = imgH;

			var page_template = '<span class="page-item"></span>',
				pageWraper = $('<span class="page-Wraper"></span>'),
				father = $('.gsPage', node);
			var totalPage = Math.ceil(data.length/self.pageSize);

			cnt.css('width', totalPage*100 + '%');
			self.totalPage = totalPage;

			for(var i=0; i<totalPage; i++){
				var p = $(page_template).attr('index', i);
				if(i==0){
					p.addClass('on');
				}
				p.appendTo(pageWraper);
			}
			father.append(pageWraper);

			pageWraper.on('click', function(e){
				var tar = $(e.target);
				if(tar.hasClass('page-item')){
					var idx = tar.attr('index')-0;
					cache.current = idx;

					self.render();
				}
			});

			var template = '<span class="gsImageWraper"><img class="gsImage" id="{id}" src="{path}"/><p class="gsName">{name}</p><a class="btn btn-green btn-small">试用</a></span>';
			var frag = document.createDocumentFragment();
			var pageUl = $('<ul class="page-ul"></ul>');

			for (var i=0, ii=data.length; i<ii; i++) {
				var obj = data[i];
				var imgWrap = template.replace(/\{([^\}]+)\}/g, function(s0, s1){
					return obj[s1] || '';
				});
				imgWrap = $(imgWrap);
				imgWrap.attr('dataIndex', i);
				imgWrap.css('width', 100/self.pageSize+'%');
				imgWrap.css('height', cache.imgH);

				var img = $('.gsImage', imgWrap);
				img.width(IMG_WIDTH);
				img.height(IMG_HEIGHT);
				imgWrap.appendTo(pageUl);

				if(i && (i+1)%self.pageSize == 0){
					pageUl.css('width', 100/totalPage +'%');
					pageUl.appendTo(frag); 
					pageUl = $('<ul class="page-ul"></ul>');
				} else if(i == ii-1){
					pageUl.css('width', 100/totalPage +'%');
					pageUl.appendTo(frag); 
				}
			}
			var contentNode = $('.gsContent', node);
			contentNode.append(frag);

			gs.swipeLeft(function(e){
				self.next();
			});
			gs.swipeRight(function(e){
				self.prev();
			});

			self.render();
		},

		//渲染
		render: function(){
			var self = this, g = self.cache, data = self.data;
			
			self._scrollToCenter();
			self._scrollIndicator();
		},
		
		//图片滚动至中心位置
		_scrollToCenter: function (){
			var self = this, node = self.node, g = self.cache, data=self.data,
				ele = $('.gsContent', node);

			//水平方向滚动
			ele.animate({left: -g.current*100+'%'}, 500, 'ease-in-out');
		},

		_scrollIndicator: function(){
			var self = this, node = self.node, g = self.cache;
			$('.page-item').each(function(index, item){
				if(index == g.current){
					$(item).addClass('on');
				}else{
					$(item).removeClass('on');
				}
			});
		},

		//下一页
		next: function(){
			var self = this, cache = self.cache, data=self.data;
			if(cache.current < self.totalPage-1){
				cache.current++;
				self.render();
			}
		},
		
		//上一页
		prev: function(){
			var self = this, cache = self.cache, data=self.data;
			if(cache.current > 0){
				cache.current--;
				self.render();
			}
		}

	});

	return Slide;

})();