function sendMessage(username, text) {
    let body = {
        username: username,
        text: text
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
        }
    });
}
function receiveMessage(username) {
    $.ajax({
        url: "/messages?username=" + username,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>')
            receiveMessage(username);
        }
    })
}

function login(username) {
    let body = {
        username: username,
        text: 'Hi!'
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(username);
        }
    });
}
//
// $(document).ready(function () {
//    sendMessage(pageId, 'Hi');
//    receiveMessage(pageId);
// });