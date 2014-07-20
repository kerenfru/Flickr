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
					// details animation
					var $that = this.$el;
					
					if (!this.$el.find('.details').hasClass('showDetails')) {
						// flip polaroid
						$that.find('.polaroid').addClass('flipOutY animated');
	
						// wait for half animation to be done
						setTimeout(function() {
							$that.find('.polaroid').removeClass('flipOutY animated');
							$that.find('.polaroid').addClass('flipInY animated');
							$that.find('.details').addClass('showDetails');
							$that.find('img').addClass('hideImg');
						},600);
					}
					else {
						$that.find('.polaroid').addClass('flipOutY animated');
						
						setTimeout(function() {
							$that.find('img').removeClass('hideImg');
							$that.find('.details').removeClass('showDetails');
							$that.find('.polaroid').removeClass('flipOutY animated');
							$that.find('.polaroid').addClass('flipInY animated');
						},600);
					}
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
