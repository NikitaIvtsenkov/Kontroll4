<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h5 class="w-100 d-flex justify-content-center mt-5">Рейтинг за ${period}</h5>
<div class="w-100 d-flex justify-content-center mt-4">
    <div class="card border-0" style="width: 24rem;">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">№</th>
              <th scope="col">Название товара</th>
              <th scope="col">Количество<br>купленных товаров</th>
              
            </tr>
          </thead>
          <tbody>
            <c:forEach var="entry" items="${mapBooksRange}" varStatus="status">
            <tr>
              <th scope="row">${status.index + 1}</th>
              <td>${entry.key.name}</td>
              <td>${entry.value}</td>

            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>