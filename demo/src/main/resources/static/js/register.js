
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
	user["firstName"] = $("#firstName").val();
	user["lastName"] = $("#lastName").val();
	user["phone"] = $("#phone").val();
	user["repeatPassword"] = $("#repeat_pass").val();
	user["type"] = "VISITOR";
	
	$.ajax({
		type : "POST",		
		url : "../users/registrationUser",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
			$("#modalR").hide();
		},
		error : function(XMLHttpRequest, Textstatus, Errorthrown){
			console.log("ajax error: " + Errorthrown + ", status: " + Textstatus);
		}
	})
	
	
}