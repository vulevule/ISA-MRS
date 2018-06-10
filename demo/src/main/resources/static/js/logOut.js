/**
 * 
 */
$(document).on('click', '#log-out', function(event){
	$.ajax({
		type : 'GET',
		url : '../users/logOut',
		dataType : 'json',
		success : function(data){
			//redirekcija na pocetnu stranicu
			window.location.replace("http://localhost:8080/index.html")
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
		
	});
})