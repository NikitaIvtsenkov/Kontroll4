<%-- 
    Document   : customerList
    Created on : Mar 6, 2023, 2:53:40 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 mt-5 d-flex justify-content-center">Customer list</h3>

<div class="w-100 d-flex justify-content-center p-5">
    <div class="card " style="width: 35rem">
        <ol>
            <c:forEach var="customer" items="${listCustomers}">
                <li>
                    ${customer.firstname} ${customer.lastname}. 
                </li>
            </c:forEach>
        </ol>
 </div>
</div>