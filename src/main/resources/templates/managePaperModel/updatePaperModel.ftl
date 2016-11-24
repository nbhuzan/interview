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
        $("#id_update_success").click(function () {
            updatePaperModel();
        });
        parent.$(".layui-layer-content iframe").css("height", "500px");
    });

</script>

<div class="container">
    <div class="form-group">
        <label class="col-sm-2 control-label" style="margin-top: 8px">岗位</label>
        <div class="col-sm-10">
            <p class="form-control-static" id="id_job" jobid="${job.id}" >${job.jobName}</p>
        </div>
    </div>

    <h4 style="margin-left: 10px">题型数量</h4>

    <#list typeList as type>
        <div class="input-group" style="margin-top: 10px;width:300px;">
            <span class="input-group-addon">${type.typeName}</span>
            <input type="number" class="form-control" typeid="${type.id}"  value="${type.num}">
        </div>

    </#list>
    <div style="clear:both"></div>

    <button type="button" class="btn btn-success" id="id_update_success" style="margin-top: 10px">更新</button>
</div>


</body>
</html>
