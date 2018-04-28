$(document).ready(function(){
	$("#loginForm").submit(function(event){
		event.preventDefault();
		
		login_submit();
	});
});


function login_submit(){
	
	var login = {}
	login["username"] = $("#username").val();
	login["password"] = $("#password").val();
	
	$.ajax({
		type : "POST",
		cont
		url : "users/loginUser",
		data : JSON.stringify(login),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data){
			var json = "<p>" + JSON.stringify(data, null, 4) + "</p>";
			$("#loginForm").html(json);
		}
	})
	
	
}