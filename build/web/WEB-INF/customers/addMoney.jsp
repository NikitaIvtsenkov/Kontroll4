<%-- 
    Document   : addCash
    Created on : Mar 9, 2023, 2:39:08 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 d-flex justify-content-center">Пополнить баланс</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0" style="width: 25rem;">
      <div class="card-body">
        
        <form action="doMoney" method="POST">
            <p class="card-text w-100">
  
                    <option selected disabled>Выберите сколько хотите пополнить</option>  
                        <input type="number" value="0" min="1"  class="form-control" id="addMoney" name="addMoney" value=""> 
            </p>
            
            <p class="card-text w-100 d-flex justify-content-end">
                <input type="submit" value="Добавить">
            </p>
        </form>
           
      </div>
    </div>
</div>