<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Theaterize</title>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-func.js"></script>

<!--[if IE 6]><link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" /><![endif]-->
</head>
<body>
<!-- START PAGE SOURCE -->
<div id="shell">
  <div id="header">
    <h1 id="logo"><a href="#">Theaterize</a></h1>
    <div class="social"> <span>FOLLOW US ON:</span>
      <ul>
        <li><a class="twitter" href="#">twitter</a></li>
        <li><a class="facebook" href="#">facebook</a></li>
      </ul>
    </div>
    <div id="navigation">
      <ul>
        <li><a class="active" href="#">HOME</a></li>
        <li><a href="#">IN THEATERS</a></li>
        <li><a href="#">CONTACT</a></li>
        <li><a href="#">ADVERTISE</a></li>
      </ul>
    </div>
    <div id="sub-navigation">
      <ul>
        <li><a href="#">SHOW ALL</a></li>
        <li><a href="#">LATEST TRAILERS</a></li>
      </ul>
      <div id="search">
        <form action="#" method="get" accept-charset="utf-8">
          <label for="search-field">SEARCH</label>
          <input type="text" name="search field" value="Enter search here" id="search-field" class="blink search-field"  />
          <input type="submit" value="GO!" class="search-button" />
        </form>
      </div>
    </div>
  </div>
  <div id="main">
    <div id="content">
	
      <div class="box">
        <div class="head">
          <h2 class="title">
			<span class="pull-left"><span class="text"><span class="line">LATEST TRAILERS</span></span></span>
			<span class="pull-right">
				<a class="left button" href="#myCinemas" data-slide="prev"></a><a class="right button" href="#myCinemas" data-slide="next"></a>
			</span>
		  
		  </h2>
          <p class="text-right"><a href="#">See all</a></p>
        </div>
		
		
		<div id="myCinemas" class="myCinemas carousel slide">
			<div class="carousel-inner">
				<div class="active item">
				
					<c:forEach items="${places}" var="place">
						<div class="movie">
					  <div class="movie-image"> <span class="play"><span class="name">{$place.name}</span></span> <a href="#"><img src="css/images/croped.jpg" alt="" /></a> </div>
					  <div class="rating">
						<p>RATING</p>
						<div class="stars">
						  <div class="stars-in"> </div>
						</div>
						<span class="comments">12</span> </div>
					</div>
					</c:forEach>
				
					
					
					
				</div>	
			</div>
		</div>
				
				
        <div class="cl">&nbsp;</div>
      </div>
      
      
    </div>
    

    <div class="cl">&nbsp;</div>
  </div>
  <div id="footer">
    <p class="lf">Copyright &copy; 2018 <a href="#">Theaterize</a> - All Rights Reserved</p>
    <p class="rf">Design by <a href="http://chocotemplates.com/">ChocoTemplates.com</a></p>
    <div style="clear:both;"></div>
  </div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>