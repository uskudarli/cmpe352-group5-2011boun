<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<script language = "JavaScript" src = "jquery-1.6.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
		$("#lg").click(function() { 
			$("#logout").submit();		
		});
    });	
    function toggleButtonEvent(){
        alert("Change view/edit mode toggle:\n@TODO: Write the proper event handling code")
    }
</script>


<html>
    <div id="imageLefty">
    </div>
    <div id="imageRighty">
    </div>		
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="schematizer.css">
        <title>Schematizer Main Page</title>
    </head>
    <body background="Resim3.jpg">	
        <div id="main">

        <div id="logoutBox">
            <form method="post" name="logout" id="logout" action="Servlet_Logout"> 
		<% String xxx=(String)request.getParameter("name");%>
            Welcome  <%= xxx%>		
		<span id="lg" class="topButton">Logout</span>
	    </form>	
	    		
	</div>
    
        <div id="header">      
            </div>
            <div id="indexLeftColumn">
                <div id="messageArea"> Left click to add points. Select a source then a destination point to make a connection </div>
                <div id ="canvasArea">
                    <applet height="525" width="800"  code="applet_algorithm.CanvasApplet" archive="EditorApplet2.jar"/>
                </div>
            </div>

            

            <div id="footer">
                footer
            </div>
        </div>
    </body>
</html>
