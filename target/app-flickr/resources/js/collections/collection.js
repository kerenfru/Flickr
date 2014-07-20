var app = app || {};

(function () {
	
	var ImagesList = Backbone.Collection.extend({
		model: app.Image,
		url: function(){
		    var url = 'http://localhost:8080/app-flickr/feed/';
			if (app.search) {
		      url = url + "author/" + app.search;
		      
		    }
			console.log('url is: '+ url);
		    return url;
		}
	});
	
	app.Images = new ImagesList();
	
})();
