/**
 * related html：subject/list.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================

    // 版本ID
    const versionId = $('#version').val();
    // 主题模态框
    const themeModal = $('#themeModal');

    // #2 函数定义区~
    //==================================================================================================================

    // 列出主题
    function listTheme() {
        $.ajax({
            url: '/themes',
            data: {
                "versionId": versionId
            },
            success: function (data) {
                $("#mainFactorContainer").html(data);
            },
            error: function () {
                toastr.error("Error!");
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 主题动态框关闭后触发事件，加载主题列表
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    themeModal.on('hidden.bs.modal', function () {
        listTheme();
    });

    // 删除主题点击事件
    // Ajax中Put和Delete请求传递参数无效
    // 1、使用地址重写的方法传递参数。2、配置web.xml项目环境。
    // https://blog.csdn.net/u012737182/article/details/52831008
    $(".theme-delete").click(function () {
        // 主题ID
        const themeId = $(this).attr("themeId");
        // 判断：是否删除
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/themes/" + themeId,
                success: function (data) {
                    if (data.valid) {
                        listTheme();
                        themeModal.modal('hide');
                        toastr.success("删除成功");
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

    // 加载主题创建页面点击事件
    $(".theme-create").click(function () {
        $.ajax({
            async: true,
            url: "/themes/create",
            data: {
                "versionId": versionId
            },
            success: function (data) {
                $("#themeModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载主题更新页面点击事件
    $(".theme-update").click(function () {
        // 主题ID
        const themeId = $(this).attr("themeId");
        $.ajax({
            async: true,
            url: "/themes/update",
            data: {
                "themeId": themeId
            },
            success: function (data) {
                $("#themeModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
