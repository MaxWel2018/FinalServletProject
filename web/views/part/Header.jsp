<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="internationalization"/>
<header>
<form method="post"  class="flex-header">
    <select   class=" form-control select-size" id="language" name="language"
            onchange="submit()" style="width: 120px;">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
    <a href="${pageContext.servletContext.contextPath}/home?command=info">
        <fmt:message key="home.header.home"/>
    </a>

    <a href="${pageContext.servletContext.contextPath}/profile"><fmt:message key="home.header.profile"/></a>
        <a  href="${pageContext.servletContext.contextPath}/result?command=show-speciality"><fmt:message key="home.header.resultExam"/></a>
    <c:choose>
    <c:when test="${isLogin==true}">
        <a class=" btn btn-primary " href="${pageContext.servletContext.contextPath}/logOut"> <fmt:message
                key="menu.button.logOut"/></a>
    </c:when>
    <c:otherwise>
    <div class="menu-menu">
        <a class=" btn btn-primary"
           href="${pageContext.servletContext.contextPath}/views/form/Registration.jsp">
            <fmt:message key="menu.button.registration"/>
        </a>
        <a  class=" btn btn-primary"
           href="${pageContext.servletContext.contextPath}/views/form/Authorization.jsp">
            <fmt:message key="menu.button.signIn"/>
        </a>
        </c:otherwise>
        </c:choose>
</div>
</header>