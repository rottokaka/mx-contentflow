/**
 * related html: section/create.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 插入类型
    $('#sectionSubmit').click(function () {
        // 构建类型表单视图对象
        const sectionFormVo = {
            "name": $('#name').val(),
            "description": $('#description').val(),
            "versionId": $('#sectionVersionId').val(),
            "subjectId": $('#sectionSubjectId').val()
        };

        $.ajax({
            async: true,
            type: 'POST',
            url: '/sections',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sectionFormVo),
            success: function (data) {
                if (data.valid) {
                    $("#sectionModal").modal('hide');
                    toastr.success(data.message);
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
