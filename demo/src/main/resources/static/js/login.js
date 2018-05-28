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
		
			alert(data.message + " " + data.user.name + " " + data.user.surname);
			if(data.user.role == "VISITOR"){
						
				var li_friend = $('<li> <a href="./myFriends.html" id="friend_list"> Friend </a> <ul id="friend_items"> </ul> </li>');
				
				$("#menu_list").append(li_friend);
				
			}else if (data.user.type == "FAN_ZONE_ADMIN"){
				var li_props = $('<li> <a href="./thematicProps.html" id="props_list"> Props </a> <ul id="props_items"> </ul> </li>');
				$("#menu_list").append(li_props);
			}else if (data.user.type == "CINEMA_THEATER_ADMIN"){
				var li_place = $('<li> <a href="./myFriends.html" id="props_list"> Place </a> <ul id="props_items"> </ul> </li>');
				$("#menu_list").append(li_place);

			}
		},
		error : function(data){
			$('#modal').hide();
			alert("Invalid username or password");

		}
	})
	
	
}

