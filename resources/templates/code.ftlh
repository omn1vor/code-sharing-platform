<!doctype html>
<html>
<head>
    <title>Code</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.1/styles/default.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.1/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <h1>Code Sharing Platform</h1>
    <div>
        <pre id="code_snippet"><code>${codeSnippet}</code></pre>
        <p class="note">
            Uploaded: <span id="load_date">${date}</span>.
            <#if (timeRestraint)>
                This snippet will be available for <span id="time_restriction">${time}</span> more seconds.
            </#if>
            <#if (viewsRestraint)>
                Views left: <span id="views_restriction">${views}</span>.
            </#if>
        </p>
    </div>
</body>
</html>