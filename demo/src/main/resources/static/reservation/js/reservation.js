/**
 * 
 */

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
$(function(){ /* DOM ready */
    $("#place").change(function() {
        FindProjection($(this).val());
    });
});


function FindProjection(place){
	
	var id = place;
	$.ajax({
		type : 'GET',
		url : '../projections/projectionId?id='+id,
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

$(function(){ /* DOM ready */
    $("#projection").change(function() {
        FindTerms($(this).val());
    });
});

function FindTerms(projection_id){
	
	var id = projection_id;
	$.ajax({
		type : 'GET',
		url : '../projections/termsId?id='+id,
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			
			
			$.each(list, function(index, term) { 
				var newOption = $("<option></option>");
				newOption.attr("value",term.id).text(term.projectionDate + ", " + term.projectionTime);
				$("#term").append(newOption);
				
			});
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


$(function(){ /* DOM ready */
    $("#term").change(function() {
        FindTerm($(this).val());
    });
});


function FindTerm(term_id){
	
	var id = term_id;
	$.ajax({
		type : 'GET',
		url : '../projections/termId?id='+id,
		success : function(data){
			/*napraviti izgled arene i setovati cenu*/
			var price = data.price; //cena karte
			var $cart = $('#selected-seats'), //Sitting Area
			$counter = $('#counter'), //Votes
			$total = $('#total'); //Total money
			
			$.ajax({
				type : 'GET',
				url : '../projections/arenaId?id='+id,
				success : function (data1){
					var row1 = data1.rowSeats;
					var col1 = data1.columnSeats;
					var i; var j;					
					var seats = [];
					var row = '';
					
					for(i = 0; i < row1; i++){
						for(j = 0; j < col1; j++){
							row = row + 'a';
						}
						
						seats.push(row);
						row = '';
					}
					var sc = $('#seat-map').seatCharts({
						map:seats,
						naming : {
							top : false,
							getLabel : function (character, row, column) {
								return column;
							}
						},
						legend : { //Definition legend
							node : $('#legend'),
							items : [
								[ 'a', 'available',   'Available' ],
								[ 'a', 'unavailable', 'Sold'],
								[ 'a', 'selected', 'Selected']
							]					
						},
						click: function () { //Click event
							if (this.status() == 'available') { //optional seat
								$('<li>Row'+(this.settings.row+1)+' Seat'+this.settings.label+'</li>')
									.attr('id', 'cart-item-'+this.settings.id)
									.data('seatId', this.settings.id)
									.appendTo($cart);

								$counter.text(sc.find('selected').length+1);
								$total.text(recalculateTotal(sc)+price);
											
								return 'selected';
							} else if (this.status() == 'selected') { //Checked
									//Update Number
									$counter.text(sc.find('selected').length-1);
									//update totalnum
									$total.text(recalculateTotal(sc)-price);
										
									//Delete reservation
									$('#cart-item-'+this.settings.id).remove();
									//optional
									return 'available';
							} else if (this.status() == 'unavailable') { //sold
								return 'unavailable';
							} else {
								return this.style();
							}
						}

					});
					

					//sold seat, treba povezati sa backendom
					sc.get(['1_2', '4_4','4_5','6_6']).status('unavailable');
					
				}
			});
			
			
			function recalculateTotal(sc) {
				var total = 0;
				sc.find('selected').each(function () {
					total += price;
				});
						
				return total;
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}




