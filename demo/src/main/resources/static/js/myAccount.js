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
			if(data.user.role == "VISITOR"){
				var li1 = $("<li> <a href='./reservation/reservation.html'> Add Reservation </a> </li>");
				var li3 = $("<li> <a href='./reservation/reservation.html'> View Reservation </a> </li>");
				var li2 = $("<li> <a href='myFriends.html'> Friends </a> </li>");
				$("#menu_list").append(li1);
				$("#menu_list").append(li3);
				$("#menu_list").append(li2);
				
				var a1 = $("<a href='myFriends.html'> My friends </a>");
				var a2 = $("<a href='myReservations.html'> My reservations </a>");
				
				$("#friend_id").append(a1);
				$("#reservation_id").append(a2);
			}
			
			
			loginUser = data
			var email_lab = $("<label class='control-label'>"+ data.user.email + "</label>")
			$('#email_id').append(email_lab);
			$('#name_id').attr("placeholder", data.user.name);
			$('#surname_id').attr("placeholder", data.user.surname);
			$('#phone_id').attr("placeholder", data.user.phone);
			$('#address_id').attr("placeholder", data.user.address);
			
			
			$('#name_id').val(data.user.name);
			$('#surname_id').val(data.user.surname);
			$('#phone_id').val(data.user.phone);
			$('#address_id').val(data.user.address);
			$('#pass_id').val(data.user.password);
			$('#repeat_pass_id').val(data.user.password);
			
			
			
		}
	});
	
	
	
	
	
	
});


$(document).on('click', "#update_button", function(event){
	
	event.preventDefault();
	
	
	var updateUser = {};
 	updateUser['firstName']= $('#name_id').val();
	updateUser['lastName'] = $('#surname_id').val();
	updateUser['phone'] = $('#phone_id').val();
	updateUser['address'] = $('#address_id').val(); 
	updateUser['password'] = $('#pass_id').val();
	updateUser['repeatPassword'] = $('#repeat_pass_id').val();
	$.ajax({
		type : 'POST',
		url : "../users/updateAccount",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(updateUser),
		success : function(data){
			alert ("User " + data.user.name + " " + data.user.surname + " is successful update");
		},
		error : function(data){
			alert ("Failed update user " + data.message);
		}
	});
	
	
	
});
	
