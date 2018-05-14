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
	
	renderFriends();
	renderRequest();
	renderAllVisitor();
	
	


	
})

function renderAllVisitor(){
	var div = $('<div class="span12"> </div>');
	var title = $('<h4 class="title">'+
			'<span class="pull-left"><span class="text"><span class="line">Add friends</span></span></span>'+
			'<span class="pull-right">'+
				'<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>'+
			'</span></h4>');
	var div1 = $('<div id="myCarousel" class="myCarousel carousel slide"> </div>');
	var div2 = $('<div class="carousel-inner"> </div>');
	var div3 = $('<div class="active item"> </div> ');
	var ul = $('<ul class="thumbnails" id="add_friend">	</ul>');
	//poceti for petlju
	
	var li = $('<li class="span3"> </li>');
	var div4 = $('<div class="product-box" id="add_friend_id"> </div>');
	var span1 = $('<span class="sale_tag"> </span>');
	var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
	var a1 = $('<a href="product_detail.html" class="title">Mira Mirkovic</a><br/>');
	var a2 = $('<a href="products.html" class="category"> miram2@gmail.com </a>');
	var a3 = $('<form> <input type="button" class="btn" value="Send request"></form>');
	
	
	div4.append(span1);
	div4.append(p1);
	div4.append(a1);
	div4.append(a2);
	div4.append(a3);
	li.append(div4);
	ul.append(li);
	
	/*$.each(list, function(index, user) {
	
		var li = $('<li class="span3"> </li>');
		var div4 = $('<div class="product-box" id="friends"> </div>');
		var span1 = $('<span class="sale_tag"> </span>');
		var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
		var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname ' </a><br/>');
		var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
		var a3 = $('<form> <input type="button" class="btn" value="Delete"></form>');
		
		
		div4.append(span1);
		div4.append(p1);
		div4.append(a1);
		div4.append(a2);
		div4.append(a3);
		li.append(div4);
		ul.append(li);
	}*/
	
//zavrsiti for petlju
	div3.append(ul);
	div2.append(div3);
	div1.append(div2);
	div.append(title);
	div.append(div1);
	
	$('#add_friends_list').append(div);
	
}



function renderRequest(){
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
	//poceti for petlju
	
	var li = $('<li class="span3"> </li>');
	var div4 = $('<div class="product-box" id="requests_id"> </div>');
	var span1 = $('<span class="sale_tag"> </span>');
	var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
	var a1 = $('<a href="product_detail.html" class="title">Petar Petrovic </a><br/>');
	var a2 = $('<a href="products.html" class="category"> petar12@gmail.com </a>');
	var a3 = $('<form> <input type="button" class="btn" value="Accept"></form>');
	
	
	div4.append(span1);
	div4.append(p1);
	div4.append(a1);
	div4.append(a2);
	div4.append(a3);
	li.append(div4);
	ul.append(li);
	
	/*$.each(list, function(index, user) {
	
		var li = $('<li class="span3"> </li>');
		var div4 = $('<div class="product-box" id="friends"> </div>');
		var span1 = $('<span class="sale_tag"> </span>');
		var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
		var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname ' </a><br/>');
		var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
		var a3 = $('<form> <input type="button" class="btn" value="Delete"></form>');
		
		
		div4.append(span1);
		div4.append(p1);
		div4.append(a1);
		div4.append(a2);
		div4.append(a3);
		li.append(div4);
		ul.append(li);
	}*/
	
//zavrsiti for petlju
	div3.append(ul);
	div2.append(div3);
	div1.append(div2);
	div.append(title);
	div.append(div1);
	
	$('#request_list').append(div);
	
	
}

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
			//poceti for petlju
			
			var li = $('<li class="span3"> </li>');
			var div4 = $('<div class="product-box" id="friends"> </div>');
			var span1 = $('<span class="sale_tag"> </span>');
			var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
			var a1 = $('<a href="product_detail.html" class="title">Pera Peric </a><br/>');
			var a2 = $('<a href="products.html" class="category"> perap@gmail.com </a>');
			var a3 = $('<form> <input type="button" class="btn" value="Delete"></form>');
			
			
			div4.append(span1);
			div4.append(p1);
			div4.append(a1);
			div4.append(a2);
			div4.append(a3);
			li.append(div4);
			ul.append(li);
			
			/*$.each(list, function(index, user) {
			
				var li = $('<li class="span3"> </li>');
				var div4 = $('<div class="product-box" id="friends"> </div>');
				var span1 = $('<span class="sale_tag"> </span>');
				var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/user.jpg" alt="" /></a></p> ');
				var a1 = $('<a href="product_detail.html" class="title">' + user.name + " " + user.surname ' </a><br/>');
				var a2 = $('<a href="products.html" class="category">'+ user.email + '</a>');
				var a3 = $('<form> <input type="button" class="btn" value="Delete"></form>');
				
				
				div4.append(span1);
				div4.append(p1);
				div4.append(a1);
				div4.append(a2);
				div4.append(a3);
				li.append(div4);
				ul.append(li);
			}*/
			
		//zavrsiti for petlju
			div3.append(ul);
			div2.append(div3);
			div1.append(div2);
			div.append(title);
			div.append(div1);
			
			$('#friends_list').append(div);
			
			
		}
	})
}
