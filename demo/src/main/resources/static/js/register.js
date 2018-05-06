$(document).ready(function(){
	$("#register_button").click( function(event){
		event.preventDefault();
		
		registration_submit();
	});
});


function registration_submit(){
	
	var user = {};
	user["email"] = $("#email").val();
	user["password"] = $("#password_reg").val();
	user["address"] = $("#address").val();
	user["name"] = $("#name").val();
	user["surname"] = $("#surname").val();
	user["phone"] = $("#phone").val();
	user["type"] = "VISITOR";
	
	$.ajax({
		type : "POST",		
		url : "../users/registrationUser",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
		},
		error : function(XMLHttpRequest, Textstatus, Errorthrown){
			console.log("ajax error: " + Errorthrown + ", status: " + Textstatus);
		}
	})
	
	
}