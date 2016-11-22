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

    <!-- Custom styles for this template -->
    <#--<link href="../css/signin.css" rel="stylesheet">-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <form class="form-signin" role="form">
        <h2 class="form-signin-heading">填写基本信息</h2>
        <input type="text" class="form-control" placeholder="姓名" required autofocus>
        <input type="text" class="form-control" placeholder="手机号" required>
        <select class="form-control" >
            <option>选择应聘岗位</option>
            <#list list as list>
                <option>${list.jobName}</option>
            </#list>

        </select>
        <div class="checkbox">
            <!-- <label>
              <input type="checkbox" value="remember-me"> Remember me
            </label> -->
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">开始笔试</button>
    </form>

</div> <!-- /container -->


</body>
</html>

