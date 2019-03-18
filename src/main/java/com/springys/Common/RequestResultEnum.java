package com.springys.Common;

import lombok.Getter;

@Getter
public enum RequestResultEnum {

    SUCCESS(200, "操作成功"),

    FAIL_PARAM_ERROR(0, "参数错误!"),
    operation_error(-1, "操作失败!"),
    DuplicateKeyException(-2, "该记录已存在!"),
    Invalid_token(-3, "token无效!"),
    JWT_expired(-3, "token已过期!"),
    login_password_error(-1000, "密码错误!"),
    edit_password_different_error(-1001, "两次新密码输入不一致!"),
    login_auth_error(-2008, "验证失败!"),

    SCRIPT_ALREADY_EXIST(-3001,"该分类已存在"),
    SCRIPT_NAME_EMPTY(-3002,"不能为空"),
    ADD_FAIL(-3003,"添加失败"),
    EDIT_FAIL(-3004,"编辑失败"),
    DELETE_FAIL(-3004,"删除失败"),
    UPLOAD_FAIL(-3010, "文件上传失败"),
    UPLOAD_FAIL_FILE_EMPTY(-3005, "文件上传失败，文件不能为空"),
    UPLOAD_FAIL_FILE_SUFFIX_INCORRECT(-3006, "文件上传失败，文件格式不正确"),
    UPLOAD_FAIL_FILE_ALREADY_EXIST(-3007, "文件上传失败，该文件名称已存在"),
    UPLOAD_FAIL_FILE_NAME_DUPLICATED(-3008, "文件上传失败，上文的文件名称有重复"),
    UPLOAD_FAIL_SCRIPT_ALREADY_EXIST(-3009, "文件上传失败，脚本名称已存在:"),

    REPLACE_FAIL_FILE_NAME_DIFFERENT(-3010, "文件替换失败，替换文件与原文件名称不相同"),
    REPLACE_FAIL(-3011, "文件替换失败"),
    SCRIPT_CLASSIFY_ALREADY_EXIST(-3012,"脚本分类已存在"),
    SCRIPT_CLASSIFY_NAME_EMPTY(-3013,"脚本分类名称不能为空"),
    SCRIPT_CLASSIFY_NOT_EXIST(-3014,"脚本分类不存在"),
    FILE_NAME_CONTAINS_ILLEGAL_CHAR(-3015,"文件名称包含非法字符：\\\\/:*?\"<>|"),
    SCRIPT_FILE_NOT_EXIST(-3016,"脚本文件不存在"),
    SCRIPT_OPERATION_LOG_FILE_NOT_EXIST(-3017,"脚本操作日志不存在"),
    PACKAGE_TYPE_ERROR(-3018,"不支持的压缩包类型"),
    RAR_COMPRESS_NONSUPPORT(-3019,"由于专利问题，暂不支持rar格式压缩文件下载"),
    DOWNLOAD_FAIL_FILE_EMPTY(-3020, "文件导出失败"),

    DOWNLOAD_FAIL_FILE_EMPTY1(-3021, "文件导出失败，导出文件为空"),
    set_online_status_same(-3101,"当前已经处于%s状态"),
    set_online_status_addjob_failed(-3102,"任务上线失败，请检查参数"),
    set_online_status_error_runPlanTpye(-3103,"非法runPlanTpye类型"),
    set_online_status_error_everyTimeType(-3104,"非法everyTimeType类型"),
    set_online_status_error_everyTime(-3105,"非法everyTimeType类型"),
    set_online_status_error_addTask(-3106,"添加任务失败"),
    login_ban(-3107,"你的账号已经被限制"),
    regist_error(-3108,"信息填写不完整"),
    regist_nameDouble(-3109,"名称重复"),
    format_error(-3110,"格式错误"),
    fail_username(-3113,"非法昵称"),
    password_error(-3111,"密码必须6到12位英文和数字"),
    email_doouble(-3112,"邮箱已经有人使用"),
    message_EMPTY(-3114,"内容不能为空"),
    title_EMPTY(-3115,"标题不能为空")


    ;


    private int code;
    private String msg;

    RequestResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
