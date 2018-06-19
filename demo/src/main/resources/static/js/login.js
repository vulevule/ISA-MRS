$(document).ready(function(){
	$("#login_button").click( function(event){
		event.preventDefault();
		
		login_submit();
	});
});

function login_submit(){
	
	var login = {};
	login["username"] = $("#username").val();
	login["password"] = $("#password").val();
	
	$.ajax({
		type : "POST",		
		url : "../users/loginUser",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(login),
		success : function(data){
			$('#modal').hide();
			$('#myAccount').show();
			$('#log-out').show();
			$('#modal_trigger').hide();
			$('#modal_trigger1').hide();
			
			location.reload();
			
		},
		error : function(data){
			$('#modal').hide();
			alert("Invalid username or password");
		}
	})
}

