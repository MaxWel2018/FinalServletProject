<%--
  Created by IntelliJ IDEA.
  User: krugo
  Date: 07.11.2019
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>

<html lang="${param.language}">
<head>
    <title>Results</title>
    <c:import url="/views/Style.jsp"/>

</head>
<body>
<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">

        <p style="font-size: 40px;
        color: #b3d7ff;; text-align: center;"><fmt:message key="result.chose.speciality"/></p>
        <form style="width: 100%;" method="get" action="/user/result">
            <label   style="width: 100%;">
                <input class="hidden" name="command" value="show-rating">
            <select name="selectedSpeciality" style="width: 50%; margin: 20px auto;"
                    class=" selectpicker form-control "
                    data-style="btn-primary" title="Choose one of the following..."
                    onchange="submit()">
                <c:forEach items="${speciality}" var="spec">
                    <option  ${select == spec.id ? 'selected' : ''}
                            value="${spec.id}"> ${spec.name}</option>
                </c:forEach>
            </select>
            </label>
        </form>

        <div>
            <table class="table table-striped table-responsive-md btn-table">
                <thead>
                <tr>
                    <th><fmt:message key="result.user.first.name"/></th>
                    <th><fmt:message key="result.user.last.name"/></th>
                    <th><fmt:message key="result.spec.name"/></th>
                    <th><fmt:message key="result.user.final.mark"/></th>
                    <th><fmt:message key="result.user.confirmed"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${rating}" var="rat">

                    <tr>
                        <td> ${rat.getUserName()}</td>
                        <td> ${rat.getUserSurName()}</td>
                        <td> ${rat.getSpecialityName()}</td>
                        <td> ${rat.getFinalMark()}</td>
                        <c:if test="${rat.getConfirmed()==false}">
                            <td>❌</td>
                        </c:if>
                        <c:if test="${rat.getConfirmed()==true}">
                            <td>✅</td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${countElement}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td><p class="btn btn-primary">${i}</p></td>
                            </c:when>
                            <c:otherwise>
                                <td><a class="btn btn-outline-success " style="color: black;"
                                       href="\user\result?command=show-rating&selectedSpeciality=${select}&page=${i}">${i}</a>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <c:import url="/views/part/Footer.jsp"/>

</div>

</body>
</html>
