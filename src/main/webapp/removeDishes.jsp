<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<%@ include file="components/navigation.jspf"%>


<!-- Page Content -->
<form method="post">
  <ul>
    <c:forEach items="${dishes}" var="dish" >
      <div>
        <li>
          <input type="checkbox" id="dish-${dish.id}" name="id[]" value="${dish.id}" />
          <label for="dish-${dish.id}" ><c:out value="${dish.name}"/> </label>
        </li>
      </div>
    </c:forEach>
  </ul>
  <button id="submitButton" type="submit">Remove selected dishes</button>
</form>
<!-- footer -->
<%@ include file="components/footer.jspf"%>
