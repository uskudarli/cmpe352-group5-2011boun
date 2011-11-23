<%--
    Document   : MainWindow
    Created on : 09.Kas.2011, 15:10:01
    Author     : Bahtiyar Kaba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%-- Dummy javascript code--%>
<script type="text/javascript">
    function toggleButtonEvent(){
        alert("Change view/edit mode toggle:\n@TODO: Write the proper event handling code")
    }
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="schematizer.css">
        <title>Schematizer Main Page</title>
    </head>
    <body>
        <div id="main">
            <% String xxx=(String)request.getParameter("name");%>
            <form method="post" name="login" id="login" action="Servlet_Logout"> <input type="submit" value="Logout" id="lg" /></form>
        <div id="header">Welcome to Schematizer      User Logged-in: <%= xxx%>      
            </div>
            <div id="indexLeftColumn">
                <div id="messageArea"> Left click to start a route, right click to finish, right click again to start another ruoute. You can edit the route after you finish drawing.</div>
                <div id ="canvasArea">
                    <applet height="500" width="700"  code="schematizing_maps.CanvasApplet" archive="EditorApplet.jar"/>
                </div>
            </div>

            <div id="indexRightColumn">
                right column
            </div>

            <div id="footer">
                footer
            </div>
        </div>
    </body>
</html>
