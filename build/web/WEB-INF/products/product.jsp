<%-- 
    Document   : products
    Created on : Mar 6, 2023, 2:06:51 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
        <div class="w-100 d-flex justify-content-center p-5">

              <h5 class="card-title">${product.name}</h5>
            </div>
            <ul class="list-group list-group-flush">
              
              <li class="list-group-item">Price: ${product.price}</li>
              <li class="list-group-item">Quantity: ${product.quantity}</li>
              <img src="insertFile/${product.cover.url}" class="card-img-top" alt="...">
             
            </ul>
            <div class="card-body">
              <a href="#" class="card-link">Buy</a>
            </div>
         
       