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
			//treba jos da se dodaju dugmici za dostupne akcije
			alert(data.message + " " + data.user.name + " " + data.user.surname);

		},
		error : function(data){
			$('#modal').hide();
			alert("Invalidate username or password");

		}
	})
	
	
}

