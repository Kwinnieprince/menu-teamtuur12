<%--
  Created by IntelliJ IDEA.
  User: Nil Marti
  Date: 17/12/2018
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<%@ include file="components/navigation.jspf"%>

<!-- Page Content -->
<h2>

</h2> <br>
<div class="container">
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Student price</th>
            <th>External price</th>
            <th>Category</th>
        </tr>
        <c:forEach items="${dishes}" var="dish">
        <tr>
                <td>${dish.name}</td>
                <td>${dish.description}</td>
                <td>${dish.internalPrice}</td>
                <td>${dish.externalPrice}</td>
                <td>${dish.category}</td>

        </tr>
        </c:forEach>
    </table>
</div>

    <%@ include file="components/footer.jspf"%>
