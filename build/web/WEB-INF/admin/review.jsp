<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2  class="w-100 d-flex justify-content-center mt-5">Кол-во купленных товаров</h2>
<div class="w-100 d-flex justify-content-center mt-4">
    <div class="card border-0" style="width: 24rem;">
        <form action="showReview" method="POST">
            <h5>Год</h5> 
            <select class="form-select" name="year">
                <option selected disabled>Выберите год</option>
                <c:forEach var="y" items="${years}">
                    <option value="${y}">
                        ${y}
                    </option>
                </c:forEach>
            </select>
            <h5>Месяц</h5> 
           
            <select class="form-select" name="month">
                <option selected disabled>Выберите месяц</option>
                <c:forEach var="m" begin="0" end="11">
                    <option value="${m+1}">
                        ${m+1}
                    </option>
                    
                </c:forEach>
            </select>
            <h5>День:</h5> 
            
            <select class="form-select" name="day">
                <option selected disabled>Выберите день месяца</option>
                <c:forEach var="day" begin="1" end="31">
                    <option value="${day}">
                        ${day}
                    </option>
                   
                </c:forEach>
            </select>
            <p class="w-100 mt-5 d-flex justify-content-evenly">
                <input class="btn btn-secondary w-25" type="submit" name="action" value="Вычислить">
            </p>
        </form>
    </div>
</div>