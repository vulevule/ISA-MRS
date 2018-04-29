$(function(){
	$('button[type=submit]').click(function(e){
		e.preventDefault();
		
		$('input').next().remove();
		
		$.post({
			url : '/users/loginUser',
			data : $('form[name=loginForm]').serialize(),
			success : function(res){
				if(res.validated){
					//set response
				}else{
					//set error messages
					
				}
			}
		})
	});
});

