/**
 * related html: pmc/factor.html
 */

$(function () {
    // #1 变量定义区~
    //==================================================================================================================

    // 因素选择框
    const factorSelect = $('#factorSelect');
    // 项目选择框
    const projectSelect = $('#projectSelect');
    // 版本选择框
    const versionSelect = $('#versionSelect');
    // 版本选择表单框
    const versionFormGroup = $('#versionFormGroup');
    // 专题选择框
    const subjectSelect = $('#subjectSelect');
    // 专题选择表单框
    const subjectFormGroup = $('#subjectFormGroup');

    // #2 函数定义区~
    //==================================================================================================================

    // 列出项目版本
    function listVersion() {
        $.ajax({
            url: "/versions/hosted?projectId=" + projectSelect.val(),
            success: function (data) {
                if (data.valid) {
                    $("#versionSelect").empty();
                    $.each(data.data, function (i, value) {
                        $("#versionSelect").append("<option value=\"" + value.versionId + "\">" + value.name + "</option>");
                    });
                    // 如果选择的是类型的话，则继续加载专题选择框
                    if ('FACTOR_SECTION' === factorSelect.val()) {
                        listSubject();
                    }
                    listFactor();
                } else {
                    toastr.warning(data.message);
                }
            }
        });
    }

    // 列出项目主题
    function listSubject() {
        $.ajax({
            url: "/subjects/hosted?versionId=" + versionSelect.val(),
            success: function (data) {
                if (data.valid) {
                    subjectSelect.empty();
                    subjectSelect.append("<option value=\"" + '' + "\">" + "请选择项目专题" + "</option>");
                    $.each(data.data, function (i, value) {
                        subjectSelect.append("<option value=\"" + value.subjectId + "\">" + value.name + "</option>");
                    });
                } else {
                    toastr.warning(data.message);
                }

            }
        });
    }

    // 列出因素列表，类型，专题或者版本
    function listFactor() {
        let factorType = factorSelect.val();
        // 类型
        if ('FACTOR_SECTION' === factorType) {
            $.ajax({
                url: "/section/manage?versionId=" + versionSelect.val() + "&subjectId=" + subjectSelect.val(),
                success: function (data) {
                    $("#mainFactorContainer").html(data);
                },
                error: function () {
                    toastr.error("Error");
                }
            });
        }
        // 专题
        if ('FACTOR_SUBJECT' === factorType) {
            $.ajax({
                url: "/subject/manage?versionId=" + versionSelect.val(),
                success: function (data) {
                    $("#mainFactorContainer").html(data);
                },
                error: function () {
                    toastr.error("Error");
                }
            });
        }
        // 版本
        if ('FACTOR_VERSION' === factorType) {
            $.ajax({
                url: "/version/manage?projectId=" + projectSelect.val(),
                success: function (data) {
                    $("#mainFactorContainer").html(data);
                },
                error: function () {
                    toastr.error("Error");
                }
            });
        }
    }

    // #3 脚本初始化~
    //==================================================================================================================

    // 版本初始化
    versionFormGroup.hide();
    // 专题初始化
    subjectFormGroup.hide();

    // 加载因素页面
    listFactor();

    // #4 事件定义区~
    //==================================================================================================================

    // 因素变换事件
    factorSelect.change(function () {
        const factorType = factorSelect.val();
        // 类型
        if ("FACTOR_SECTION" === factorType) {
            versionFormGroup.show();
            subjectFormGroup.show();
        }
        // 专题
        if ("FACTOR_SUBJECT" === factorType) {
            versionFormGroup.show();
            subjectFormGroup.hide();
        }
        // 版本
        if ("FACTOR_VERSION" === factorType) {
            versionFormGroup.hide();
            subjectFormGroup.hide();
        }
        listVersion();
    });

    // 项目变换事件
    projectSelect.change(function () {
        listVersion();
    });

    versionSelect.change(function () {
        listSubject();
    });

    // 专题变换事件
    subjectSelect.change(function () {
        listFactor();
    });

    // 查找按钮
    $("#searchFactor").click(function () {
        listFactor();
    });
});
