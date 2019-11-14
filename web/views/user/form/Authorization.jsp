<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 03.11.2019
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.language}">
<head>
    <title>Authorization</title>
</head>
<c:import url="../../Style.jsp"/>
<body>
<div class="flex-wrapper">
    <c:import url="../../part/Header.jsp"/>
    <div class="content">
        <form class="regist-form" method="post" action="/user"
              style="color:white;  margin: 10px 0 0 30px;">
            <div class="form-group">
                <label for="email"> <fmt:message key="reg.email"/></label>
                <input type="email" autocomplete="new-username" class="form-control" name="email" id="email"
                       aria-describedby="emailHelp"
                       placeholder= <fmt:message key="reg.email"/>>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="reg.password"/></label>
                <input type="password" autocomplete="new-password" class="form-control" name="password" id="password"
                       placeholder=<fmt:message
                        key="reg.password"/>>
            </div>
            <label>
                <input type="text" class="hidden" name="command" value="login">
            </label>
            <button type="submit" class="btn btn-primary"><fmt:message key="entry.confirm"/></button>
            <div style="color: red; font-weight: bold; font-size: 20px;" class="${authFailed==null?'hidden':''}"><fmt:message key="auth.failed"/></div>
        </form>
    </div>
    <c:import url="../../part/Footer.jsp"/>
</div>
</body>
</html>
