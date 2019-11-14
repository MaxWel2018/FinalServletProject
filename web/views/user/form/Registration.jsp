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
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.language}">

<head>
    <title>Registration</title>
</head>
<c:import url="../../Style.jsp"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
    function checkPasswordMatch() {
        var password = $("#password").val();
        var confirmPassword = $("#confirm-password").val();

        if (password != confirmPassword)
            $("#divCheckPasswordMatch").html("Passwords do not match!").css('color', 'red');
        else
            $("#divCheckPasswordMatch").html("Passwords match.").css('color', 'green');
    }

    $(document).ready(function () {
        $("#password, #confirm-password").keyup(checkPasswordMatch);
    });
</script>
<body>

<div class="flex-wrapper">
        <c:import url="../../part/Header.jsp"/>
    <div class="content">

        <form class="regist-form" method="post" action="${pageContext.servletContext.contextPath}/user"
              style="color:white; width: 20%; margin: 10px 0 0 30px">
            <label>
                <input class="hidden" name="command" value="register">
            </label>
            <div class="form-group">
                <label for="email"> <fmt:message key="reg.email"/></label>
                <input type="email" value="${email}" pattern="[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                       placeholder=<fmt:message key="reg.email"/>>
                <div style="color: red;font-weight: bolder;" class=" ${isAlreadyExist==null?"hidden":' ' }"><fmt:message key="user.exists"/></div>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="reg.password"/></label>
                <input type="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{2,32}" class="form-control" name="password" id="password" placeholder=<fmt:message
                        key="reg.password"/>>
            </div>
            <div class="form-group">
                <label style="color: black;" for="confirm-password"><fmt:message key="reg.confiPassword"/></label>
                <input  type="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{2,32}" class="form-control" name="confirmPassword" id="confirm-password" placeholder=
                <fmt:message key="reg.confiPassword"/>>
                <div style="font-weight: bold;" id="divCheckPasswordMatch"></div>
            </div>
            <div class="form-group">
                <label style="color: black" for="name"><fmt:message key="reg.name"/></label>
                <input type="text"  value="${firstName}" pattern="[A-Za-zА-Яа-яёЁ]{2,200}" class="form-control" name="name" id="name" placeholder=<fmt:message key="reg.name"/>>
            </div>
            <div class="form-group">
                <label style="color: black"  for="secondName"><fmt:message key="reg.secondName"/></label>
                <input type="text" value="${secondName}" pattern="[A-Za-zА-Яа-яёЁ]{2,200}" class="form-control" name="secondName" id="secondName" placeholder=<fmt:message
                        key="reg.secondName"/>>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="reg.regestration"/></button>
        </form>
    </div>
        <c:import url="../../part/Footer.jsp"/>
</div>
</body>
</html>
