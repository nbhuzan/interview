/**
 * Created by huzan on 2016/11/21.
 */
var code_success = 200;
var code_error = 1;
var code_manageLoginError = 101;

var vision = '/v1';
var method_addSubject = vision+'/manage/addSubject';
var method_updateSubject = vision+'/manage/updateSubject';
var method_addPaperModel = vision+'/manage/addPaperModel';
var method_updatePaperModel = vision+'/manage/updatePaperModel';
var method_subject_rest = vision+'/manage/subject';
var method_paperModel_rest = vision+'/manage/paperModel';
var method_record_rest = vision+'/manage/record';
var method_manage_login = vision+'/manage/index';
var method_examination = vision+'/examination';

var method_login = vision+'/index';

var subject_del_y=1;
var subject_del_n=0;

/**
 * 顶部菜单index
 * @type {number}
 */
var top_menu_subject=1;
var top_menu_model=2;
var top_menu_record=3;


/**
 * loading
 */
var myLayerLoad;
function showLoading(is) {
    if(is) {
         myLayerLoad = layer.load(0, {shade: false});
    }else{
        layer.close(myLayerLoad);
    }
}

