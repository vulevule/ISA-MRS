/**
 * 
 */
var loginUser;

$(document).ready(function(){
	
	$.ajax({
		type : 'GET',
		url : '../users/exists',
		dataType : 'json',
		success : function(data){
			loginUser = data.user;
		}
	})
	
	/*
	
	$('#name_id').attr("placeholder", loginUser.name);
	$('#surname_id').attr("placeholder", loginUser.surname);
	$('#phone_id').attr("placeholder", loginUser.phone);
	$('#address_id').attr("placeholder", loginUser.address);
	
	$('#name_id').val(loginUser.name);
	$('#surname_id').val( loginUser.surname);
	$('#phone_id').val(loginUser.phone);
	$('#address_id').val(loginUser.address);*/
});


$("#update_button").click(function(event){
	var updateUser = {};
	updateUser['email'] = loginUser.email;
 	updateUser['name']= $('#name_id').val();
	updateUser['surname'] = $('#surname_id').val();
	updateUser['phone'] = $('#phone_id').val();
	updateUser['address'] = $('#address_id').val(); 
	var password = $('#pass_id').val();
	var repeat_pass = $('#repeat_pass').val();
	
	
	
	if (password == "" ){
		updateUser['password'] = password;
		updateUser['repeatPassword'] = repeat_pass;
	}
	
	$.ajax({
		type : 'POST',
		url : "../users/updateUser",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(updateUser),
		success : function(data){
			alert ("User " + data.user.name + " " + data.user.surname + " is successful update");
		},
		error : function(data){
			alert ("Failed update user");
		}
	})
	
	
	
});
	
