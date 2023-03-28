<%-- 
    Document   : createproduct
    Created on : Mar 6, 2023, 1:01:42 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <h3 class="w-100 mt-5 d-flex justify-content-center">New product</h3>
        <form method="POST" action="createProduct">
            <p>Product name: </p><input type="text" name="name" value=""><br>
            <p>Product quantity:</p><input type="text" name="quantity" value=""><br>
            <p>Product price:</p><input type="text" name="price" value=""><br>
            <p class="w-100 d-flex justify-content-center p-5"><a href="addCover">Добавить обложку для товара</a></p>
                    <div class="mb-3 row">
                        <label for="covers_select" class="col-sm-5 col-form-label justify-content-md-end">Обложки:</label>
                        <div class="col-sm-7">
                            <select name="coverId" id="covers_select" class="form-select">
                                <option value="#" selected disabled>Выберите обложку</option>
                                <c:forEach var="cover" items="${listCovers}">
                                    <option value="${cover.id}">${cover.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                      </div>
            <input type="submit" value="Add">
        </form>

