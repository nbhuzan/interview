/**
 * Created by huzan on 2016/11/22.
 */
index1=1;
function addPageModelWindow() {
     index1 = layer.open({
        type: 2,
        // skin: 'layui-layer-demo', //样式类名
        closeBtn: 1, //不显示关闭按钮
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['800px', '50%'],
        content: method_addPageModel
    });
}

function addPageModel() {
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
    var url = method_pageModel_rest;
    myAjax('post',json, url, addSubjectAfter);
}


function addSubjectAfter(data) {
    data = decodeURIComponent(data);
    var arr = jQuery.parseJSON(data);
    if(arr['code']==code_success){
        parent.layer.close(parent.index1);
    }else{
        layer.alert("新增失败");
    }

}
