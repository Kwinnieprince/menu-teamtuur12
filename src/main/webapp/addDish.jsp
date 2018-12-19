<%--
  Created by IntelliJ IDEA.
  User: tvermeersch
  Date: 12/18/18
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<%@ include file="components/navigation.jspf"%>

<!-- Page Content -->

<html>
<head>
    <title>Add Dish</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1 id="pageTitle" class="text-center login-title">Add a dish</h1>
            <div class="account-wall">
                <img class="profile-img" src="/static/css/user.png" alt="">
                <form class="form-signup" method="post" action="add">
                    <c:forEach items="${errors}" var="error">
                        <p class="error">${error}</p>
                    </c:forEach>
                    <div class="form-group">
                        <input id="name" type="text" class="form-control" name="name" value="${name}" placeholder="Name" required autofocus>
                    </div>
                    <div class="form-group">
                        <input id="description" type="text" class="form-control" name="description" value="${description}" placeholder="Description" required>
                    </div>
                    <div class="form-group">
                        <input id="internalPrice" type="number" step="any" min="0" class="form-control" name="internalPrice" value="${internalPrice}" placeholder="Internal Price" required>
                    </div>
                    <div class="form-group">
                        <input id="externalPrice" type="number" step="any" min="0" class="form-control" name="externalPrice" value="${externalPrice}" placeholder="External Price" required>
                    </div>
                    <div class="form-group">
                        <select id="category" class="form-control" name="category" value="${externalPrice}" required>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat}">${cat}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button id="submitButton" class="btn btn-lg btn-primary btn-block" type="submit">Add Item</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<%@ include file="components/footer.jspf"%>

</body>
</html>
