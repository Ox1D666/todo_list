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
</head>
<body>
<script>
    function showAll() {
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
                    table.append('</tr>')
                    table.append('</tbody>')
                });
            }
        })
    }

    function showTrue() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/todo_list/show.do',
            data: 'done=' + 'true',
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
                    table.append('</tr>')
                    table.append('</tbody>')
                });
            }
        })
    }

    function showFalse() {
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
                    table.append('</tr>')
                    table.append('</tbody>')
                });
            }
        })
    }

    function add() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/todo_list/show.do',
            data: 'desc=' + $('#desc').val(),
            dataType: 'text',
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
                    table.append('</tr>')
                    table.append('</tbody>')
                });
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
                        table.append('</tr>')
                        table.append('</tbody>')
                    });
                }
            })
        };
    }

</script>

<label>Show all tasks</label>
<input type="button" value="Display" onclick="showAll()">
<br/>
<label>Show done tasks</label>
<input type="button" value="Display" onclick="showTrue()">
<br/>
<label>Show uncompleted tasks</label>
<input type="button" value="Display" onclick="showFalse()">
<br/>
<label>Add task description</label>
<br/>
<label>
    <input type="text" class="form-control" id="desc" value="">
</label>
<br/>
<button type="submit" class="btn btn-primary" onclick="add()">Add Task</button>
<br/>
<label>
    <input type="checkbox" id="checkbox" onclick="showChecked()">Show
</label>
<h2>Task list:</h2>
<table class="table" id="table" border="3">
    <thead>
    <tr>
        <th>Description</th>
        <th>Create Date</th>
        <th>Done</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>
                <c:out value="${item.description}"/>
            </td>
            <td>
                <c:out value="${item.create}"/>
            </td>
            <td>
                <a href='<c:url value="/index.do?id=${item.id}&done=all"/>'>
                    <c:out value="${item.done}"/>
                </a>
                <a href='<c:url value="/index.do?id=${item.id}&action=remove&done=all"/>'>
                    <i class="fa fa-remove mr-3"></i>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
