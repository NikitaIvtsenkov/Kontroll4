<%-- 
    Document   : addCustomer
    Created on : Mar 6, 2023, 2:53:09 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3 class="w-100 mt-5 d-flex justify-content-center">Новый пользователь</h3>
        <div class="w-100 d-flex justify-content-center p-5">
            <div class="card" style="width: 40rem;">
                <div class="card-body">
             
                    <form method="POST" action="registration">
                      <div class="mb-3 row">
                        <label for="firstname" class="col-sm-5 col-form-label">Имя: </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="firstname" name="firstname" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="lastname" class="col-sm-5 col-form-label">Фамилия: </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="lastname" name="lastname" value="">
                        </div>
                      </div>
                         <div class="mb-3 row">
                        <label for="phone" class="col-sm-5 col-form-label"> телефон  </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="password" name="phone" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="login" class="col-sm-5 col-form-label"> Логин: </label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="login" name="login" value="">
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="password" class="col-sm-5 col-form-label"> Пароль:  </label>
                        <div class="col-sm-7">
                          <input type="password" class="form-control" id="password" name="password" value="">
                        </div>
                      </div>
                     
                      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                          <button type="submit" class="btn btn-primary me-md-2">Добавить</button>
                      </div>

                    </form>
                </div>
            </div>
        </div>


