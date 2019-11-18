<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.language}">
<head>
    <title>Setting grades</title>
    <c:import url="/views/Style.jsp"/>
</head>

<body>

<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">


        <form style="width: 50%;margin:0 auto;display: flex;flex-direction: column;" method="get"
              action="/admin/profile"> <%--Пофиксить путь --%>
            <label style="width: 100%;">
                <input class="hidden" name="command" value="set-grades">
                <select name="courseSelected" style="width: 50%; margin: 20px auto;"
                        class=" selectpicker form-control "
                        data-style="btn-primary" title="Choose one of the following...">
                    <c:forEach items="${allCourses}" var="course">
                        <option  ${select == course.id ? 'selected' : ''} <%-- назначить селект в атрибут после выбора --%>
                                value="${course.id}"> ${course.name}</option>
                    </c:forEach>
                </select>
            </label>
            <label style="
    margin: 20px auto;
    width: 50%;
">
                <input required name="data-exam" style="
    width: 100%;
    margin: 0 auto;
" type="date">
            </label>
            <input class="btn-primary btn" type="submit" value="Submit">
        </form>
        <form  action="/admin/profile" method="post">
            <input type="text" class="hidden" name="command" value="update-grade">
        <c:if test="${allResult!=null}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Course</th>
                    <th scope="col">Date</th>
                    <th scope="col">Grade</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${allResult}" var="res">
                    <tr>
                        <input type="text" class="hidden" name="idRecord" value="${res.id}">
                        <th scope="row">${res.id}</th>
                        <td>${res.user.firstName}</td>
                        <td>${res.user.secondName}</td>
                        <td>${res.course.name}</td>
                        <td>${res.date}</td>
                        <td><input type="text" name="grade" value="${res.mark}"></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <input class="btn btn-primary" type="submit">
        </form>

        </c:if>

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