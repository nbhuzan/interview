<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="starter-template.css" rel="stylesheet">
    <script src="/js/subject.js"></script>
    <script src="/js/pageModel.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/json2.js"></script>
    <script src="/js/ajax.js"></script>
    <script src="/js/constant.js"></script>
    <script src="/js/manage.js"></script>
    <script src="/layer/layer.js"></script>


</head>

<body>
<script>
    var menu;
    $(function () {
        $("#id_add_subject").click(function () {
            if (menu == top_menu_subject) {
                addSubjectWindow();
            } else if (menu == top_menu_model) {
                addPageModelWindow();
            } else if (menu == top_menu_record) {

            }

        });
    });

    function model(index) {
        menu=index;
        if (index == top_menu_subject) {
            showSubject();
        } else if (index == top_menu_model) {
            showPageModel();
        } else if (index == top_menu_record) {

        }
    }
</script>


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">笔试系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li onclick="model(top_menu_subject)"><a href="#">题库</a></li>
                <li onclick="model(top_menu_model)"><a href="#">试题模版</a></li>
                <li onclick="model(top_menu_record)"><a href="#about">考试记录</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div id="id_div_subject">
        <div class="panel panel-default" style="margin-top: 100px">
            <!-- Default panel contents -->
            <div class="panel-body">
                <h5 style="float: left" id="h5"></h5>
                <#--<div class="input-group" style="margin-left: 60px;width: 200px">-->
                    <#--<input type="text" class="form-control">-->
                    <#--<span class="input-group-addon">查询</span>-->
                <#--</div>-->

                <div style="clear:both"></div>
                <div class="btn-group" style="float: right">
                    <button type="button" id="id_add_subject" class="btn btn-default">新增</button>
                    <button type="button" id="id_update_subject" class="btn btn-default">修改</button>
                    <button type="button" id="id_del_subject" class="btn btn-default">禁用</button>
                </div>

            </div>
            <!-- Table -->
            <table class="table">
                <tbody id="table">
                <#--<#list tableRank as tr>-->
                    <#--<th>${tr}</th>-->
                <#--</#list>-->
                </tbody>

            <#--<#list list as l>-->
                    <#--<tr>-->
                    <#--<td>${l.description}</td>-->
                    <#--<td>${l.kindName}</td>-->
                    <#--<td>${l.typeName}</td>-->
                    <#--<td>${l.answer}</td>-->
                    <#--<td>${l.answerNum}</td>-->
                    <#--<td><span style="color: red">详情</span></td>-->
                <#--</tr>-->
            <#--</#list>-->
            </table>

        </div>
        <nav>
            <ul class="pagination" style="float: left">
            <#--<#list 1..max as i>-->
                <#--<li><a href="#">${i}</a></li>-->
            <#--</#list>-->
            </ul>
        </nav>
        <p class="pagination" id="count" style="margin-left: 20px"></p>
    </div>


</div>


</body>
</html>
