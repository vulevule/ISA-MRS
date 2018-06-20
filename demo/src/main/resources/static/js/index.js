var loginUser;

function showPlaces() { 
	$.ajax({
		type : "GET",		
		url : "../places",
		dataType : "json",
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$('#cinema_theater').empty();
			
			$.each(list, function(index, place) { 
				var li = $('<li class="span3"> </li>');
				var div4 = $('<div class="product-box" id="cinema_theater"> </div>');
				var span1 = $('<span class="sale_tag"> </span>');
				var p1 = $('<p><a href="place.html?id=' + place.id + '"><img src="./themes/images/cinemas/croped.jpg" alt="" /></a></p> ');
				var a1 = $('<a href="place.html?id=' + place.id + '" class="title">' + place.name + '</a><br/>');
				var a2 = $('<a href="place.html?id=' + place.id + '" class="category">' + place.address + '</a>');
				
				div4.append(span1);
				div4.append(p1);
				div4.append(a1);
				div4.append(a2);
				li.append(div4);
				$('#cinema_theater').append(li);
			});
			
			$('#place_list').show();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	})
}

function showReservation(){
	$.ajax({
		type : "GET",		
		url : "../reservation",
		dataType : "json",
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$('#my_reservations').empty();
			
			$.each(list, function(index, r) { 
				var li = $('<li class="span3"> </li>');
				var div4 = $('<div class="product-box" id="my_reservation"> </div>');
				var span1 = $('<span class="sale_tag"> </span>');
				var p1 = $('<p class="title"> Projection name: ' + r.term.projection.name + ' <br/> Place: ' + r.term.projection.place.name + '</p> ');
				var a1 = $('<p> Date: ' + r.term.projectionDate + ' Time: ' + r.term.projectionTime +  '</p><br/>');
				var a2 = $('<p> Row: ' + r.row + ' Seat: ' + r.seatNum + '</p>');
				var a3 = $('<form> <input type="button" id='+ r.id + ' class="btn" value="Cancel" onclick="deleteRes(this)" >'+
						' </form>' );
						
				div4.append(span1);
				div4.append(p1);
				div4.append(a1);
				div4.append(a2);
				div4.append(a3);
				li.append(div4);
				$('#my_reservations').append(li);
			});
			
			$('#reservation_list').show();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	})
	
	
}

function deleteRes(button){
	alert(button.id);
	
	var id = button.id;
	$.ajax({
		type : "GET",		
		url : "../reservation/cancelReservation?id=" + id,
		contentType : "application/json",
		success : function(data){
			alert(data.message);
			location.reload();
			
		},
		error : function(data){
			alert("Reservation can not be canceled.");
			location.reload();

		}
	})
}



$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : '../users/exists',
		dataType : 'json',
		async: false,
		success : function(data){
			loginUser = data.user;
			
			if (loginUser != null) {
				$('#modal_trigger').hide();
				$('#modal_trigger1').hide();
				$('#myAccount').show();
				$('#log-out').show();
				if (loginUser.role == "SYSTEM_ADMIN") {
					$('#modal_trigger_place').show();
					$('#modal_trigger_projection').hide();
				}else if (loginUser.role == "VISITOR"){
					//dodati u meni friends i reservation kartice
					var li1 = $("<li> <a href='./reservation/reservation.html'> Add Reservation </a> </li>");
					var li2 = $("<li> <a href='myFriends.html'> Friends </a> </li>");
					$("#menu_list").append(li1);
					$("#menu_list").append(li2);
					
					showReservation();
					
				}
			}
		}
	})
	
	showPlaces();});
