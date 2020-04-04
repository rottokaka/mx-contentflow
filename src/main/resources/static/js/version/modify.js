/**
 * related html: version/modify.html
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

    // 插入版本
    $("#versionUpdate").click(function () {
        // 版本ID
        const versionId = $('#versionId').val();
        // 版本对象
        const versionModifyForm = {
            "name": $('#name').val(),
            "description": $('#description').val(),
        };

        $.ajax({
            type: 'PUT',
            url: '/versions/' + versionId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(versionModifyForm),
            success: function (data) {
                if (data.valid) {
                    $("#versionModal").modal('hide');
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
