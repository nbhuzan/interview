/**
 * Created by huzan on 2016/11/22.
 */
function showSubject() {
    showLoading(true);
    var json = JSON.stringify(1);
    var url = method_subject_rest;
    myAjax('get',json, url, showSubjectAfter);
}


function showSubjectAfter(data) {
    data = decodeURIComponent(data);
    var arr = jQuery.parseJSON(data);
    if(arr['code']==code_success){
       console.info(arr);

        showData(arr);


        $("#table").empty();
        //列名
        var htmlTable='<tr>';
        for(var i=0;i<arr['tableRank'].length;i++){
            htmlTable+='<th>'+arr['tableRank'][i]+'</th>';
        }
        htmlTable+='</tr>';


        //数据
        for (var i = 0;i<arr['list'].length;i++){
            htmlTable+='<tr>';
            htmlTable+='<td>'+arr['list'][i].description+'</td>';
            htmlTable+='<td>'+arr['list'][i].kindName+'</td>';
            htmlTable+='<td>'+arr['list'][i].typeName+'</td>';
            htmlTable+='<td>'+arr['list'][i].answer+'</td>';
            htmlTable+='<td>'+arr['list'][i].answerNum+'</td>';
            htmlTable+='<td>';
            htmlTable+='<a style="color: dodgerblue" onclick="subjectMsg('+arr["list"][i].id+')">详情</a>';
            htmlTable+='<a style="color: red;margin-left: 10px">禁用</a>';
            htmlTable+= '</td>';
            htmlTable+='</tr>';
        }
        $("#table").append(htmlTable);

    }else{
        layer.alert("获取失败");
    }
    showLoading(false);

}
var indexSubjectMsg;
function subjectMsg(index) {
    indexSubjectMsg = layer.open({
        type: 2,
        // skin: 'layui-layer-demo', //样式类名
        closeBtn: 1, //不显示关闭按钮
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['800px', '50%'],
        content: method_updateSubject+"/?id="+index
    });
}

function showPaperModel() {
    showLoading(true);
    var json = JSON.stringify(1);
    var url = method_pageModel_rest;
    myAjax('get',json, url, showPaperModelAfter);
}


function showPaperModelAfter(data) {
    data = decodeURIComponent(data);
    var arr = jQuery.parseJSON(data);
    if(arr['code']==code_success){
        console.info(arr);
        showData(arr);



        $("#table").empty();
        //列名
        var htmlTable='<tr>';
        for(var i=0;i<arr['tableRank'].length;i++){
            htmlTable+='<th>'+arr['tableRank'][i]+'</th>';
        }
        htmlTable+='</tr>';


        //数据
        for (var i = 0;i<arr['list'].length;i++){
            htmlTable+='<tr>';
            htmlTable+='<td>'+arr['list'][i].jobName+'</td>';
            htmlTable+='<td>'+arr['list'][i].subject+'</td>';
            htmlTable+='<td><a style="color: dodgerblue">详情</a></td>';
            htmlTable+='</tr>';
        }
        $("#table").append(htmlTable);



    }else{
        layer.alert("获取失败");
    }
    showLoading(false);

}


function showData(arr) {

    //标题
    $("#h5").empty();
    $("#h5").append(arr['title']);

    //页码
    $('.pagination').empty();
    for (var i=1;i<=arr['page'];i++){
        $(".pagination").append('<li><a href="#">'+i+'</a></li>');
    }

    //总数
    $("#count").empty();
    $("#count").append('总共'+arr['size']+'条数据');
}



