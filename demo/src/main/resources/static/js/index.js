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
			
			if(data.user != null){
				$('#modal_trigger').hide();
				$('#modal_trigger1').hide();
				$('#myAccount').show();
				$('#log-out').show();
			}
		}
	});


	
	var div = $('<div class="span12"> </div>');
	var title = $('<h4 class="title">'+
			'<span class="pull-left"><span class="text"><span class="line">Cinema and theater list</span></span></span>'+
			'<span class="pull-right">'+
				'<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>'+
			'</span></h4>');
	var div1 = $('<div id="myCarousel" class="myCarousel carousel slide"> </div>');
	var div2 = $('<div class="carousel-inner"> </div>');
	var div3 = $('<div class="active item"> </div> ');
	var ul = $('<ul class="thumbnails" id="cinema_theater">	</ul>');
	
	//1 bioskop ili pozoriste, ovde staviti for petlju za ispis vise bioskopa ili pozorista
	var li = $('<li class="span3"> </li>');
	var div4 = $('<div class="product-box" id="cinema_theater"> </div>');
	var span1 = $('<span class="sale_tag"> </span>');
	var p1 = $('<p><a href="product_detail.html"><img src="./themes/images/cinemas/croped.jpg" alt="" /></a></p> ');
	var a1 = $('<a href="product_detail.html" class="title">Cinema1</a><br/>');
	var a2 = $('<a href="products.html" class="category">Novi Sad</a>');
	
	//ispis bioskopa
	div4.append(span1);
	div4.append(p1);
	div4.append(a1);
	div4.append(a2);
	li.append(div4);
	ul.append(li);
	//kraj for petlje 
	
	$.ajax({
		type : "GET",		
		url : "../places/getPlaces",
		dataType : "json",
		success : function(data){
			alert("Success!");
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	})
	
	div3.append(ul);
	div2.append(div3);
	div1.append(div2);
	div.append(title);
	div.append(div1);
	
	
	
	$('#place_list').append(div);
	$('#accaountButton_id').hide();
	
});

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	var forma = $("#productsform");
	
//	$('#wineList li').remove();
//	$.each(list, function(index, product) {
//		$('#wineList').append(
//				'<li><a href="#" data-identity="' + product.name + '">'
//						+ product.price + "," + product.name + '</a></li>');
//	});
	$.each(list, function(index, product) {
		
        var forma = $('<form method="get" class="productsform" action="ShoppingCartServlet"></form>');
        var tr = $('<tr></tr>');
        tr.append('<td>' + product.name + '</td>' +
                '<td>' + product.price + '</td>');
        forma.append('<input type="text" size="3" name="count">' +
                '<input type="hidden" name="id" value='+ product.id +'>' +
                '<input type="submit" value="Dodaj">');
        var td = $('<td></td>');
        td.append(forma);
        tr.append(td);
        $('#tabela').append(tr);
	});
}

