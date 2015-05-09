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
        <c:if test="${action == 'statistics'}">
            <spring:message code="max_client"/> ${result.maxTotalOrdersAmountCustomer.name} (id ${result.maxTotalOrdersAmountCustomer.id})<br>
            <spring:message code="total_orders"/> ${result.totalOrders}<br>
            <spring:message code="total_amount"/> ${result.totalAmount}<br>
            <spring:message code="max_order"/> ${result.maxOrderAmount}<br>
            <spring:message code="min_order"/> ${result.minOrderAmount}<br>
            <spring:message code="avr_order"/> ${result.averageOrderAmount}<br>
        </c:if>
        <c:if test="${action == 'customers'}">
            <c:forEach items="${result.selectedCustomers}" var="customer">
                <p>${customer.name} (id ${customer.id})</p>
            </c:forEach>
            <c:if test="${empty result.selectedCustomers}">
                <spring:message code="empty_customers"/>
            </c:if>
        </c:if>

        <p>
            <form action="/" method="post">
                <input type="submit" value="<spring:message code="one_more"/>" name="submit">
            </form>
        </p>
    </div>

</body>
</html>