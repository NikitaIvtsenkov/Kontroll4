<%-- 
    Document   : listproduct
    Created on : Mar 6, 2023, 1:01:19 PM
    Author     : pupil

<div class="w-100 d-flex justify-content-center p-5">
    <div class="card " style="width: 35rem">
        <ol>
             <c:forEach var="product" items="${listProduct}">
                <li>
                    <a href="product?id=${product.id}">
                    ${product.name}
                    <img src="insertFile/${product.cover.url}" class="card-img-top" alt="...">
                    </a>
                </li>
            </c:forEach>
        </ol>
 </div>
</div>
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 mt-5 d-flex justify-content-center">Products list</h3>

<div class="w-100 d-flex justify-content-center p-5">
   <c:forEach var="product" items="${listProduct}">
        <div class="card " style="width: 18rem">

            <a href="product?id=${product.id}">
            ${product.name}
            <img src="insertFile/${product.cover.url}" class="card-img-top" alt="...">
            </a>
        </div>
    </c:forEach>
</div>