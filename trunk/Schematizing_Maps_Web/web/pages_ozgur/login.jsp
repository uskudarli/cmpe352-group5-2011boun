<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

 <script language = "JavaScript" src = "jquery-1.6.2.min.js"></script>
 
 <script language="JavaScript">
 	$(document).ready(function() {
		$("#lg").click(function() {
				
			$("#alertBox").html("");	
			var ch = false;
			if ($("#username").val() == "" || $("#passwd").val() == "")
			{
				$("#alertBox").append("*Please fill all the fields!");
				ch = true;
			}

			if (!ch)
				$("#login").submit();
		});

	});

 </script>	

	
 <style type="text/css">
   div.box
   {
	position: absolute;
	top: 30%;
	left: 38%;
	width: 50%;

   } 	
   div.labelBox 
   {
	margin-right: 20px;
	float: left;
   }
   div.inputBox 
   {
	margin-left: 20px;
   }	
   p {margin-left:20px;}
 </style>


</head>

<body background="paisley_background_soft-630x390.jpg">

  <img src="407_Welcome_4_plain.png" style="position: absolute; left: 40%; top: 5%; width="300" height="100">
  	
  <form method="post" name="login" id="login" action="">
	
    <div class="box">		
	<div id="alertBox" style="margin-bottom: 10px;">	
  	</div>	
	<div class="labelBox">
	 <label>User Name:</label><br />
	 <label style="margin-top: 10px; display: block;">Password:</label>
	</div>

	<div class="inputBox">	
	<input type="text" name="username" id="usrname" /><br />
	<input type="password" name="password" id="passwd" /><br />
	</div>	
	
	<div id="userType" style="margin-top: 20px;">
	    <input type="radio"	id="adv" name="userType" value="Advanced"/>Advanced User
	    <input type="radio"	id="smpl" name="userType" value="Simple" style="margin-left: 15px;" checked />Simple User		
	</div>		

	<input type="button" value="Register Now!" style="margin-top: 10px; display: block; float: left; width: 150px;" onclick="window.location.href="(LINK_TO_THE_REGISTER_PAGE)"/>
	<input type="button" value="Enter" id="lg" style="margin-left: 50px; margin-top: 10px; width: 100px;" />

    </div>	
  </form>		


</body>
</html>
