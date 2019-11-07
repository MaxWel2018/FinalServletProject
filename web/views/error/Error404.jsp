<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 03.11.2019
  Time: 23:05
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
    <title>Error 404</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/style/style.css">

<body>
<div class="flex-wrapper">
    <header>
        <form action="" class="flex-header">
            <select class="  select-size" id="language" name="language"
                    onchange="submit()" style="width: 120px;">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
            <a  href="${pageContext.servletContext.contextPath}/views/HomePage.jsp">
                <fmt:message key="home.header.home"/>
            </a>
            <a href="#"><fmt:message key="home.header.profile"/></a>
            <a href="#"><fmt:message key="home.header.resultExam"/></a>

            <c:choose>
            <c:when test="${isUser==true}">
                <a class=" btn btn-primary " href="${pageContext.servletContext.contextPath}/logOut"> <fmt:message
                        key="menu.button.logOut"/></a>
            </c:when>
            <c:otherwise>
            <div class="menu-menu">
                <a   class=" btn btn-primary" href="${pageContext.servletContext.contextPath}/views/form/Registration.jsp">
                    <fmt:message key="menu.button.registration"/>
                </a>
                <a class=" btn btn-primary" href="${pageContext.servletContext.contextPath}/views/form/Authorization.jsp">
                    <fmt:message key="menu.button.signIn"/>
                </a>
                </c:otherwise>
                </c:choose>
        </form>

    </header>
    <div  class="content ">
<div class="error404">
    404
    <br>
   <span>Page Not Found </span>
</div>
       <div class="img404"></div>
    </div>
    <footer>
        <fmt:message key="all.footer.rights"/>
    </footer>
</div>
</body>
</html>
