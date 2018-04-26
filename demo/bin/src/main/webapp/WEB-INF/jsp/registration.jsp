<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
		<title>Theaterize</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">      
		<link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.min.css"/>" rel="stylesheet">		
		<link href="<c:url value="/resources/themes/css/bootstrappage.css"/>" rel="stylesheet"/>
		
			<!-- global styles -->
		<link href="<c:url value="/resources/themes/css/flexslider.css"/>" rel="stylesheet"/>
		<link href="<c:url value="/resources/themes/css/main.css"/>" rel="stylesheet" />
		<!-- scripts -->
		<script src="<c:url value="/resources/themes/js/jquery-1.7.2.min.js"/>"></script>
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>				
		<script src="<c:url value="/resourcse/themes/js/superfish.js"/>"></script>	
		<script src="<c:url value="/resources/themes/js/jquery.scrolltotop.js"/>"></script>
</head>
<body>
<div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
					<form method="POST" class="search_form">
						<input type="text" class="input-block-level search-query" Placeholder="eg. T-sirt">
					</form>
				</div>
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">				
							<li><a href="#">My Account</a></li>
							<li><a href="cart.html">Your Cart</a></li>
							<li><a href="checkout.html">Checkout</a></li>					
							<li><a href="users/login">Login</a></li>		
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index.html" class="logo pull-left"><img src="themes/images//logo.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="./products.html">Thematic Props</a>					
								<ul>
									<li><a href="./products.html">Add</a></li>									
									<li><a href="./products.html">Change</a></li>
									<li><a href="./products.html">Remove</a></li>									
								</ul>
							</li>															
							<li><a href="./products.html">Reservation</a></li>			
							<li><a href="./products.html">Ad</a>
								<ul>									
									<li><a href="./products.html">List</a></li>
									<li><a href="./products.html">Bids</a></li>
								</ul>
							</li>							
						</ul>
					</nav>
				</div>
			</section>			
			<section class="header_text sub">
				<h4><span>Register</span></h4>
			</section>			
			<section class="main-content">				
				<div class="row">
					<div class="span5">
					</div>
					<c:url var="reg_action" value="/users/registrationUser"/>
					<div class="span7">					
						<form action="${reg_action}" method="post" class="form-stacked" modelAttribute="user">
							<fieldset>
								<div class="control-group">
									<label class="control-label">Email address:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your email" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Name</label>
									<div class="controls">
										<input type="text" placeholder="Enter your name" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Surname</label>
									<div class="controls">
										<input type="text" placeholder="Enter your surname" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Password:</label>
									<div class="controls">
										<input type="password" placeholder="Enter your password" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Confirm password:</label>
									<div class="controls">
										<input type="password" placeholder="Enter your password again" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Phone:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your phone number" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Country:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your country" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">City:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your city" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Address:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your address" class="input-xlarge">
									</div>
								</div>
								
								
								<div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Create your account"></div>
							</fieldset>
						</form>					
					</div>				
				</div>
			</section>			
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contac Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo.png" class="site_logo" alt=""></p>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2013 bootstrappage template  All right reserved.</span>
			</section>
		</div>
		<script src="themes/js/common.js"></script>
		<script>
			$(document).ready(function() {
				$('#checkout').click(function (e) {
					document.location.href = "checkout.html";
				})
			});
		</script>		
</body>
</html>