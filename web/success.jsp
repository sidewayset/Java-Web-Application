<%-- 
    Document   : success
    Created on : Oct 19, 2013, 5:39:36 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        Name: ${requestScope.ann.ann}
        <form action="MySqlController" method="POST">
       Read My Records: <input type="submit" name="read" value="Read Records"><br>
        </form>
    </body>
</html>
