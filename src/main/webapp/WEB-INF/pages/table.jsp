<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">City</th>
        <th scope="col">Temperature</th>
        <th scope="col">Created at</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${temperatures}" var="temperature">
        <tr>
            <td>${temperature.id}</td>
            <td>${temperature.cityName}</td>
            <td>${temperature.temperature}</td>
            <td><fmt:formatDate value="${temperature.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
    </c:forEach>

    </tbody>
</table>