/**
 * related html: project/create.html
 */
$(function () {
    // #1 变量定义区~
    //=================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================

    // 选择框初始化
    $(".form-control-chosen").chosen();

    // #4 事件定义区~
    //==================================================================================================================

    // 新建项目
    $('#projectSubmit').click(function () {
        const projectFormVo = {
            "name": $('#name').val(),
            "description": $('#description').val(),
            "website": $('#website').val(),
            "aboveProjectId": $('#aboveProjectSelect').val(),
            "scope": $('#scopeSelect').val(),
            "contributionNotAllowed": $('#contributionNotAllowed').val()
        };
        $.ajax({
            type: 'POST',
            url: '/projects',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(projectFormVo),
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
