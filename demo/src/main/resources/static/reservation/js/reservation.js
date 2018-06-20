
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
	
	//popuniti listu prijatelja
	$.ajax({
		type : 'GET',
		url : "../users/allFriends",
		dataType : 'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data: [ data ]);
			
			$.each(list, function(index, friend){
				var option = $("<option> </option>");
				option.attr("value",friend.email).text(friend.email);
				$("#friends_list").append(option);
			})
		}
	})
	
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
	//ocistiti div u kojem se nalazi arena
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
								$('<li> '+(this.settings.row+1)+'-'+this.settings.label+'</li>')
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
					
					
					
					$.ajax({
						type : 'GET',
						url : '../reservationByTerm?id='+term_id,
						success : function(data){
							var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
							
							var sold_seat = [];
							$.each(list, function(index, reservation) { 
								var seat = '';
								seat = seat + reservation.row + '_' + reservation.seatNum;
								sold_seat.push(seat);
							});
							
							sc.get(sold_seat).status('unavailable');//sold seats
							
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("AJAX ERROR: " + errorThrown);
						}
					});
					
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

$(document).on('click', "#book_button", function(event){
	
	event.preventDefault();
	
	var seats = [];
	const listItems = document.querySelectorAll('#selected-seats li');
	for (let i = 0; i < listItems.length; i++) {
	  seats.push(listItems[i].textContent);
	}

	
	var inviteFriends = [];	
	var friends = $("#friends_list").val();
	if(friends != null){
		for(let i=0; i < friends.length; i++){
			inviteFriends.push(friends[i]);
		}
	}
	//povukli smo sve podatke, sad samo treba da ih prosledimo serveru
	var res = {};
	res["term"] = $("#term").val();
	res["seats"] = seats;
	res["inviteFriends"] = inviteFriends;
	var termid = $("#term").val();
	$.ajax({
		type : "POST",		
		url : "../reservation/createReservation",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(res),
		success : function(data){
			alert("Successful reservation");
			location.reload();
		},
		error : function(XMLHttpRequest, Textstatus, Errorthrown){
			console.log("ajax error: " + Errorthrown + ", status: " + Textstatus);
		}
	})
	//location.reload();
/*	var place = $("#place").val();
	var projection = $("#projection").val();
	var term = $("#term").val();
	
	
	location.reload();
	$("#place").val(place);
	$("#projection").val(projection);
	$("#term").val(term);
	*/
});
	

