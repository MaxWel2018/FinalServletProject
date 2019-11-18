<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<html lang="${param.language}">
<head>
    <title>Enroll Users</title>
    <c:import url="/views/Style.jsp"/>
</head>

<body>

<div class="flex-wrapper">
    <c:import url="/views/part/Header.jsp"/>
    <div class="content">


        <form style="width: 100%;" method="get" action="/admin/profile">
            <label   style="width: 100%;">
                <input class="hidden" name="command" value="enroll">
                <select name="selectedSpeciality" style="width: 50%; margin: 20px auto;"
                        class=" selectpicker form-control "
                        data-style="btn-primary" title="Choose one of the following..."">
                    <c:forEach items="${speciality}" var="spec">
                        <option  ${select == spec.id ? 'selected' : ''}
                                value="${spec.id}"> ${spec.name}</option>
                    </c:forEach>
                </select>
            </label>
            <input class="btn-primary btn" type="submit" value="Submit">
        </form>


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