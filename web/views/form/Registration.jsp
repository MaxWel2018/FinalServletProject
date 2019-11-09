<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 03.11.2019
  Time: 21:24
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
    <title>Registration</title>
</head>
<c:import url="../Style.jsp"/>

<body>
<div class="flex-wrapper">
        <c:import url="../part/Header.jsp"/>
    <div class="content">

        <form class="regist-form" method="post" action="${pageContext.servletContext.contextPath}/login"
              style="color:white; width: 20%; margin: 10px 0 0 30px">
            <label>
                <input class="hidden" name="command" value="register">
            </label>
            <div class="form-group">
                <label for="email"> <fmt:message key="reg.email"/></label>
                <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                       placeholder=<fmt:message key="reg.email"/>>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="reg.password"/></label>
                <input type="password" class="form-control" name="password" id="password" placeholder=<fmt:message
                        key="reg.password"/>>
            </div>
            <div class="form-group">
                <label for="confirm-password"><fmt:message key="reg.confiPassword"/></label>
                <input type="password" class="form-control" name="confirmPassword" id="confirm-password" placeholder=
                <fmt:message key="reg.confiPassword"/>>
            </div>
            <div class="form-group">
                <label style="color: black" for="name"><fmt:message key="reg.name"/></label>
                <input type="text" class="form-control" name="name" id="name" placeholder=<fmt:message key="reg.name"/>>
            </div>
            <div class="form-group">
                <label style="color: black" for="secondName"><fmt:message key="reg.secondName"/></label>
                <input type="text" class="form-control" name="secondName" id="secondName" placeholder=<fmt:message
                        key="reg.secondName"/>>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="reg.regestration"/></button>
        </form>
    </div>
        <c:import url="../part/Footer.jsp"/>
</div>
</body>
</html>
