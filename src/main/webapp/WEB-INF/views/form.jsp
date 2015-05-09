<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/assets/css/style.css">
    <title>XML parser</title>
</head>
<body>
    <c:if test="${not empty error}">
        <div class="error">
            <spring:message code="${error}"/>
        </div>
    </c:if>

    <div class="main">
        <form action="/process" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><spring:message code="choose_file"/></td>
                    <td><input type="file" name="file"></td>
                </tr>
                <tr>
                    <td><spring:message code="choose_action"/></td>
                    <td>
                        <select onchange="hideOptions();" id="action" name="action">
                            <option value="statistics"><spring:message code="statistics"/></option>
                            <option value="customers"><spring:message code="customers"/></option>
                        </select>
                    </td>
                </tr>
                <tr id="amount">
                    <td><spring:message code="min_amount"/></td>
                    <td><input type="number" min="0" value="0" name="amount"></td>
                </tr>
            </table>
            <p>
                <input type="submit" value="<spring:message code="submit"/>" name="submit">
            </p>
        </form>
    </div>
    <script src="/assets/js/script.js"></script>
</body>
</html>