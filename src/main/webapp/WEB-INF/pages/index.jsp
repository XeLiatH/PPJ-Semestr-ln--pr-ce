<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Getting Started: Serving Web Content</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

    <section class="my-5">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1 class="text-center mb-5">Spring boot Weather API</h1>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <c:forEach items="${countries}" var="country">
                        <h5>${country.name}</h5>
                        <hr>

                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">City</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${country.cities}" var="city">
                                    <tr>
                                        <td>${city.name}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </c:forEach>

                </div>
            </div>
        </div>
    </section>

</body>
</html>