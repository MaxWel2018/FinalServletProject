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
    <title><fmt:message key="home.spec.registratioin"/></title>
    <c:import url="/views/Style.jsp"/>

</head>
<body>
<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">
        <form method="post">
        <p><fmt:message key="home.spec.registratioin"/></p>
            <c:forEach items="${specialityFounded.requiredCourses}" var="course">
                <p> ${course.name}</p>
                <label>
                    <input name="${course.id}" type="date" min="${specialityFounded.examsStart}" max="${specialityFounded.examsEnd}">
                </label>
            </c:forEach>
            <a type="submit" href="${pageContext.servletContext.contextPath}/home" class="btn btn-primary">Submit </a>
        </form>
    </div>

    <c:import url="/views/part/Footer.jsp"/>

</div>

</body>
</html>