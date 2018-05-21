$(document).ready(function(){
	$("#create_place_button").click( function(event){
		event.preventDefault();
		
		$.ajax({
			type : 'GET',
			url : '../users/exists',
			dataType : 'json',
			success : function(data) {
				var loggedInUserEmail = data.user.email;
			
				var place = {};
				place["name"] = $('#place_name').val();
				place["address"] = $('#place_address').val();
				place["description"] = $('#place_description').val();
				
 		        var arenas = [];
				$('#arena_select input:checked').each(function() {
					var arena = {};
				    arena["id"] = $(this).val();
				    arenas.push(arena);
				});
				place["arenas"] = arenas;
				
				place["type"] = $('#place_type_select').val();
				
				 var projections = [];
					$('#projection_select input:checked').each(function() {
						var projection = {};
					    projection["id"] = $(this).val();
					    projections.push(projection);
					});
				place["projections"] = projections;
				
				place["userEmail"] = loggedInUserEmail;
				
				$.ajax({
					type : 'POST',
					url : '../places',
					contentType : "application/json",
					dataType : "json",
					data : JSON.stringify(place),
					complete : function (xhr) {
						$("#modalPlace").hide();
						$("#lean_overlay").hide();
						showPlaces();
					}
				});
			}
		})	
	});
	
	$.ajax({
		type : 'GET',
		url : '../places/types',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, placeType) {
				$('#place_type_select').append('<option value="' + placeType + '">' + placeType +'</option>');
			});
		}
	});
	
	$.ajax({
		type : 'GET',
		url : '../projections',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, projection) {
				$('#projection_select').append('<div><input type="checkbox" value="' + projection.id + '">' + projection.name +'</div>');
			});
		}
	});

	$.ajax({
		type : 'GET',
		url : '../arenas',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, arena) {
				$('#arena_select').append('<div><input type="checkbox" value="' + arena.id + '">' + arena.name +'</div>');
			});
		}
	});
	
});