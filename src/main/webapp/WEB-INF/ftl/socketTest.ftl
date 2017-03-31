<!DOCTYPE html>
<html>
<head>
    <title>登录页面</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="static/js/bootstrap-3.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/js/bootstrap-3.3.0/dist/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="static/js/jQuery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="static/js/bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/sockjs/sockjs.min.js"></script>

<#--<link rel="stylesheet" href="../../static/js/bootstrap-3.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/js/bootstrap-3.3.0/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../../static/js/bootstrapValidator/dist/css/bootstrapValidator.min.css">
<script type="text/javascript" src="../../static/js/jQuery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../../static/js/bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../static/js/bootstrapValidator/dist/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../static/js/bootstrapValidator/dist/js/language/zh_CN.js"></script>
<script type="text/javascript" src="../../static/js/echarts-2.2.7/build/dist/echarts.js"></script>-->
</head>
<style type="text/css">

</style>
<script type="text/javascript">
    var ws;
    window.onload = connect;
    function connect() {
        if ('WebSocket' in window) {
            ws = new WebSocket("ws://192.168.1.49:2080/websocket1");
            console.log("尝试连接!")
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket("ws://192.168.1.49:2080/webSocketServer");
        } else {
            //如果是低版本的浏览器，则用SockJS这个对象，对应了后台“sockjs/webSocketServer”这个注册器，
            //它就是用来兼容低版本浏览器的
            ws = new SockJS("http://192.168.1.49:2080/sockjs/webSocketServer");
        }
        ws.onopen = function (evnt) {
            console.log("连接成功!")
        };
        ws.onmessage = function (evnt) {
            //eval("var dataObj=" + evnt.data);
            /*if (dataObj != undefined) {
                $(dataObj.data).each(function (i, o) {
                    $("#msgName").text(o.msgName);
                    $("#amount").text(o.amount);
                    $("#msg a:first").attr("_href", core.getRootPath() + o.toUrl).text(o.taskName);
                    $("#msgId").val(o.id);
                    setTimeout("tips_pop()", 1000);
                });
            }*/
            console.log(evnt.data)
            console.log("接受消息!")

        };
        ws.onerror = function (evnt) {
            console.log("连接错误!")
        };
        ws.onclose = function (evnt) {
            console.log("连接关闭!")
        }
        console.log(ws);
    }
    function send() {
        var value = $("#msg").val();
        console.log(value)
        if (ws == null){
            console.log("ws 是null")
            ws = new WebSocket("ws://192.168.1.49:2080/websocket");
        }
        console.log(ws);
        ws.send("nihao");
        console.log("发送成功！")
    }
</script>
<body>

<div id="msgName">1</div><br>
<div id="amount">2</div><br>
<div id="msgId">4</div><br>
<input type="text" id="msg"/><br>
<input type="button" onclick="send()" value="发送"><br>
    <div id="abc">
        11<input type="checkbox">
        12<input type="checkbox">
        13<input type="checkbox">
        14<input type="checkbox">
    </div>
</body>
<script type="text/javascript">
    $("#abc input").attr("disabled",true)
</script>
</html>
