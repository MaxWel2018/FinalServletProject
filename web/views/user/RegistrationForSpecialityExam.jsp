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
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>



<html lang="${param.language}">

<head>
    <title><fmt:message key="home.spec.registratioin"/></title>
    <c:import url="/views/Style.jsp"/>

</head>
<body>
<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">
        <form method="post" action="${pageContext.servletContext.contextPath}/home?command=exam-registration">
        <p style="text-align: center; font-size: 48px; font-weight: bold;"><fmt:message key="home.spec.registratioin"/>(<span style="color: white">${specialityFounded.name}</span>)</p>
        <p style="text-align: center; font-size: 26px;"><fmt:message key="home.spec.registratioin.description"/></p>
            <c:forEach items="${specialityFounded.requiredCourses}" var="course">
                <p style="font-size: 20px;font-weight: 600"> ${course.name}</p>
                <label>
                    <input name="${course.id}" type="date" min="${specialityFounded.examsStart}" max="${specialityFounded.examsEnd}">
                </label>
            </c:forEach>
            <p></p>
            <input type="submit" class="btn btn-primary" value=<fmt:message key="home.exam.submit"/>>
        </form>
    </div>

    <c:import url="/views/part/Footer.jsp"/>

</div>

</body>
</html>