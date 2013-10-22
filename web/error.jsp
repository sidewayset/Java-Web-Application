<%-- 
    Document   : error
    Created on : Oct 20, 2013, 1:51:11 PM
    Author     : Side
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
            
        </style>
    </head>
    <body>
        <h1>Error Detected</h1>
        
        ${requestScope.ann.ann}
        <form action="MySqlController" method="POST">
            
            Read My Records: <input type="submit" name="read" value="Read Records"><br>
        </form>
    </body>
</html>
