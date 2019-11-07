<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 02.11.2019
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"  %>
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
<c:import url="/views/Style.jsp"/>

<body>
<div class="flex-wrapper">
    <header>

        <c:import url="/views/Header.jsp"/>

    </header>
    <div class="content">
        <c:forEach items="${spec}" var="sp">
            <p>${sp.name}</p>
            <p>${sp.studentsNumber}</p>
            <br>
        </c:forEach>
    </div>
 <footer>
        <fmt:message key="all.footer.rights"/>
 </footer>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
