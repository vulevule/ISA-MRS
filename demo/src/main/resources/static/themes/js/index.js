// Plugin options and our code
$("#modal_trigger").leanModal({
		top: 100,
		overlay: 0.6,
		closeButton: ".modal_close"
});
$("#modal_trigger1").leanModal({
		top: 100,
		overlay: 0.6,
		closeButton: ".modal_close"
});

$(function() {
		// Calling Login Form
		$("#login_form").click(function() {
				$(".user_login").show();
				$(".header_title").text('Login');
				return false;
		});

		// Calling Register Form
		$("#register_form").click(function() {
				$(".user_register").show();
				$(".header_title").text('Register');
				return false;
		});

});