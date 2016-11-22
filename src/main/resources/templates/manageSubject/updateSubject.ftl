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
        $("#id_type").val(${info.typeId});
        $("#id_kind").val(${info.kindId});
        $("#id_del").val(${info.del});
        $("#id_update_success").click(function () {
            updateSubject();
        })
        parent.$(".layui-layer-content iframe").css("height", "500px");
    })
</script>

<div class="container" >
    <div>
        <input type="hidden" id="id_update_id" value="${info.id}"/>
        <div class="input-group" style="width: 300px;margin:10px 0 10px 0">
            <span class="input-group-addon">是否禁用</span>
            <select class="form-control input-sm" id="id_del">
                <option value="0">否</option>
                <option value="1">是</option>
            </select>
        </div>
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
            <input class="form-control" type="text" placeholder="" value="${info.answer}" id="id_answer">
        </div>
        <div class="input-group" style="margin-left:10px;margin-top:10px;float: left;width: 300px">
            <span class="input-group-addon">答案数量</span>
            <input class="form-control" type="number" placeholder="" value="${info.answerNum}" id="id_answerNum">
        </div>



        <div style="clear:both"></div>
    </div>
    <textarea class="form-control" rows="3" id="id_desc"  placeholder="请输入问题描述,选项或填空部分换行符隔开。。。">${info.description}</textarea>

    <button type="button" class="btn btn-success" id="id_update_success">更新</button>
</div>


</body>
</html>
