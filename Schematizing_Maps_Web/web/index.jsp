<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	

 <script language = "JavaScript" src = "jquery-1.6.2.min.js"></script>
 <script language = "JavaScript" src = "jquery.easing.1.3.js"></script> 
 <script language = "JavaScript" src = "jquery.easing.compatibility.js"></script> 		
 
 <script language="JavaScript">
 	$(document).ready(function() {
		
		    $('#left').animate({
    			left: '-=20%'
  			}, 3000, 'easeOutQuad', function() {
    			// Animation complete.
  		    });
		
		    $('#right').animate({
    			left: '+=20%'
  			}, 3000, 'easeOutQuad', function() {
    			// Animation complete.
  		    });


		$("#loginButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#loginForm").css({'display': 'block'});		
		});
		
		$("#registerButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#registerForm").css({'display': 'block'});		
		});

		$("#aboutButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#about").css({'display': 'block'});		
		});

		$("#historyButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#history").css({'display': 'block'});		
		});
			
		$("#contactButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#contact").css({'display': 'block'});		
		});

		$("#socialButton").click(function() { 
			$(".form").css({'display': 'none'});
			$("#social").css({'display': 'block'});		
		});
	
		$("#lg").click(function() {
				
			$("#loginAlert").html("");	
			var ch = false;
			if ($("#lgn_username").val() == "" || $("#lgn_passwd").val() == "")
			{
				$("#loginAlert").append("*Please fill all the fields!");
				ch = true;
			}

			if (!ch)
			{
				$(".middleBlock").css({'display': 'none'});
				$('#left').animate({
    					left: '-=30%'
  					}, 3000, 'easeOutQuad', function() {
    						$("#login").submit();
  		   		});
				$('#right').animate({
    					left: '+=30%'
  					}, 3000, 'easeOutQuad', function() {
    					// Animation complete.
  		    		});
			}
		});

		$("#rg").click(function() {
				
			$("#registerAlert").html("");	
			var ch = false;
			if ($("#rg_username").val() == "" || $("#rg_passwd").val() == "" || $("#rg_passwd2").val() == "")
			{
				$("#registerAlert").append("*Please fill all the fields!");
				ch = true;
			}
			if ($("#rg_passwd").val() !=  $("#rg_passwd2").val())
			{
				$("#alertBox").append("<br /> Passwords do not match!");
				ch = true;
			}
			if (!ch)
			{
				$(".middleBlock").css({'display': 'none'});
				$('#left').animate({
    					left: '-=30%'
  					}, 3000, 'easeOutQuad', function() {
    						$("#register").submit();
  		   		});
				$('#right').animate({
    					left: '+=30%'
  					}, 3000, 'easeOutQuad', function() {
    					// Animation complete.
  		    		});
			}
				
		});


	});

 </script>	

	
 <style type="text/css">
   div.leftBlock
   {
	position: absolute;
	z-index: 2;
	top: 0%;
	left: 20%;
	width: 30%;
	height: 100%;
	background: url('Resim1.jpg') no-repeat;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
   } 	
   div.rightBlock 
   {
	position: absolute;
	z-index: 2;	
	top: 0%;
	left: 50%;
	width: 30%;
	height: 100%;
	background: url('Resim2.jpg') right top no-repeat;  
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
   }
   div.middleBlock 
   { 
	position: absolute;
	top: 0%;
	right: 30%;
	width: 40%;
	height: 100%;
	background: url('Resim3.jpg') no-repeat;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
   }

   div.buttonBox
   {
	margin-left: 10px;
	margin-top: 40%;     
   }

   div.loginBox
   {
	position: absolute;
	right: 10px;
	top: 10px;
	width: 200px;
	height: 40px;
	margin-left: 10px;     
   }

   div.innerBox
   {
	position: absolute;
	top: 35%;
	left: 30%;
   }	

   span.menuButton
   {
	cursor: pointer;
	display: block;
   	margin-top: 5px;
	width: 66px;
	height: 30px;
	line-height: 20px;
	text-align: center;
	font-style: italic;
	font-weight: bold;
	background: url('Resim5.jpg') no-repeat;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
   }

   span.topButton 	
   {
	cursor: pointer;
   	margin: 5px;
	padding: 5px;	
	line-height: 20px;
	text-align: center;
	font-style: italic;	
	font-weight: bold;
	background: url('Resim5.jpg') no-repeat;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;   
   }
   div.form
   {		
   	display: none;
   }
   div.box
   {
	position: absolute;
	top: 30%;
	left: 20%;
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
	
   p
   {
   	font-style: italic;	
	font-weight: bold;
	max-width: 350px;
   }
 </style>


<% 
    Cookie[] cookies = request.getCookies();
    Cookie myCookie = null;
    boolean flag=false;
    if (cookies != null)
        {
            for (int i = 0; i < cookies.length; i += 1) {
              if (cookies[i].getValue().equals("LOGGED_IN")) {
                myCookie = cookies[i];
                flag=true;
                break;
              }
            }
            if(flag){
                String name=myCookie.getName();
                response.sendRedirect("mainWindow.jsp?name="+name);
            }
       }
%>
</head>

<body background="Resim3.jpg">

	<div class="leftBlock" id="left">
	</div>
	<div class="middleBlock">
	    <div class="loginBox">
                <%String login_req=(String) session.getAttribute("loginRequired");
                    if(login_req!=null){
                        System.out.print("<p>Login Required</p>");
                    }%>
	        <span class="topButton" id="loginButton">
		    Login
		</span>
		<span class="topButton" id="registerButton">
		    Register
		</span>
	    </div>	
	    <div class="buttonBox">
		<span class="menuButton" id="aboutButton">
		    About
		</span>
		<span class="menuButton" id="historyButton">
		    History
		</span>
		<span class="menuButton" id="contactButton">
		    Contact
		</span>
		<span class="menuButton" id="socialButton">
		    Social
		</span>
	    </div>
		    <h1 style="font-style: italic; font-weight: bold; position: absolute; top: 15%; left: 33%; font-size: 50px;">Schematizer</h1>	
		    <div class="innerBox">
		     <div id="loginForm" class="form">		
		      <form method="post" name="login" id="login" action="Servlet_Login">
			
			<div id="loginAlert" style="margin-bottom: 10px;">	
		  	</div>	

			 <label>User Name:</label>
            		 <input type="text" name="lgn_username" id="lgn_usrname" /><br />
			 <label>Password:</label>&nbsp;&nbsp;&nbsp;
			 <input type="password" name="lgn_password" id="lgn_passwd" /><br />
	
			<div id="userType" style="margin-top: 20px;">
			    <input type="radio"	id="adv" name="userType" value="Advanced"/>Advanced User
			    <input type="radio"	id="smpl" name="userType" value="Simple" style="margin-left: 15px;" checked="CHECKED" />Simple User		
			</div>

			<input type="button" value="Login" id="lg" style="margin-top: 10px; width: 100px;" />
		      </form>
		     </div>

		     <div id="registerForm" class="form">
			  <form method="post" name="register" id="register" action="Servlet_Register">
				
				<div id="registerAlert" style="margin-bottom: 10px;">	
		  		</div>				
		
				 <label>User Name:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="text" name="rg_username" id="rg_usrname" /><br />				
				 <label>Password:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="password" name="rg_password" id="rg_passwd" /><br />
				 <label>Password (Again):</label>
				 <input type="password" name="rg_password2" id="rg_passwd2" /><br />
                                 <div id="userType" style="margin-top: 20px;">
                                    <label>Login As:</label>
                                    <input type="radio"	id="adv" name="userType" value="Advanced"/>Advanced User
                                    <input type="radio"	id="smpl" name="userType" value="Simple" style="margin-left: 15px;" checked="CHECKED" />Simple User		
                                 </div>
				<input type="button" value="Register" id="rg" style="margin-top: 10px; display: block; float: left; width: 150px;" />
	
			  </form>
		     </div>
				
		     <div id="about" class="form" style="display: block;">
			<p>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris molestie eleifend nunc, vel egestas sapien cursus ac. Nunc in enim at ante tincidunt pharetra eu quis neque. Quisque vel auctor enim. Nullam porta, tortor vel vehicula laoreet, felis erat molestie libero, vitae aliquam augue ipsum vitae odio. Maecenas et velit mi, sit amet auctor nunc. In eros diam, molestie vel varius id, vulputate ut purus. Pellentesque eu viverra erat. Duis ut elit quam. Vestibulum gravida, nisl dapibus volutpat placerat, dui lectus feugiat est, iaculis pharetra risus dui et tortor. Sed dictum suscipit nisi eu aliquet. Donec in odio nec eros iaculis scelerisque at vitae mauris. Vestibulum egestas rutrum dolor, at adipiscing nunc scelerisque scelerisque. Sed eget sapien a dolor luctus commodo.	
			</p>
			<p>
				Etiam fermentum iaculis aliquet. Maecenas dignissim est consectetur magna tincidunt nec egestas metus volutpat. Praesent suscipit, lacus ac placerat cursus, turpis quam sagittis sapien, non pellentesque purus mauris id mauris. Etiam et venenatis risus. Aenean cursus eleifend quam, quis porttitor lacus faucibus faucibus. Mauris suscipit metus quis dolor blandit non molestie urna dignissim. Nunc vel eros nec justo dignissim vestibulum eu sit amet justo. Pellentesque nibh quam, viverra cursus fringilla eu, consectetur sit amet sapien. Fusce congue accumsan velit sed hendrerit. Aliquam pellentesque diam eget tellus faucibus aliquet.	
			</p>
		     </div>		
		
		     <div id="history" class="form">
			<p>
				Integer eleifend luctus pulvinar. Maecenas posuere ipsum quis nisl sollicitudin dignissim. Morbi congue adipiscing metus, id ultrices eros ultrices tempus. Pellentesque ut enim ut urna lacinia lacinia non sed nunc. Integer quis nibh eget turpis interdum tempus eu ac nisl. Mauris blandit eleifend diam sagittis tristique. Phasellus nec enim quis velit consequat porttitor id ut massa.
			</p>
			<p>
				Donec facilisis fermentum nulla, ut porta erat egestas in. Proin tellus mi, condimentum ac euismod id, imperdiet eu erat. Duis interdum ipsum ac diam pellentesque in tincidunt turpis tempor. Cras et mi sapien. Aenean pharetra condimentum lacus, vitae feugiat elit ullamcorper quis. In tellus turpis, tristique a sagittis in, pulvinar tincidunt nunc. Nullam id metus vel urna congue lobortis pharetra laoreet arcu. Nullam hendrerit magna gravida quam pulvinar eget accumsan massa pharetra. Nulla sed ligula tortor. Sed pulvinar egestas egestas. Cras quis commodo lectus. Mauris at aliquam leo. Maecenas accumsan dapibus purus non pretium. Nulla mollis, neque viverra molestie ullamcorper, nisl diam condimentum risus, adipiscing dictum diam magna in justo.
			</p>
		     </div>	

		     <div id="contact" class="form">
			<p>
				Contact us via our google accounts:<br />
				npx.yilmaz<br /> bahhti<br /> ervin.domazet<br /> yektasaid.can<br /> eylultasyurek<br /> mertterzihan<br /> akcali.ozgur
			</p>
			
		     </div>

		     <div id="social" class="form">
			<p>
				Follow us on Facebook and Twitter!
			</p>
			
		     </div>		

		    </div>
	
		  	

	</div>
	<div class="rightBlock" id="right">
	</div>

</body>
</html>
