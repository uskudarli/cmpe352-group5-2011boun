<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 	
 <script language = "JavaScript" src = "jquery-1.6.2.min.js"></script>
 
 <script language="JavaScript">
 	$(document).ready(function() {
		$("#rg").click(function() {
				
			$("#alertBox").html("");	
			var ch = false;
			if ($("#username").val() == "" || $("#passwd").val() == "" || $("#passwd2").val() == "")
			{
				$("#alertBox").append("*Please fill all the fields!");
				ch = true;
			}
			if ($("#passwd").val() !=  $("#passwd2").val())
			{
				$("#alertBox").append("<br /> *Passwords do not match!");
				ch = true;
			}
			if (!ch)
				$("#register").submit();
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
  
  <form method="post" name="register" id="register" action="Servlet_Register">
	
    <div class="box">
	<div id="alertBox" style="margin-bottom: 10px;">	
  	</div>			
	<div class="labelBox">
	 <label>User Name:</label><br />
	 <label style="margin-top: 8px; display: block;">Password:</label>
	 <label style="margin-top: 8px; display: block;">Password (Again):</label>
	</div>

	<div class="inputBox">	
	<input type="text" name="username" id="usrname" /><br />
	<input type="password" name="password" id="passwd" /><br />
	<input type="password" name="password2" id="passwd2" /><br />
	</div>	

	<input type="button" value="Register" id="rg" style="margin-top: 10px; display: block; float: left; width: 150px;" />
        <h2>The user already exists... Try again!</h2>>
    </div>	
  </form>		


</body>
</html>
