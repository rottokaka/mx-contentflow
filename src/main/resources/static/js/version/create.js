/**
 * related html: version/create.html
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

    // 保存版本
    $("#versionSubmit").click(function () {
        // 版本对象
        const versionCreateForm = {
            "name": $('#name').val(),
            "description": $('#description').val(),
            "projectId": $('#projectId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/versions',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(versionCreateForm),
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
