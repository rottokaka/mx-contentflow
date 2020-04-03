/**
 * related html: version/list.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================

    // 加载版本列表
    function listVersion() {
        $.ajax({
            url: '/versions',
            data: {
                "topicId": $('#topic').val()
            },
            success: function (data) {
                $("#mainFactorContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 版本动态框隐藏后出发，加载版本列表
    $('#versionModal').on('hidden.bs.modal', function () {
        listVersion();
    });

    // 删除版本点击事件
    $(".version-delete").click(function () {
        // 版本ID
        const versionId = $(this).attr("versionId");
        // 判断：是否删除
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/versions/" + versionId,
                success: function (data) {
                    if (data.valid) {
                        listVersion();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 加载版本新建页面事件
    $(".version-create").click(function () {
        $.ajax({
            url: "/versions/create",
            data: {
                "topicId": $('#topic').val()
            },
            success: function (data) {
                $("#versionModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载版本编辑页面事件
    $(".version-update").click(function () {
        // 版本ID
        const versionId = $(this).attr("versionId");
        $.ajax({
            url: "/versions/update",
            data: {
                "versionId": versionId
            },
            success: function (data) {
                $("#versionModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
