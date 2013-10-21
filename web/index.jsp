<%-- 
    Document   : index
    Created on : Oct 19, 2013, 3:27:33 PM
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
        
        <form action="MySqlController" method="POST">
            
            Delete Table: <input type="text" name="table"><br> 
            <input type="submit" name="delete" value="Delete Table"/><br> 
            Create Table:<input type="text" name="tablename"><br> 
            <input type="submit" name="create" value="Create Table"><br> 
            Insert Record:<br> 
           ID: <input type="text" name="id"><br> 
           First Name: <input type="text" name="firstName"><br> 
           Last Name :<input type="text" name="lastName"><br> 
           Age       : <input type="text" name="age"><br> 
           <input type="submit" name="insert" value="Insert Data"> <br>  
           Update Record:<br>
           Table:<input type="text" name="updateTable"><br>
           Update Data: <br>
           <input type="radio" name="data" value="id">ID<br>
           <input type="radio" name="data" value="first">First Name<br>
           <input type="radio" name="data" value="last">Last Name<br>
           <input type="radio" name="data" value="age">Age<br>
           To:<br>
           <input type="text" name="newData"><br>
           In ID:
           <input type="text" name="inID"><br>
           <input type="submit" name="update" value="Update Data"><br>
        <form
    </body>
</html>
