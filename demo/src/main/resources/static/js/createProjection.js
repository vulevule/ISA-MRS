$(document).ready(function(){
	$("#create_projection_button").click( function(event){
		event.preventDefault();
		
		create_projection();
	});
	
	$("#add_new_term_button").click( function(event){
		event.preventDefault();
		
		var input = $('<li><label>Arena: </label><select id="selectArena" name="selected_arena"></select><label>Start: </label><input type="datetime-local" id="datetime"/><label>Price: </label></li>');
		$('#terms_section').append(input);
		
		$.ajax({
			type : 'GET',
			url : '../users/exists',
			dataType : 'json',
			success : function(data) {
				var arenas = data.user.place.arenas;
				var list = arenas == null ? [] : (arenas instanceof Array ? arenas : [ arenas ]);
				
				$.each(list, function(index, arena) {
					$('#terms_section li:last-child #selectArena').append('<option value="' + arena.name + '">' + arena.name + '</option>');
				});
			}
		})
	});
});


function create_projection(){
	
	var projection = {};
	projection["name"] = $("#name").val();
	projection["cast"] = $("#cast").val();
	projection["genre"] = $("#genre").val();
	projection["director"] = $("#director").val();
	projection["duration"] = $("#duration").val();
	projection["description"] = $("#description").val();
	
	var terms = [];
	
	$.ajax({
		type : 'GET',
		url : '../users/exists',
		dataType : 'json',
		success : function(data) {
//			if (data.user.place == "CINEMA") {
//				projection["type"] = "MOVIE";
//			} else {
//				projection["type"] = "PLAY";
//			}
				
		}
	});
	
	$('#terms_section li').each(function() {
		var term = {};
		term["projectionDate"] = $(this).find("datetime-local").value.split("T")[0];
		term["projectionTime"] = $(this).find("datetime-local").value.split("T")[1];
		term["arena"] = {};
		term["arena"]["name"] = $(this).find("selectArena").value;
		term["projection"] = projection;
		terms.push(term);
	});
	
	projection["terms"] = terms;
	
	$.ajax({
		type : 'POST',
		url : '../projections/createProjection',
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(projection),
		success : function(data) {
			alert(data.message);
		}
	});
	
}