<!doctype html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.1/styles/default.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.1/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <h1>Code Sharing Platform</h1>
    <#list snippets as snippet>
        <pre id="code_snippet"><code>${snippet.code}</code></pre>
        <p class="note">Uploaded: <span id="load_date">${snippet.getDateAsString()}</span></p>
    </#list>
</body>
</html>