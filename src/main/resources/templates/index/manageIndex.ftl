<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../favicon.ico"> -->

    <title>Signin Template for Bootstrap</title>

    <link rel="stylesheet/less" type="text/css" href="/less/sign.less">

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <script type="text/javascript" src="/js/less.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/json.js"></script>
    <script src="/js/ajax.js"></script>
    <script src="/js/constant.js"></script>
    <script src="/layer/layer.js"></script>


    <!-- Custom styles for this template -->
<#--<link href="../css/signin.css" rel="stylesheet">-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<script>
    $(function () {
        $("#login_submit").click(function () {
            login();
        })
    })
    function login() {
        var arr = {};
        arr['name'] = $("#id_login_name").val();
        arr['password'] = $("#id_login_password").val();
        var json = JSON.stringify(arr);
        var url = method_manage_login;
        myAjax('post',json, url, loginAfter);
    }

    function loginAfter(data) {
        data = decodeURIComponent(data);
        var arr = jQuery.parseJSON(data);
        console.info(arr);
        if(arr['code']==code_success){
            location.href='manage/manage';
        }else if(arr['code']==code_error){
            layer.alert("登录出错");
        }else if(arr['code']==code_manageLoginError){
            layer.alert("登录名或账号错误");
        }
    }
</script>

<div class="container">
    <form class="form-signin">
        <h2 class="form-signin-heading">笔试管理</h2>
        <input type="text" id="id_login_name" name="name" class="form-control" placeholder="姓名" required autofocus>
        <input type="password" id="id_login_password" name="password" class="form-control" placeholder="密码" required
               autofocus>
        <div class="checkbox">
            <label>
                <a href="">注册</a>
            </label>
        </div>
        <Button class="btn btn-lg btn-primary btn-block" type="button" id="login_submit">登录</Button>
    </form>

</div>


</body>
</html>

