<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>笔试系统</title>

    <link rel="stylesheet/less" type="text/css" href="/less/sign.less">

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <script type="text/javascript" src="/js/less.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/json.js"></script>
    <script src="/js/ajax.js"></script>
    <script src="/js/constant.js"></script>
    <script src="/layer/layer.js"></script>

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
        arr['phone'] = $("#id_login_phone").val();
        arr['jobId'] = $("#id_job").val();
        var json = JSON.stringify(arr);
        var url = method_login;
        myAjax('post',json, url, loginAfter);
    }

    function loginAfter(data) {
        data = decodeURIComponent(data);
        var arr = jQuery.parseJSON(data);
        console.info(arr);
        if(arr['code']==code_success){
            var form = decodeURIComponent(arr['msg']);
            form = jQuery.parseJSON(form);
            console.info(form);
            location.href=method_examination+"/?id="+form.id+"&jobId="+form.jobId;
        }else if(arr['code']==code_error){
            layer.alert(arr['msg']);
        }else{
            layer.alert("错误");
        }
    }
</script>

<div class="container">
    <div class="form-signin">
        <h2 class="form-signin-heading">填写基本信息</h2>
        <input type="text" class="form-control" id="id_login_name" placeholder="姓名" required autofocus>
        <input type="text" class="form-control" id="id_login_phone" placeholder="手机号" required>
        <select class="form-control" id="id_job" >
            <option>选择应聘岗位</option>
            <#list list as list>
                <option value="${list.id}">${list.jobName}</option>
            </#list>

        </select>
        <div class="checkbox">

        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="login_submit">开始笔试</button>
    </>

</div> <!-- /container -->


</body>
</html>

