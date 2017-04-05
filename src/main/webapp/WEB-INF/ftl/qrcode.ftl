<!DOCTYPE html>
<html>
<head>
    <title>二维码</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>

    <link type="text/css" href="../../static/js/bootstrap-3.3.0/dist/css/bootstrap-theme.css" rel="stylesheet"/>
    <link type="text/css" href="../../static/js/bootstrap-3.3.0/dist/css/bootstrap.css" rel="stylesheet"/>

    <script type="text/javascript" src="../../static/js/jQuery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../static/js/common/common.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap-3.3.0/dist/js/bootstrap.js"></script>


</head>
<body>
    <div>
        <input type="button" value="生成二维码"  id="qrcode"/><br>
        key:<input type="text" id="key"><br>
        value:<input type="text" id="value"><br>
        content:<input type="text" id="content"><br>
        <img  id="qrcodeImg" src="" width="200" height="200">
    </div>


    <script type="text/javascript">
        $(function(){
            $("#qrcode").click(function(){
                var key = $("#key").val();
                var value = $("#value").val();
                var content = $("#content").val();

                var src = baseUrl + "/qrcode/qrcode?content=" + content + "?{key}" + key +"{value}" + value;

                $("#qrcodeImg").attr("src", src);

                console.log(src);
                console.log(uuid());
                var interval = window.setInterval(showalert, 2000);

                function showalert() {
                    console.log(baseUrl + "/qrcode/qrcodeContent?key=" + key);
                    $.ajax({
                        type: "GET",
                        url: baseUrl + "/qrcode/qrcodeContent?key=" + key,
                        dataType: "json",
                        success: function(data){
                            console.log(data);
                            var value2 = data.value;
                            console.log(data.value);
                            if (value2 != null && value2 != '') {
                                alert(value2);
                                clearInterval(showalert);
                                window.location.href = value2;
                            }
                        },
                        error: function(){
                            console.log("出错了");
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>

