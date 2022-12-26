<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>References</title>
    <style type="text/css">
        button {
            cursor: pointer;
        }

        .ml {
            margin-left: 0.2rem;
        }

        .container {
            padding: 0.5rem;
            border: solid 1px black;
            margin-bottom: 0.1rem;
        }

        .toolbar {
            padding: 0.5rem;
            margin-bottom: 0.1rem;
        }
    </style>
</head>
<body>
    Session Id: <input type="text" value="<%= session.getId() %>" id="sessionId" readonly="true">
    <h1>UWSR</h1>

    <div>
        <input type="text" id="secret-message">
        <button class="ml" onclick="setOwnerMode()">Set</button>
        <button class="ml" onclick="resetOwnerMode()">Reset</button>
    </div>

    <div id="toolbar" class="toolbar">
        <button onclick="onInsertBtnClick()" id="insertRefControl">insert</button>
        <button>filter</button>
    </div>

    <div class="container" id="insert-container" hidden>
        <input type="text" id="insertUrl" placeholder="https://url-useful-website-reference"><br>
        <input type="text" id="insertDescription" placeholder="key-word, key-word..."><br>
        <button onclick="submitInserting()">OK</button>
        <button onclick="closeInsertingReference()">Cancel</button>
    </div>

    <div id="references-container">

    </div>

<script type="text/javascript" src="references.js"></script>
</body>
</html>