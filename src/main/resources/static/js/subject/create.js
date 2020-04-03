/**
 * related html: subject/create.html
 */
$(function () {
    // #1 变量定义区~
    //=================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 新建专题
    $('#subjectSubmit').click(function () {
        // 主题插入视图对象
        const subjectInsertVo = {
            "name": $('#name').val(),
            "description": $('#description').val(),
            "versionId": $('#subjectVersionId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/subjects',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(subjectInsertVo),
            success: function (data) {
                if (data.valid) {
                    $("#subjectModal").modal('hide');
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
