<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">
    <title>XML parser</title>
</head>
<body>

    <c:if test="${not empty error}">
        <div class="error">
            <spring:message code="${error}"/>
        </div>
    </c:if>

    <div class="main">
        <c:if test="${empty error}">
            no error!
        </c:if>

        <form action="/" method="post">
            <input type="submit" value="<spring:message code="one_more"/>" name="submit">
        </form>
    </div>

</body>
</html>