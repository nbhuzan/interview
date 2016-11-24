<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>笔试管理</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/json2.js"></script>
    <script src="/js/ajax.js"></script>
    <script src="/js/constant.js"></script>

    <script src="/layer/layer.js"></script>



    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>

<body>
<script>
    var list = ${list};
//    list = decodeURIComponent(list);
//    var arr = jQuery.parseJSON(list);
    var subject = list.list;
    var index = 0;
    console.info(subject);

    $(function () {

        $("#id_kind").text(subject[index].kindName);
        $("#id_desc").val(subject[index].description);
        $("#id_submit").click(function () {
            $("#id_submit").attr("disabled", true);
            submitSubject();

        });
    })
    function submitSubject() {
        var arr = {};
        arr['id'] = subject[index].id;
        arr['subjectId'] = subject[index].subjectId;
        arr['respond'] = $("#id_respond").val();
        arr['paperId'] = subject[index].paperId;
        var json = JSON.stringify(arr);
        var url = method_examination;
        myAjax('put',json, url, submitSubjectAfter);
    }

    function submitSubjectAfter(data) {
        data = decodeURIComponent(data);
        var arr = jQuery.parseJSON(data);
        console.info(arr);
        $("#id_submit").attr("disabled", false);
        if(arr['code']==code_success){
            index+=1;
            if(index>=subject.length){
                layer.confirm('笔试已完成，请通知面试官', {
                    btn: ['好的'] //按钮
                }, function(){
                    location.href='/';
                });
            }else {
                arr['respond'] = $("#id_respond").val("");
                $("#id_kind").text(subject[index].kindName);
                $("#id_desc").val(subject[index].description);
            }
        }else{
            layer.alert("提交异常");
        }

    }
</script>


<div class="container">
    <div class="form-horizontal" >
        <div class="form-group">
            <label class="col-sm-2 control-label">类型</label>
            <div class="col-sm-10">
                <p class="form-control-static" id="id_kind"></p>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">描述</label>
            <div class="col-sm-10">
                <textarea class="form-control" rows="10" id="id_desc" placeholder="请输入问题描述,选项或填空部分换行符隔开。。。"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">答案</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id_respond" placeholder="请回答">
            </div>
        </div>
        <button type="submit" class="btn btn-default" id="id_submit" style="float: right">提交</button>
    </div>

</div>

<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
