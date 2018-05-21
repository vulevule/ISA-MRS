/* $(document).ready(function(){
	$("#create_admin_button").click( function(event){
		event.preventDefault();
		
		$.ajax({
			type : 'GET',
			url : '../users/exists',
			dataType : 'json',
			success : function(data) {
				var loggedInUserEmail = data.user.email;
			
			
				
				var place = {};
				place["name"] = $('#user_name').val();
				place["surname"] = $('#user_surname').val();
				place["email"] = $('#user_email').val();
				place["password"] = $('#user_password').val();
				place["address"] = $('user_address').val();
				place["phone"] = $('user_type').val();
				place["type"] = $('user_phone').val();
				place["place"] =  $('#place_select').val();

				place["userEmail"] = loggedInUserEmail;
				
				
				$.ajax({
					type : 'POST',
					url : '../users',
					contentType : "application/json",
					dataType : "json",
					data : JSON.stringify(place),
					success : function(data) {
						alert(data.message);
					}
				});
			}
		})	
	});
	
	$.ajax({
		type : 'GET',
		url : '../places',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, placeType) {
				$('#place_select').append('<option value="' + place.id + '">' + place.name +'</option>');
			});
		}
	});
	
	
}); */