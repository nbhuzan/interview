/**
 * Created by huzan on 2016/11/22.
 */
index1=1;
function addPaperModelWindow() {
     index1 = layer.open({
        type: 2,
        // skin: 'layui-layer-demo', //样式类名
        closeBtn: 1, //不显示关闭按钮
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['800px', '50%'],
        content: method_addPaperModel
    });
}

function addPaperModel() {
    var arr = {};
    arr['jobId'] = $("#id_job").val();
    var type = [];
    $('input[class="form-control"]').each(function(){
        $this=$(this);
        console.info($this.attr('typeid'));
        type.push({
            id:$this.attr('typeid'),
            num:$this.val()
        })

    });
    arr['typeNumList'] = type;
    var json = JSON.stringify(arr);
    var url = method_paperModel_rest;
    myAjax('post',json, url, addPaperModelAfter);
}




function addPaperModelAfter(data) {
    data = decodeURIComponent(data);
    var arr = jQuery.parseJSON(data);
    console.info(arr);
    if(arr['code']==code_success){

        window.parent.layer.msg("新增成功");
        window.parent.showPaperModel();
        parent.layer.close(parent.index1);
    }else{
        layer.alert("新增失败");
    }

}


function updatePaperModel() {
    var arr = {};
    arr['jobId'] = $("#id_job").attr("jobid");
    var type = [];
    $('input[class="form-control"]').each(function(){
        $this=$(this);
        console.info($this);
        type.push({
            id:$this.attr('typeid'),
            num:$this.val()
        })

    });
    arr['typeNumList'] = type;
    console.info(arr);
    var json = JSON.stringify(arr);
    var url = method_paperModel_rest;
    myAjax('put',json, url, updatePaperModelAfter);
}




function updatePaperModelAfter(data) {
    data = decodeURIComponent(data);
    var arr = jQuery.parseJSON(data);
    console.info(arr);
    if(arr['code']==code_success){

        window.parent.layer.msg("更新成功");
        window.parent.showPaperModel();
        parent.layer.close(parent.indexPaperModelMsg);
    }else{
        layer.alert("更新失败");
    }

}


