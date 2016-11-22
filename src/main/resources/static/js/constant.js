/**
 * Created by huzan on 2016/11/21.
 */
var code_success = 0;
var code_error = 1;
var code_manageLoginError = 101;

var vision = '/v1';
var method_addSubject = vision+'/manage/addSubject';
var method_addPageModel = vision+'/manage/addPageModel';
var method_subject_rest = vision+'/manage/subject';
var method_pageModel_rest = vision+'/manage/pageModel';
var method_manage_login = vision+'/manage/index';

/**
 * 顶部菜单index
 * @type {number}
 */
var top_menu_subject=1;
var top_menu_model=2;
var top_menu_record=3;