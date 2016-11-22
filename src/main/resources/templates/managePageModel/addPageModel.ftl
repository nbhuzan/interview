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
    <script src="/js/pageModel.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/constant.js"></script>
    <script src="/layer/layer.js"></script>


</head>

<body>
<script>
    $(function () {
        $("#id_add_success").click(function () {
            addPageModel();
        })
    });

</script>

<div class="container">
    <div class="input-group" style="width: 300px">
        <span class="input-group-addon">岗位</span>
        <select class="form-control input-sm" id="id_job">
        <#list  jobList as job>
            <option value="${job.id}">${job.jobName}</option>
        </#list>
        </select>
    </div>

    <h4 style="margin-left: 10px">题型数量</h4>

    <#list typeList as type>
        <div class="input-group" style="margin-top: 10px;width:300px;">
            <span class="input-group-addon">${type.typeName}</span>
            <input type="text" class="form-control" typeid="${type.id}"  placeholder="数量">
        </div>

    </#list>
    <div style="clear:both"></div>

    <button type="button" class="btn btn-success" id="id_add_success" style="margin-top: 10px">Success</button>
</div>


</body>
</html>
