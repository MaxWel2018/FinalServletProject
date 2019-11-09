<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 07.11.2019
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.lang}">
<head>
    <title>Profile</title>
    <c:import url="/views/Style.jsp"/>

</head>
<body>
<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">


        <div class="${show==false?'hidden':''}">
            <table class="table table-striped table-responsive-md btn-table">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Speciality name</th>
                    <th>Final Mark</th>
                    <th>Confirmed</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${rating}" var="rat">

                    <tr>
                        <td> ${rat.getUserName()}</td>
                        <td> ${rat.getUserSurName()}</td>
                        <td> ${rat.getSpecialityName()}</td>
                        <td> ${rat.getFinalMark()}</td>
                        <td> ${rat.getConfirmed()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table  cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${countElement}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td ><p class="btn btn-primary" >${i}</p></td>
                            </c:when>
                            <c:otherwise>
                                <td ><a class="btn btn-outline-success " style="color: black;" href="${pageContext.servletContext.contextPath}\profile?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <c:import url="/views/part/Footer.jsp"/>

</div>

</body>
</html>
