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
    <c:import url="/views/Style.jsp"/>
</head>

<body>

<div class="flex-wrapper">

    <c:import url="/views/part/Header.jsp"/>
    <div class="content">

            <p style="font-size: 40px;
    color: #b3d7ff;; text-align: center;"><fmt:message key="home.chose.speciality"/></p>
            <form style="width: 100%;" method="get" action="${pageContext.servletContext.contextPath}/home">
                <label style="width: 100%;">
                    <input class="hidden" name="command" value="info">
                    <select name="selectedSpeciality" style="width: 50%; margin: 20px auto;"
                            class="selectpicker form-control "
                            data-style="btn-primary" title="Choose one of the following..."
                            onchange="submit()">
                        <c:forEach items="${speciality}" var="spec">
                            <option  ${specialityFounded.id == spec.id ? 'selected' : ''}
                                    value="${spec.id}"> ${spec.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </form>

            <div class=" spec-item ${specialityFounded==null? 'hidden':''}">

                <div class="hidden"> ${specialityFounded.id}</div>
                <label for="name"> <fmt:message key="home.content.speciality.name"/></label>
                <p style="color: #f8f9fa;font-size: 30px; font-weight: bold;" id="name"
                   class="speciality-name">${specialityFounded.name}</p>

                <label for="activity"> <fmt:message key="home.content.speciality.activity"/></label>
                <div id="activity" class="speciality-activity"> ${specialityFounded.activity}</div>
                <label for="background"> <fmt:message key="home.content.speciality.background"/> </label>
                <div id="background" class="speciality-background"> ${specialityFounded.background}</div>

                <label for="employments"><fmt:message key="home.content.speciality.employments"/> </label>
                <div id="employments" class="speciality-employments"> ${specialityFounded.employments}</div>

                <label class="speciality-courses-label " for="speciality-courses">Required Courses</label>
                <div id="speciality-courses" class="speciality-courses">
                    <c:forEach items="${specialityFounded.requiredCourses}" var="course">
                        <p> ${course.name}</p>
                    </c:forEach>
                </div>
                <form method="post" action="${pageContext.servletContext.contextPath}\">
                    <label>
                        <input type="text" class="hidden" name="id" value="${specialityFounded.id}">
                    </label>
                    <button type="submit" class="btn btn-primary"><fmt:message key="menu.button.registration"/></button>
                </form>
            </div>
        </div>


    <c:import url="/views/part/Footer.jsp"/>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
