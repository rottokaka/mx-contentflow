/**
 * related html: project/modify.html
 */
$(function () {
    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================

    // 选择框初始化
    $(".form-control-chosen").chosen();

    // #4 事件定义区~
    //==================================================================================================================

    // 更新项目
    $('#projectUpdate').click(function () {
        const projectId = $('#projectId').val();
        const projectModifyForm = {
            "name": $('#name').val(),
            "description": $('#description').val(),
            "website": $('#website').val(),
            "aboveProjectId": $('#projectAboveSelect').val(),
        };
        $.ajax({
            type: 'PUT',
            url: '/projects/' + projectId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(projectModifyForm),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    $("#projectModal").modal('hide');
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
