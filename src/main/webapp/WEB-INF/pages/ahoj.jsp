<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

    <c:forEach items="${countries}" var="country">
        <p>${country.name}</p>
    </c:forEach>


    <c:forEach items="${temperatures}" var="temperature">
        <p>${temperature.cityName}</p>
    </c:forEach>

</body>
</html>