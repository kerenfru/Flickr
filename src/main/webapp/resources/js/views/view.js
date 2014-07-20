var app = app || {};

(function() {
	app.ImageView = Backbone.View
			.extend({
				tagName : 'li',
				template : _.template($('#image-template').html()),

				events : {
					"click .detailsButton" : "details",
					"click .delete" : "remove",
					"click .author" : "author"
				},
				initialize : function() {
					//this.listenTo(this.model, 'change', this.render);
				},
				render : function() {
					this.$el.html(this.template(this.model.toJSON()));
					return this;
				},
				details : function() {
					// show the details
					this.$el.find('.details').toggleClass('showDetails');
					this.$el.find('img').toggleClass('hideImg');
				},
				remove : function() {
					// disappearing the image
					this.$el.animate({
						'top' : '0px',
						'left' : '0px'
					}, 100, function() {
						$(this).remove();
					});
				},
				author : function() {
					// show gallery of the selected author
					app.search = this.model.toJSON().author['flickr:nsid'];
					$('h1').text(this.model.toJSON().author.name + ' Photos!');
					app.Images.reset();
					app.Images.fetch();
				}
			});
})(jQuery);
