
		
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
		}
	})
	
	renderFriends();
	renderRequest();
	renderAllVisitor();//ovo nam ne treba ovde, izbrisacemo ga
	
	


	
})

function renderAllVisitor(){
	
	$.ajax({
		type : 'GET',
		url : "../users/allNotFriends",
		dataType : "json",
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			
			var div = $('<div class="span12"> </div>');
			var title = $('<h4 class="title">'+
					'<span class="pull-left"><span class="text"><span class="line">Add friends</span></span></span>'+
					'<span class="pull-right">'+
						'<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>'+
					'</span></h4>');
			var div1 = $('<div id="myCarousel" class="myCarousel carousel slide"> </div>');
			var div2 = $('<div class="carousel-inner"> </div>');
			var div3 = $('<div class="active item"> </div> ');
			/*var ul = $('<ul class="thumbnails" id="add_friend">	</ul>');*/
			
			$.each(list, function(index, user) {
				var newOption = $("<option></option>");
				alert(user.name);
				newOption.attr("value",user.username).text(user.name + " " + user.surname);
				$("#add_friends").append(newOption);
				/*	var li = $('<li class="span3"> </li>');
					var div4 = $('<div class="product-box" id="friends"> </div>');
					var span1 = $('<span class="sale_tag"> </span>');
					var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
					var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname + ' </a><br/>');
					var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
					var a3 = $('<form id="send_request"> <input type="button" class="btn" id="send_btn" value="Send request">' + 
							'<input type="hidden" name="username" id="username_accept" value=' + user.email +'> </form>' );
					
					
					div4.append(span1);
					div4.append(p1);
					div4.append(a1);
					div4.append(a2);
					div4.append(a3);
					li.append(div4);
					ul.append(li);*/
			})
			
			
		//zavrsiti for petlju
			div2.append(div3);
			div1.append(div2);
			div.append(title);
			div.append(div1);
			
			$('#add_friends_list').append(div);
		}
	});
	
}

$(document).on('click', '#send_request', function(event){
	var user = {};
	user['email'] = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : "POST",		
		url : "../users/addFriend",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
		},
		error : function(data){
			alert(data.message);

		}
	});
});


function renderRequest(){
	$.ajax({
		type : 'GET',
		url : "../users/allFriendshipRequest",
		dataType : "json",
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var div = $('<div class="span12"> </div>');
			var title = $('<h4 class="title">'+
					'<span class="pull-left"><span class="text"><span class="line">Friendship request</span></span></span>'+
					'<span class="pull-right">'+
						'<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>'+
					'</span></h4>');
			var div1 = $('<div id="myCarousel" class="myCarousel carousel slide"> </div>');
			var div2 = $('<div class="carousel-inner"> </div>');
			var div3 = $('<div class="active item"> </div> ');
			var ul = $('<ul class="thumbnails" id="requests">	</ul>');
			
			
			$.each(list, function(index, user) {
				
				var li = $('<li class="span3"> </li>');
				var div4 = $('<div class="product-box" id="friends"> </div>');
				var span1 = $('<span class="sale_tag"> </span>');
				var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
				var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname + ' </a><br/>');
				var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
				var a3 = $('<form id="accept_friend"> <input type="button" class="btn" id="send_btn" value="Accept">' + 
				'<input type="hidden" name="username" id="username_accept" value=' + user.email +'> </form>' );
				var a4 = $('<form id="not_accept_friend"> <input type="button" class="btn" id="send_btn" value="Not accept">' + 
						'<input type="hidden" name="username" id="username_accept" value=' + user.email +'> </form>');
				
				
				div4.append(span1);
				div4.append(p1);
				div4.append(a1);
				div4.append(a2);
				div4.append(a3);
				li.append(div4);
				ul.append(li);
			})
			
			
			//zavrsiti for petlju
				div3.append(ul);
				div2.append(div3);
				div1.append(div2);
				div.append(title);
				div.append(div1);
				
				$('#request_list').append(div);
				
			
		}
	});
}

$(document).on('click', '#not_accept_friend', function(event){
	var user = {};
	user['email'] = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : "POST",		
		url : "../users/notAcceptFriendship",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
		},
		error : function(data){
			alert(data.message);

		}
	});
});



$(document).on('click', '#accept_friend', function(event){
	var user = {};
	user['email'] = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : "POST",		
		url : "../users/acceptFriendship",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
		},
		error : function(data){
			alert(data.message);

		}
	});
});



function renderFriends(){
	$.ajax({
		type : 'GET',
		url : "../users/allFriends",
		dataType : "json",
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			
			var div = $('<div class="span12"> </div>');
			var title = $('<h4 class="title">'+
					'<span class="pull-left"><span class="text"><span class="line">Friends</span></span></span>'+
					'<span class="pull-right">'+
						'<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>'+
					'</span></h4>');
			var div1 = $('<div id="myCarousel" class="myCarousel carousel slide"> </div>');
			var div2 = $('<div class="carousel-inner"> </div>');
			var div3 = $('<div class="active item"> </div> ');
			var ul = $('<ul class="thumbnails" id="friends">	</ul>');
			
			$.each(list, function(index, user) {
			
				var li = $('<li class="span3"> </li>');
				var div4 = $('<div class="product-box" id="friends"> </div>');
				var span1 = $('<span class="sale_tag"> </span>');
				var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
				var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname+ ' </a><br/>');
				var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
				var a3 = $('<form id="delete_friend"> <input type="button" id="delete_btn" class="btn" value="Delete" >'+
				'<input type="hidden" name="username" id="username" value=' + user.email +'> </form>' );
				
				
				div4.append(span1);
				div4.append(p1);
				div4.append(a1);
				div4.append(a2);
				div4.append(a3);
				li.append(div4);
				ul.append(li);
			})
			
		//zavrsiti for petlju
			div3.append(ul);
			div2.append(div3);
			div1.append(div2);
			div.append(title);
			div.append(div1);
			
			$('#friends_list').append(div);
				
			
		}
	});
	
}

$(document).on('click', '#delete_friend', function(event){
	var user = {};
	user['email'] = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : "POST",		
		url : "../users/deleteFriendship",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(user),
		success : function(data){
			alert(data.message);
		},
		error : function(data){
			alert(data.message);

		}
	})
})
