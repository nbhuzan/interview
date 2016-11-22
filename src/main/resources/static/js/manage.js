/**
 * Created by huzan on 2016/11/22.
 */
function showSubject() {
    var json = JSON.stringify("");
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
            htmlTable+='<td><span style="color: red">详情</span></td>';
            htmlTable+='</tr>';
        }
        $("#table").append(htmlTable);



    }else{
        layer.alert("获取失败");
    }

}

function showPageModel() {
    var json = JSON.stringify("");
    var url = method_pageModel_rest;
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
            htmlTable+='<td><span style="color: red">详情</span></td>';
            htmlTable+='</tr>';
        }
        $("#table").append(htmlTable);



    }else{
        layer.alert("获取失败");
    }

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



