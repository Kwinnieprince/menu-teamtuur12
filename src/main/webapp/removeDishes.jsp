<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<%@ include file="components/navigation.jspf"%>


<!-- Page Content -->
<div class="container">
  <div class="col-md-8 well">
    <br>
    <h2>Verwijder een gerecht</h2>
    <div class="row">
      <div class="col-sm">
        <form method="post">
          <ul style="list-style: none" class="table">
            <c:forEach items="${dishes}" var="dish" >
              <div>
                <li>
                  <input type="checkbox" id="dish-${dish.id}" name="id[]" value="${dish.id}" />
                  <label for="dish-${dish.id}" ><c:out value="${dish.name}"/> </label>
                </li>
              </div>
            </c:forEach>
          </ul>
          <button id="submitButton" class="btn btn-lg btn-primary btn-block" type="submit">Verwijder geselecteerde gerechten</button>
          <br>
        </form>
      </div>
    </div>
  </div >
</div>

<!-- footer -->
<%@ include file="components/footer.jspf"%>
