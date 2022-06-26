<!doctype html>
<html>
<head>
    <title>Create</title>
    <script type="text/javascript" src="/js/addCode.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Code Sharing Platform</h1>
    <textarea id="code_snippet" placeholder="Type or insert code here"></textarea>
    <br>
    <h3>Restrictions (leave 0 for no restrictions)</h3>
    <div class="container">
        <div class="restriction">
            <label for="time_restriction">Time restriction (in seconds)</label>
            <input id="time_restriction" class="number-input" type="text"/>
        </div>
        <div class="restriction">
            <label for="views_restriction">Views restriction (in views)</label>
            <input id="views_restriction" class="number-input" type="text"/>
        </div>
    </div>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    <p id="result"><p>
</body>
</html>