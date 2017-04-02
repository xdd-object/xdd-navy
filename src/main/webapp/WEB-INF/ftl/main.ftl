<!DOCTYPE html>
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
</head>
<body>
    Welcome<br/><input id="text" type="text"/>
    <button onclick="conn()">连接</button>
    <button onclick="send()">发送消息</button>
    <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <hr/>
    <div id="message"></div>
</body>
<script type="text/javascript">
    var websocket = null;

    function conn() {
        websocket = new WebSocket("ws://localhost:5080/websocket1");

        websocket.onopen = function () {
            setMessageInnerHTML("连接成功！");
            console.log("执行onopen方法！");
        };
        websocket.onmessage = function (event) {
            setMessageInnerHTML("执行onmessage方法");
            console.log(event.data);
            console.log("执行onmessage方法！");
        };
        websocket.onclose = function (event) {
            setMessageInnerHTML("执行onclose方法！");
            console.log("执行onclose方法！");
        };
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</html>