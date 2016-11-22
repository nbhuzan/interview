<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">


    <title>Starter Template for Bootstrap</title>
    <link href="/less/addSubject.less" rel="stylesheet/less">

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/json2.js"></script>
    <script src="/js/ajax.js"></script>
    <script src="/js/less.js"></script>
    <script src="/js/subject.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/constant.js"></script>
    <script src="/layer/layer.js"></script>


</head>

<body>
<script>
    $(function () {

        $("#id_add_success").click(function () {
            addSubject();
        })
    })
</script>

<div class="container" >
    <div>
        <div class="input-group" style="float: left;width: 300px">
            <span class="input-group-addon">种类</span>
            <select class="form-control input-sm" id="id_type">
            <#list  typeList as type>
                <option value="${type.id}">${type.typeName}</option>
            </#list>
            </select>
        </div>
        <div class="input-group" style="margin-left:10px;float: left;width: 300px">
            <span class="input-group-addon">题型</span>
            <select class="form-control input-sm" id="id_kind">
                <#list  kindList as kind>
                <option value="${kind.id}">${kind.kindName}</option>
                </#list>
            </select>
        </div>
        <div class="input-group" style="float:left;margin-top:10px;width: 300px">
            <span class="input-group-addon">标准答案</span>
            <input class="form-control" type="email" placeholder="" id="id_answer">
        </div>
        <div class="input-group" style="margin-left:10px;margin-top:10px;float: left;width: 300px">
            <span class="input-group-addon">答案数量</span>
            <input class="form-control" type="number" placeholder="" id="id_answerNum">
        </div>



        <div style="clear:both"></div>
    </div>
    <textarea class="form-control" rows="3" id="id_desc" placeholder="请输入问题描述,选项或填空部分换行符隔开。。。"></textarea>

    <button type="button" class="btn btn-success" id="id_add_success">Success</button>
</div>


</body>
</html>
