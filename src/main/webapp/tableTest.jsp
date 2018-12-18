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

<div class="container">
    <div class="row">
        <div class="col-md-8 well">
            <h2 id="pageTitle">Menu campus {Campus ...}</h2> <!-- TODO get campus from enum -->
        </div>
    </div>
    <div class="row">
        <article class="col-sm">
            <table class="table">
                <thead>
            <tr>
                <th scope="col">Dag van de week</th>
                <th scope="col">Maandag</th>
                <th scope="col">Dinsdag</th>
                <th scope="col">Woensdag</th>
                <th scope="col">Donderdag</th>
                <th scope="col">Vrijdag</th>
            </tr>
            </thead>
                <thead>
                <tr>
                    <th scope="col">Dag</th>
                    <th scope="col">17/12</th>
                    <th scope="col">18/12</th>
                    <th scope="col">19/12</th>
                    <th scope="col">20/12</th>
                    <th scope="col">21/12</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">Soep</th>
                    <td colspan="5" style="text-align: center">Tomatensoep</td>
                </tr>
                <tr>
                    <th scope="row">Soep</th>
                    <td>wortel-gembersoep</td>
                    <td>Champignonsoep</td>
                    <td>witloofsoep</td>
                    <td>preisoep</td>
                    <td>Minestrone</td>
                </tr>
                <tr>
                    <th scope="row">Pasta</th>
                    <td colspan="5" style="text-align: center">Pasta bolognaise</td>
                </tr>
                <tr>
                    <th scope="row">Dagschotel</th>
                    <td>Nasi goreng</td>
                    <td>Cordon bleu</td>
                    <td>Vegetarische lasagne</td>
                    <td>Verse gyros</td>
                    <td>tortelini</td>
                </tr>
                <tr>
                    <th scope="row">Vis van de dag</th>
                    <td>Pladijs</td>
                    <td>Visspies</td>
                    <td>Zalmfilet</td>
                    <td>Koolvis</td>
                    <td>Papilotte</td>
                </tr>
                <tr>
                    <th scope="row">Snacks</th>
                    <td colspan="5" style="text-align: center">koninginnenhapje - Rundstoofvlees - balletjes in tomatensaus</td>
                </tr>
                </tbody>
            </table>
        </article>
        <aside class="col-sm">
            <p>This is aside</p>
        </aside>
    </div>
</div>

<%@ include file="components/footer.jspf"%>
