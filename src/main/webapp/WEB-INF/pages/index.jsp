<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
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

        <div class="row mb-5">
            <div class="col">
                <select class="form-control" id="choose-country">
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.id}">${country.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <a href="/export" id="export" class="btn btn-block btn-primary" data-country-id="">Export</a>
            </div>
            <div class="col">
                <form method="post" action="/import" enctype="multipart/form-data">
                    <div class="input-group">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="file" id="file" accept=".csv">
                            <label class="custom-file-label" for="file">Choose file</label>
                        </div>
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">Import</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="row">
            <div class="col" id="redraw">

            </div>
        </div>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    var countryId;

    function redraw() {
        $.ajax({
            url: "/table/" + countryId,
        }).done(function (data) {
            $('#redraw').html(data);
        });
    }

    $('#file').change(function () {
        var fileName = $(this).val().replace('C:\\fakepath\\', " ");
        $(this).next('.custom-file-label').html(fileName);
    });

    $('#choose-country').change(function () {

        countryId = $(this).val();
        $('#export').attr('data-country-id', countryId);
        redraw();

    }).change();

    $('#export').click(function (e) {
        e.preventDefault();
        window.location = $(this).attr('href') + '/' + $(this).data('country-id');
        return false;
    })

    $(function () {
        setInterval(function () {
            redraw();
        }, 5000);
    });
</script>

</body>
</html>