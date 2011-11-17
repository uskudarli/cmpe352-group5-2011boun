<%-- 
    Document   : index
    Created on : Nov 16, 2011, 10:27:46 PM
    Author     : px5x2
--%>

<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
                                <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
	</head>

	<body>

  <form method="post" name="login" action="">
      <br>
      
      </br>
      <br></br>
      <br></br>
      
      <table align="center">
          <tr>
              <td>
          <h>
              WELCOME TO SCHEMATIZER
              <br></br>
              </h>
              </td>
          </tr>
          
          <tr>
              <td>
        User Name:          
              </td>
              <td>
            <input type="text" name="username" id="usrname" /><br />      
              </td>
          </tr>
          <tr>
              <td>
      Password :         
              </td>
              <td>
         <input type="password" name="password" id="passwd" /><br />     
              </td>
              <td>
                  <form> 
             <INPUT TYPE="radio" NAME="radios" VALUE="radio1" CHECKED>
            Advanced
            <BR>
            <INPUT TYPE="radio" NAME="radios" VALUE="radio2">
            Simple
            </form>
              </td>
          </tr>
          <tr>
              <td>
   
	<input type="submit" value="Login" action="SM_Servlet"/>    
          <br> </br>
          <br></br>
              </td>
        
          </tr>
          <tr>
              <td>
                  
              </td>
              <td>
              </td>
              <td>
                  <a href="login2.jsp"><input type="button" value="New User" name="New User"/></a>
                   
              </td>
          </tr>
          
      </table>
	
	
	
  </form>		


</body>
</html>