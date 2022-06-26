function send() {

    let time = 0;
    try {
        time = parseInt(document.getElementById("time_restriction").value);
    } catch(ignored) {}

    let views = 0;
    try {
        views = parseInt(document.getElementById("views_restriction").value);
    } catch(ignored) {}

    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": time,
        "views": views
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
        let result = JSON.parse(xhr.responseText);
        let msg = `Your snippet was successfully sent, ID = ${result.id}`;
        document.getElementById("result").innerHTML = msg;
    }
}