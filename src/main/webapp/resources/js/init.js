$('.fancybox').fancybox();

$(document).foundation();

$(document).mousemove(function(e) {
	$('#hand').offset({
		left : e.pageX - 130,
		top : e.pageY + 10
	});
});

// animate the title
setInterval(
		function() {
			$('h1')
					.removeClass()
					.addClass('hinge animated')
					.one(
							'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
							function() {
								$(this).removeClass();
							})
		}, 18000);