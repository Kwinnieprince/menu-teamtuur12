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

<c:choose>
    <c:when test="${cookie['language'].value == 'en'}">
        <%@ include file="lan/en/weekMenuEn.jspf"%>
    </c:when>

    <c:when test="${cookie['language'].value == 'nl'}">
        <%@ include file="lan/nl/weekMenuNl.jspf"%>
    </c:when>

    <c:otherwise>
        <div class="container">
            <div class="row">
                <div class="col-md-8 well">
                    <h2 id="pageTitle"><br>Weekmenu campus {Campus ...}</h2>
                    <form method="POST" action="/index/cookies">
                        <p>
                            <label for="nl"><input type="radio" name="language" value="nl" id="nl">Nederlands</label>
                            <label for="en"><input type="radio" name="language" value="en" id="en">English</label>
                            <br>
                            <input type="submit" id="language" value="Send">
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="components/footer.jspf"%>
