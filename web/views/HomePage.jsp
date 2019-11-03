<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 02.11.2019
  Time: 21:14
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
    <title>HomePage</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/style/style.css">

<body>
<div class="flex-wrapper">
    <header>
        <form action="" class="flex-header">
            <select class="  select-size" id="language" name="language"
                    onchange="submit()" style="width: 150px;">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
            <a href="#"><fmt:message key="home.header.home"/></a>
            <a href="#"><fmt:message key="home.header.profile"/></a>
            <a href="#"><fmt:message key="home.header.resultExam"/></a>
            <a class=" btn btn-primary " href="${pageContext.servletContext.contextPath}/logOut"> <fmt:message
                    key="menu.button.logOut"/>
            </a>



        </form>

    </header>
    <div class="content">

        <c:forEach items="${spec}" var="sp">
            <p>${sp.getName()}</p>
            <p>${sp.getStudentsNumber()}</p>
            <br>
        </c:forEach>
    </div>
 <footer>
     © All Rights Reserved
 </footer>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
