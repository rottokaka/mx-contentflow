/**
 * related html: section/list.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================

    // 加载类型列表
    function listType() {
        $.ajax({
            url: '/types',
            data: {
                "username": username,
                "versionId": $('#version').val()
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

    // 类型动态框隐藏时触发事件，加载类型列表
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    $('#typeModal').on('hidden.bs.modal', function () {
        listType();
    });

    // 保存类型
    $("#submitType").click(function () {
        const type = {
            "id": $('#contentId').val(),
            "name": $('#name').val(),
            "description": $('#description').val(),
            "version": {
                "id": $('#version').val()
            },
            "user": {
                "username": username
            }
        };
        $.ajax({
            async: true,
            type: 'POST',
            url: '/types',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(type),
            success: function (data) {
                if (data.valid) {
                    $("#typeModal").modal('hide');
                    toastr.success(data.message);
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error!");
            }
        });
    });

    // 删除类型
    // Ajax中Put和Delete请求传递参数无效
    // 1、使用地址重写的方法传递参数。2、配置web.xml项目环境。
    // https://blog.csdn.net/u012737182/article/details/52831008
    $(".contentType-delete").click(function () {
        // 判断：是否确认删除
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                async: true,
                type: 'DELETE',
                url: "/types/" + $(this).attr("typeId"),
                data: {
                    "username": username
                },
                success: function (data) {
                    if (data.valid) {
                        listType();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message)
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 获取创建类型页面点击事件
    $(".contentType-create").click(function () {
        $.ajax({
            url: "/types/create",
            data: {
                "versionId": $('#version').val()
            },
            success: function (data) {
                $("#typeModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载类型更新页面点击事件
    $(".contentType-update").click(function () {
        // 类型ID
        const typeId = $(this).attr("typeId");
        $.ajax({
            url: "/types/update",
            data: {
                "typeId": typeId
            },
            success: function (data) {
                $("#typeModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
