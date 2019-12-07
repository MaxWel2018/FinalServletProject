<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="internationalization"/>
<header>
    <form method="post" class="flex-header">
        <select class=" form-control select-size" id="language" name="language"
                onchange="submit()" style="width: 120px;">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
        </select>
        <a href="/user/home?command=info">
            <fmt:message key="home.header.home"/>
        </a>
        <a  id ="profile" href="#"><fmt:message key="home.header.profile"/></a>

        <script type="text/javascript">
            document.getElementById("profile").onclick = function() {
                if(${user.role == "ADMIN"}) {
                    document.getElementById("profile").href="/admin/profile?command=show-profile";
                }else {
                    document.getElementById("profile").href="/user/profile?command=show-profile";
                }
            }

      </script>

        <a href="/user/result?command=show-speciality"><fmt:message
                key="home.header.resultExam"/></a>
        <c:choose>
            <c:when test="${isLogin==true}">
                <a class=" btn btn-primary " href="/user?command=logout"> <fmt:message
                        key="menu.button.logOut"/></a>
            </c:when>
            <c:otherwise>
                <div class="menu-menu">
                    <a class=" btn btn-primary"
                       href="/user?command=show-register">
                        <fmt:message key="menu.button.registration"/>
                    </a>
                    <a class=" btn btn-primary"
                       href="/user?command=show-auth">
                        <fmt:message key="menu.button.signIn"/>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</header>