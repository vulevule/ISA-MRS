/**
 * 
 */
/*1. funkcija koja se aktivira prilikom ucitavanja reservation.html stranice, 
 * popunjava polje sa bioskopima i pozoristima 
 * 
 */

$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : '../places',
		dataType : 'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			
			
			$.each(list, function(index, place) { 
				var newOption = $("<option></option>");
				newOption.attr("value",place.id).text(place.name);
				$("#place").append(newOption);
				
			});
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
})




$('#place').on('change',function (e) {
    alert(this.value);
});


function FindProjection(place){

	
	$.ajax({
		type : 'GET',
		url : '../projections/projectionId/' + place,
		dataType : 'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			
			
			$.each(list, function(index, projection) { 
				var newOption = $("<option></option>");
				newOption.attr("value",projection.id).text(projection.name);
				$("#projection").append(newOption);
				
			});
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}