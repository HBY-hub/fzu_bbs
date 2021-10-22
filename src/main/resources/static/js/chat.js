//聊天室主人
var username;
var userId;
// 消息接收者
var toUserId;

//登录后显示用户名和状态
$(function () {
    $.ajax({
        //是否异步,此项目此处必须是false
        async: false,
        //请求方式
        type: 'GET',
        //请求url
        url: "/getUser",
        success : function (res) {
            var jsonData = JSON.parse(res);
            alert(jsonData);
            username = jsonData.username;
            userId = jsonData.id;
            $('#chatMeu').html('<p>用户：' + userId + "<span style='float: right;color: greenyellow; height: 20px'>在线</span></p>")
        }
    });
    //创建websocket对象
    var ws = new WebSocket("ws://localhost:8080/chat/"+userId.toString());

    //建立连接后触发
    ws.onopen = function () {
        $('#chatMeu').html('<p>用户：' + userId + "<span style='float: right;color: greenyellow; height: 20px'>在线</span></p>")
    };

    //接收到服务端的推送后触发
    ws.onmessage = function (evt)
    {
        //获取数据
        var dataStr = evt.data;
        //jsonData 格式举例（需要判断是否是系统消息）：{“systemMsgFlag”: false, "fromName": "YYJ", "message": “你在哪里呀？”}
        var jsonData = JSON.parse(dataStr);

        //不是系统消息，是发送给指定用户的消息,示例值：{“systemMsgFlag”: false, "fromName": "YYJ", "message": “你在哪里呀？”}
        var data = jsonData.text;
        var cnt = "<div class=\"atalk\"><span id=\"asay\">"+data+"</span></div>"
        if (toUserId === jsonData.fromUserId)
        {
            $("#chatCnt").append(cnt);
        }
        var chatData = sessionStorage.getItem(jsonData.fromUserId);
        if (chatData != null)
        {
            cnt = chatData + cnt;
        }
        sessionStorage.setItem(jsonData.fromUserId ,cnt);
    };
    //关闭连接触发
    ws.onclose = function ()
    {
        $('#chatMeu').html('<p>用户' + userId + "<span style='float: right;color: #d50a0a; height: 20px'>离线</span></p>")
    };


    //发送按钮点击
    $("#submit").click(function () {
        //获取发送输入框中的内容
        var data = $("#tex_content").val();
        //点击发送后，清空输入内容框架
        $("#tex_content").val("");
        var sendJson = {"toUserId": toUserId, "text": data,"fromUserId":userId};
        //聊天框显示发送内容
        var cnt = "<div  class=\"btalk\"><span id=\"bsay\">" + data+ "</span></div>";
        $("#chatCnt").append(cnt);
        var chatData = sessionStorage.getItem(toUserId);
        if (chatData != null) {
            cnt = chatData + cnt;
        }
        sessionStorage.setItem(toUserId,cnt);

        //发送数据给服务端
        ws.send(JSON.stringify(sendJson));
    });

});

function setToUser(){
    toUserId = $("#toUserId").val();

    $('#p1').remove();
    $('#chatMeu').append('<p id="p1" style="text-align: center">正在和<b style="color: #db41ca ">' + name + '</b>聊天</p>');
    $('#chatMain').css("display", "inline");
    //清空聊天区
    $("#chatCnt").html("");
    //当点击聊天人列表时，需要获与之对应的聊天记录，聊天记录存放在sessionStorage中
    var chatData = sessionStorage.getItem(toName);
    if (chatData != null) {
        //渲染聊天数据到聊天区
        $("#chatCnt").html(chatData);
    }
}