$(document).ready(function() {
	$("#create_button").click(function(event) {
		event.preventDefault();
		
		$.ajax({
			type : 'GET',
			url : '../users/exists',
			dataType : 'json',
			success : function(data) {
				var selectedProjectionId = $('#projection_select').val();
				var loggedInUserEmail = data.user.email;
				var thematicPropName = $('#name_id').val();
				
				var thematicProp = {};
				thematicProp["name"] = thematicPropName;
				thematicProp["projectionId"] = selectedProjectionId;
				thematicProp["userEmail"] = loggedInUserEmail;
				
				$.ajax({
					type : 'POST',
					url : '../thematicprops',
					contentType : "application/json",
					dataType : "json",
					data : JSON.stringify(thematicProp),
					success : function(data) {
						alert(data.message);
					}
				});
			}
		})	
	});
	
	$.ajax({
		type : 'GET',
		url : '../projections',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, projection) {
				$('#projection_select').append('<option value="' + projection.id + '">' + projection.name +'</option>');
			});
		}
	});
});
