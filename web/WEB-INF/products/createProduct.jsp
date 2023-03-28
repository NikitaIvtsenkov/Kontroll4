<%-- 
    Document   : createproduct
    Created on : Mar 6, 2023, 1:01:42 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3 class="w-100 mt-5 d-flex justify-content-center">Новый товар</h3>
        <div class="w-100 d-flex justify-content-center p-5">
            <div class="card" style="width: 40rem;">
                <div class="card-body">
                    <p class="w-100 d-flex justify-content-center p-5"><a href="addCover"><b>Добавить обложку для товара</b></a></p>
                    <form method="POST" action="createProduct">
                      <div class="mb-3 row">
                        <label for="productName" class="col-sm-5 col-form-label">Название товара </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="productName" name="name" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="brand" class="col-sm-5 col-form-label">Бренд </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="brand" name="brand" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="quantity" class="col-sm-5 col-form-label">Количество </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="quantity" name="quantity" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="cost" class="col-sm-5 col-form-label">Цена </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="cost" name="cost" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="razmer" class="col-sm-5 col-form-label">Размер </label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="razmer" name="razmer" value="">
                            </div>
                      </div>
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

                      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                          <button type="submit" class="btn btn-primary me-md-2">Добавить</button>
                      </div>

                    </form>
                </div>
            </div>
        </div>
    