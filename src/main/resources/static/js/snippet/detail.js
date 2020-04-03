/**
 * related html: snippet/detail.html
 */
$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    // 片段ID
    const snippetId = $('#snippetId').val();

    // #2 函数定义区 start
    //==================================================================================================================

    // 请求片段页面
    function loadSnippet() {
        $.ajax({
            type: "GET",
            url: "/snippet/" + snippetId + "/content",
            success: function (data) {
                $("#snippetContainer").html(data);
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================

    // 加载博客
    loadSnippet();

    // #4 事件定义区 start
    //==================================================================================================================

    // 获取片段修改页面
    $('.snippet-update').click(function () {
        window.location = "/snippet/" + snippetId + "/modify";
    });

    // 删除片段
    $('.snippet-delete').click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: '/snippet/' + snippetId,
                success: function (data) {
                    if (data.valid) {
                        toastr.success(data.message);
                        window.location = data.data;
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    })

});
