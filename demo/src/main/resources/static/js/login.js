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
			var a = $('<a id="my_account" href="" class="btn">' + data.user.email + " </a> ");
			var script = $('<script src="js/myAccount.js"> </script>');
			$('body').append(script);
			$("#account").append(a);
			$('#register_button').hide();
			$('#login_button').attr('id', 'sign_out_button');
			$('#sign_out_button').attr('value', 'Sign out');
			alert(data.message + " " + data.user.name + " " + data.user.surname);
			if(data.user.type == "VISITOR"){
				var li_props = $('<li> <a href="#" id="props_list"> Thematic Props </a> <ul id="items_props"> </ul> </li>');
				var item_props1 = $('<li><a href="#" id="add_props">Add</a></li>'); 								
				var item_props2= $('<li><a href="#" id="change_props">Change</a></li>'); 
				var item_props3= $('<li><a href="#">Remove</a></li>');
				
				var li_reservation = $('<li> <a href="#" id="reservation_list"> Reservation </a> <ul id="items_reservation"> </ul> </li>'); 
				var item_res1 = $('<li><a href="#" id="add_props">Add</a></li>'); 								
				var item_res2 = $('<li><a href="#" id="change_props">Cancel</a></li>'); 
				var item_res3 = $('<li><a href="#">List</a></li>');
				
				var li_ad= $('<li> <a href="#" id="ad_list"> Ad </a> <ul id="items_ad"> </ul> </li> '); 
				var ad_item1=$('<li><a href="#" id="add_props">Add</a></li>'); 								
				var ad_item2=$('<li><a href="#" id="change_props">Bids</a></li>');
				var ad_item3=$('<li><a href="#">List</a></li>');
				
				var li_mark = $('<li> <a href="#" id="mark_list"> Mark </a> <ul id="items_mark"> </ul> </li>'); 
				var item_mark1=$('<li><a href="#" id="add_props">Cinema/Theater</a></li>'); 								
				var item_mark2=$('<li><a href="#" id="change_props">Projection</a></li>');
				
				var li_friend = $('<li> <a href="#" id="friend_list"> Friend </a> <ul id="friend_items"> </ul> </li>');
				var friend_item1=$('<li><a href="#" id="add_props">Add</a></li>'); 								
				var friend_item2=$('<li><a href="#" id="change_props">Delete</a></li>'); 
				var friend_item3=$('<li><a href="#">List</a></li>');
				
				$("#menu_list").append(li_friend);
				$("#friend_items").append(friend_item1);
				$("#friend_items").append(friend_item2);
				$("#friend_items").append(friend_item3);

				
				$("#menu_list").append(li_mark);
				$("items_mark").append(item_mark1);
				$("items_mark").append(item_mark2);
				
				$("#menu_list").append(li_reservation);
				$("#items_reservation").append(item_res1);
				$("#items_reservation").append(item_res2);
				$("#items_reservation").append(item_res3);

				
				$("#menu_list").append(li_ad);
				$("#items_ad").append(ad_item1);
				$("#items_ad").append(ad_item2);
				$("#items_ad").append(ad_item3);

				$("#menu_list").append(li_props);
				$("#items_props").append(item_props1);
				$("#items_props").append(item_props2);
				$("#items_props").append(item_props3);

				
			}
		},
		error : function(data){
			$('#modal').hide();
			alert("Invalidate username or password");

		}
	})
	
	
}

