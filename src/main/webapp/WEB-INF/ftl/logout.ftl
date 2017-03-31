<!DOCTYPE html>
<html>
<head>
    <title>登录页面</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="static/js/bootstrap-3.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/js/bootstrap-3.3.0/dist/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="static/js/jQuery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="static/js/bootstrap-3.3.0/dist/js/bootstrap.min.js"></script>


</head>
<body>
${name}<br/>
${msg}<br/>
<#--
<img id="herd-img" src="http://120.76.161.248/image-server//appImages/20161230//20161230154252160589882817017.jpeg">
-->
数据：<input type="text" id="btn2"><div id="btn1Error"></div><br/>
<input type="button" id="btn1" value="点击按钮"><br/>
<p>
    <button type="button" class="btn btn-lg btn-default">Default</button>
    <button type="button" class="btn btn-lg btn-primary">Primary</button>
    <button type="button" class="btn btn-lg btn-success">Success</button>
    <button type="button" class="btn btn-lg btn-info">Info</button>
    <button type="button" class="btn btn-lg btn-warning">Warning</button>
    <button type="button" class="btn btn-lg btn-danger">Danger</button>
    <button type="button" class="btn btn-lg btn-link">Link</button>
</p>

<br/><br/><br/><br/>

<h2 class="sub-header">Section title</h2>
<div class="table-responsive">
<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>Header</th>
        <th>Header</th>
        <th>Header</th>
        <th>Header</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1,001</td>
        <td>Lorem</td>
        <td>ipsum</td>
        <td>dolor</td>
        <td>sit</td>
    </tr>
    <tr>
        <td>1,002</td>
        <td>amet</td>
        <td>consectetur</td>
        <td>adipiscing</td>
        <td>elit</td>
    </tr>
    <tr>
        <td>1,003</td>
        <td>Integer</td>
        <td>nec</td>
        <td>odio</td>
        <td>Praesent</td>
    </tr>
    <tr>
        <td>1,003</td>
        <td>libero</td>
        <td>Sed</td>
        <td>cursus</td>
        <td>ante</td>
    </tr>
    <tr>
        <td>1,004</td>
        <td>dapibus</td>
        <td>diam</td>
        <td>Sed</td>
        <td>nisi</td>
    </tr>
    <tr>
        <td>1,005</td>
        <td>Nulla</td>
        <td>quis</td>
        <td>sem</td>
        <td>at</td>
    </tr>
    <tr>
        <td>1,006</td>
        <td>nibh</td>
        <td>elementum</td>
        <td>imperdiet</td>
        <td>Duis</td>
    </tr>
    <tr>
        <td>1,007</td>
        <td>sagittis</td>
        <td>ipsum</td>
        <td>Praesent</td>
        <td>mauris</td>
    </tr>
    </tbody>
</table>
</div>

<script type="text/javascript">
    $("#btn1").click(function () {
        var btn2 = $("#btn2").val();
        if(btn2 == null || btn2 == ''){
            console.error("没有填入数据！");
            $("#btn1Error").text("没有填入数据！");
        }else if (btn2.trim() == ''){
            console.error("填入的是空格！");
            $("#btn1Error").text("填入的是空格！");
        }
    });

</script>
</body>
</html>
