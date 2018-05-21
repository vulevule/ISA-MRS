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
				var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/cinemas/croped.jpg" alt="" /></a></p> ');
				var a1 = $('<a href="product_detail.html" class="title">' + place.name + '</a><br/>');
				var a2 = $('<a href="products.html" class="category">' + place.address + '</a>');
				
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

$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : '../users/exists',
		dataType : 'json',
		async: false,
		success : function(data){
			loginUser = data.user;
		}
	})
	
	if (loginUser != null) {
		$('#modal_trigger').hide();
		$('#modal_trigger1').hide();
		$('#myAccount').show();
		$('#log-out').show();
		if (loginUser.type == "SYSTEM_ADMIN") {
			$('#modal_trigger_place').show();
			$('#modal_trigger_projection').hide();
		}
	}
	
	showPlaces();});
