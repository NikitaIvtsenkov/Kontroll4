<%-- 
    Document   : addCustomer
    Created on : Mar 6, 2023, 2:53:09 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <h3 class="w-100 mt-5 d-flex justify-content-center">New customer</h3>
        <form method="POST" action="registration">
            <p>Customer firstname: </p><input type="text" name="firstname" value=""><br>
            <p>Customer lastname:</p><input type="text" name="lastname" value=""><br>
            <p>Customer cash</p><input type="text" name="cash" value=""><br>
            <p>Password</p> <input type="password" name="password" value=""><br>
            <p>Login</p><input type="text" name="login" value=""><br>
            <input type="submit" value="Add">
        </form>


