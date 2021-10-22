
function login() {
    var data = {};
    data['username'] = $('#uname').val();
    data['password'] = $('#pwd').val();
    $.ajaxSetup({contentType: "application/json; charset=utf-8"})
    $.post("login", JSON.stringify(data), function (res) {
        if (res.code == 200){
            location.href = 'toChatroom';
        }else {
            alert(JSON.stringify(res));
        }
    }, 'json');
}