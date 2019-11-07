<form action="" class="flex-header">
    <select class="  select-size" id="language" name="language"
            onchange="submit()" style="width: 120px;">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
    <a href="${pageContext.servletContext.contextPath}/views/HomePage.jsp">
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
        <a class=" btn btn-primary"
           href="${pageContext.servletContext.contextPath}/views/form/Registration.jsp">
            <fmt:message key="menu.button.registration"/>
        </a>
        <a class=" btn btn-primary"
           href="${pageContext.servletContext.contextPath}/views/form/Authorization.jsp">
            <fmt:message key="menu.button.signIn"/>
        </a>
        </c:otherwise>
        </c:choose>
</form>
