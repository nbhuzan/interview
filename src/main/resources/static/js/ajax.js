/**
 * Created by huzan on 2016/11/21.
 */
function myAjax(method,value,url,callback){
    //$.ajax({url:url,data:});
    var aj = $.ajax({
        url:url,
        data:{
            ajaxdata:value
        },
        type:method,
        cache:false,

        success:function(data) {
            callback(data);
        },
        error : function() {
            alert("异常！");
        }
    });
}