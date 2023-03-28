<%-- 
    Document   : goProduct
    Created on : Mar 9, 2023, 12:52:38 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 d-flex justify-content-center">Product output</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0" style="width: 25rem;">
      <div class="card-body">
        <h5 class="card-title w-100">Product list</h5>
        <form action="purchase" method="POST">
            <p class="card-text w-100">
                <select name="productId" class="w-100">
                    <option selected disabled>Choose product</option>
                    <c:forEach var="product" items="${listProduct}">
                        <option value="${product.id}">${product.name} ${product.price}</option>
                           
                    </c:forEach>
                        
                        <input type="number" value="1" min="1" max="100" class="form-control" id="quantity" name="quantity" value=""> 
                       
                </select>
                    
            </p>
            
            <p class="card-text w-100 d-flex justify-content-end">
                <input type="submit" value="купить ">
            </p>
        </form>
           
      </div>
    </div>
</div>
        
