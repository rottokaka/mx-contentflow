/**
 * related html: tag/modify.html
 */
$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================

    // 标签初始化
    $('.form-control-tag').tagEditor({
        initialTags: [],
        delimiter: ', ',
        forceLowercase: false,
        animateDelete: 0,
        placeholder: '输入标签...'
    });

    // #4 事件定义区~
    //==================================================================================================================

    // 新建标签
    $("#updateTag").click(function () {
        // 标签ID
        const tagId = $('#tagId').val();
        // 标签更新视图对象
        const tagUpdateVO = {
            "property": $('#propertySelect').val(),
            "name": $('#name').val(),
            "description": $('#description').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/tags/' + tagId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tagUpdateVO),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    $("#tagModal").modal('hide');
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
