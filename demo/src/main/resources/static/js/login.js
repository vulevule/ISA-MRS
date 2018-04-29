$(document).ready(function(){
	$("#login_button").submit(function(event){
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
		
		url : "users/loginUser",
		data : JSON.stringify(login),
		dataType : 'application/json',
		cache : false,
		timeout : 600000,
		success : function(data){
			$("#model").hidden();
		}
	})
	
	
}