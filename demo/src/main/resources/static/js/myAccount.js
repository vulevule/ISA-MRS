/**
 * 
 */

$(document).ready(function(){
	document.getElementById('my_account').click(function(event){
		event.preventDefault();
		
		myAccount_click();
	});
	
});

function myAccount_click(){
	var username = $(this).text();
	console.log(username);
	var sendData = {};
	sendData['username'] = username;
	
	$.ajax({
		type : "POST",
		url :  "../users/getUser/"+ username,
		dataType : "json",
		success : function(data){
			//PRAVIMO ACCOUNT STRANICU 
			var div1 = $('<div class="span9"> </div>');
			var fieldset = $('<fieldset>  </fieldset>');
			var title = $('<h4 class="title"> <span class="pull-left"> <span class="text"> <span class="line"> MY PROFILE </span> </span> </span> </h4> ');
			
			//name
			var name_div = $('<div class="control-group" id="name_div"> </div>');
			var name_label = $('<label class="control-label"> First name </label>');
			name_div.append(name_label);
			var name_div2 = $('<div class="controls"> </div>');
			var name= $('<input type="text" value='+ data.user.name + 'class="input-xlarge">');
			name_div2.append(name);
			name_div.append(name_div2);
			
			//surname
			var surname_div = $('<div class="control-group" id="surname_div"> </div>');
			var surname_label = $('<label class="control-label"> Last name </label>');
			surname_div.append(name_label);
			var surname_div2 = $('<div class="controls"> </div>');
			var surname= $('<input type="text" value='+ data.user.surname + 'class="input-xlarge">');
			surname_div2.append(surname);
			surname_div.append(surname_div2);
			
			//email
			var email_div = $('<div class="control-group" id="email_div"> </div>');
			var email_label = $('<label class="control-label"> Email </label>');
			email_div.append(email_label);
			var email_div2 = $('<div class="controls"> </div>');
			var email= $('<input type="text" value='+ data.user.email + 'class="input-xlarge">');
			email_div2.append(email);
			email_div.append(email_div2);
			
			//password
			var password_div = $('<div class="control-group" id="password_div"> </div>');
			var pass_label = $('<label class="control-label"> Password </label>');
			password_div.append(pass_label);
			var pass_div2 = $('<div class="controls"> </div>');
			var pass= $('<input type="password" placeholder="New password" class="input-xlarge">');
			pass_div2.append(pass);
			pass_div.append(pass_div2);
			
			//address
			var address_div = $('<div class="control-group" id="address_div"> </div>');
			var address_label = $('<label class="control-label"> Address </label>');
			address_div.append(address_label);
			var address_div2 = $('<div class="controls"> </div>');
			var address= $('<input type="text" value=' + data.user.address + 'class="input-xlarge">');
			address_div2.append(address);
			address_div.append(address_div2);
			
			//phone
			var phone_div = $('<div class="control-group" id="phone_div"> </div>');
			var phone_label = $('<label class="control-label"> Phone </label>');
			phone_div.append(phone_label);
			var phone_div2 = $('<div class="controls"> </div>');
			var phone= $('<input type="text" value='+ data.user.phone + ' class="input-xlarge">');
			phone_div2.append(phone);
			phone_div.append(phone_div2);
			
			//update and continue button
			var p = $('<p> </p>');
			var update = $('<button class="btn" type="button"> Update </button>');
			var cont = $('<button class="btn" type="button"> Continue </button>');
			
			p.append(update);
			p.append(cont);
			
			
			fieldset.append(title);
			fieldset.append(name_div);
			fieldset.append(surname_div);
			fieldset.append(email_div);
			fieldset.append(pass_div);
			fieldset.append(address_div);
			fieldset.append(phone_div);
			fieldset.append(p);
			
			
			
			div1.append(fieldset);
			alert (data.username);
		},
		error : function(data){
			alert("Error request");
		}
	})
}