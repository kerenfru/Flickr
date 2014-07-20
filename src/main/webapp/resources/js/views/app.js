var app = app || {};

(function($) {
	app.AppView = Backbone.View.extend({
		el : '#container',

		events : {
			"click .shuffle" : "shuffle",
			"click .loadFlickr" : "loadFlickr"
		},
		initialize : function() {
			this._MyElementViews = []; // view chache for further reuse
			this.listenTo(app.Images, 'reset', this.reset);
			this.listenTo(app.Images, 'change', this.addAll);
			this.listenTo(app.Images, 'add', this.addOne);
			this.listenTo(app.Images, 'all', this.render);
			app.Images.fetch();
			console.log('initialization');

		},
		reset : function() {
			console.log("reset");
			this.addAll();
		},
		render : function() {
			this.showOneafterAnother();
		},
		addOne : function(image) {
			var view = new app.ImageView({
				model : image
			});
			this._MyElementViews.push(view);
			$('#photos').append(view.render().el);
		},
		addAll : function() {
			console.log("addall");
			this.$('#photos').html('');
			app.Images.each(this.addOne, this);
			this.showOneafterAnother();
		},
		shuffle : function() {
			console.log("shuffle");
			// this._MyElementViews.each(this.shuffle1, this);
			// _.each(this._MyElementViews, this.shuffle1);
		},
		showOneafterAnother : function() {
			setTimeout(function() {
				$('ul.polaroids .polaroid').each(function(i) {
					// alert(i);
					$(this).delay((i++) * 300).fadeIn("slow");
				});
			}, 300);
		},
		loadFlickr : function() {
			app.search = null;
			$('h1').text('Flickr Photos!');
			app.Images.reset();
			app.Images.fetch();
		}
	});
})(jQuery);
