<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.language}">

<head>
    <title>About</title>
    <c:import url="/views/Style.jsp"/>
    <style>
        span {
            color: white;

        }
    </style>
</head>

<body>

<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">
        <div style="
    background: #2b272738;
    margin: 10px;
    padding: 20px;
    min-height: 500px;
" class="information">
            <div style="color:#000000;   font-size: 20px;">
                <p style="text-align: center; font-size: 48px; border-bottom: 1px solid white; color: white;">
                    <fmt:message
                            key="profile.information"/></p>
                <p><fmt:message key="result.user.first.name"/>: <span>${user.firstName}</span></p>
                <p><fmt:message key="reg.secondName"/>: <span>${user.secondName}</span></p>
                <p><fmt:message key="reg.email"/>: <span>${user.email}</span></p>
                <div class="${userSpec==null?'hidden':''}">
                    <p><fmt:message key="profile.information.speciality"/>: <span> ${userSpec.name}</span></p>

                    <p style=" text-align: center;color: white;font-size: 33px; border-bottom: 1px solid white;">
                        <fmt:message key="profile.information.exams"/></p>
                    <div>
                        <c:forEach items="${examResults}" var="exRes">

                            <p>
                                    ${exRes.course.name}:
                                <span>
                                    <c:choose>
                                        <c:when test="${exRes.mark==null}">
                                            <fmt:message key="profile.information.exam"/>: ${exRes.date}
                                        </c:when>
                                        <c:otherwise>
                                            ${exRes.mark}
                                        </c:otherwise>
                                    </c:choose>
                                    </span>
                            </p>

                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/views/part/Footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>