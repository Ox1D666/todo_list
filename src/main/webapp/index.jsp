<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>todo</title>
    <style type="text/css">
        .shadowbox {
            width: 15em;
            border: 1px solid #333;
            box-shadow: 8px 8px 5px #444;
            padding: 8px 12px;
            background-image: linear-gradient(180deg, #fff, #ddd 40%, #ccc);
            width: 1100px;
        }
    </style>
    <script>
        function add() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/todo_list/show.do',
                data: {'desc': $('#desc').val(), 'user_id': <%=session.getAttribute("user_id")%>},
                dataType: 'text',
                success: function (result) {
                    var items = $.parseJSON(result);
                    $("#table").find("td").remove();
                    let table = $('#table');
                    items.forEach(el => {
                        table.append('<tbody>')
                        table.append('<tr>')
                        table.append('<td>' + el.description + '</td>')
                        table.append('<td>' + el.create + '</td>')
                        table.append('<td>' + el.done + '</td>')
                        table.append('<td>' + el.user.login + '</td>')
                        table.append('</tr>')
                        table.append('</tbody>')
                    });
                }
            })
        }

        function getCategory() {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/todo_list/category.do',
                success: function (result) {
                    var items = $.parseJSON(result);
                    let select = $('#categories');
                    items.forEach(el => {
                        select.append($("<option></option>")
                            .text(el['name']));

                    })
                }
            })
        }

        function showChecked() {
            if ($('#checkbox').is(':checked')) {
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/todo_list/show.do',
                    data: 'done=' + 'all',
                    success: function (result) {
                        var items = $.parseJSON(result);
                        $("#table").find("td").remove();
                        let table = $('#table')
                        items.forEach(el => {
                            table.append('<tbody>')
                            table.append('<tr>')
                            table.append('<td>' + el.description + '</td>')
                            table.append('<td>' + el.create + '</td>')
                            table.append('<td>' + el.done + '</td>')
                            table.append('<td>' + el.user.login + '</td>')
                            table.append('</tr>')
                            table.append('</tbody>')
                        });
                    }
                })
            } else {
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/todo_list/show.do',
                    data: 'done=' + 'false',
                    success: function (result) {
                        var items = $.parseJSON(result);
                        $("#table").find("td").remove();
                        let table = $('#table')
                        items.forEach(el => {
                            table.append('<tbody>')
                            table.append('<tr>')
                            table.append('<td>' + el.description + '</td>')
                            table.append('<td>' + el.create + '</td>')
                            table.append('<td>' + el.done + '</td>')
                            table.append('<td>' + el.user.login + '</td>')
                            table.append('</tr>')
                            table.append('</tbody>')
                        });
                    }
                })
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="navbar shadowbox">
        <div class="mx-auto"><h2 class="text-black display-5">TODO LIST
        </h2></div>
    </div>
    <br>
    <ul>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/login.jsp'/>"> <c:out value="${user.login}"/> | Sign in</a>
        </li>
    </ul>
    <br><br><label>Add task description</label>
    <br><input type="text" class="form-control" id="desc">
    <br>
    <div class="col-sm-5">
        <button type="button" onclick="getCategory()">Show Category</button>
        <br><br><select class="form-control" name="categories" id="categories" multiple>
    </select>

    </div>
    <br>
    <button type="submit" class="btn btn-primary" onclick="add()">Add Task</button>
    <br><br><input type="checkbox" id="checkbox" onclick="showChecked()">Show all/not completed
    <h2>Task list:</h2>
    <table class="table" id="table" border="3">
        <thead>
        <tr>
            <th>Description</th>
            <th>Create Date</th>
            <th>Done</th>
            <th>Author</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
