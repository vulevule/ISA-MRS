$(document).ready(function(){
	$("#activate_button").click( function(event){
		event.preventDefault();
		
		activate_submit();
	});
});


function activate_submit(){

	var activate = {};
	login["email"] = $("#email_id").val();
	login["activateAccount"] = $("#activate_id").val();
	
	$.ajax({
		type : "POST",		
		url : "../users/activateAccount",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(activate),
		success : function(data){
			$(location).attr('href', 'http://localhost:8080');
		},
		error : function(data){

		}
	})
}