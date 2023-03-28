<%-- 
    Document   : login
    Created on : Mar 9, 2023, 1:07:23 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="w-100 d-flex justify-content-center mt-5">Login</h2>
<div class="w-100 d-flex justify-content-center mt-5">
    <div class="card p-2" style="width: 35rem;">
      <div class="card-body">
        <form action="enter" method="POST">
          <div class="my-2 row">
            <label for="inputLogin" class="col-sm-4 col-form-label d-flex justify-content-end">Логин</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="login" id="inputLogin" value="">
            </div>
          </div>
          <div class="mb-2 row">
            <label for="inputPassword" class="col-sm-4 col-form-label d-flex justify-content-end">Password</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" name="password" id="inputPassword">
            </div>
          </div>
          <div class="mt-3 p-2 row d-flex justify-content-end">
            <input type="submit" class="btn btn-secondary col-sm-4" value="Войти" id="inputEnter">
          </div>
        </form>
          <div class="mb-2 row">
              <p class="text-info w-100 d-flex justify-content-end"><a href="newCustomer">Register</a></p>
          </div>
      </div>
    </div>
</div>