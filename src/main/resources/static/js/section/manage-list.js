/**
 * related html: section/manage-list.html
 */

$(function () {

    // #1 变量定义区~
    //=================================================================================================================

    // 版本ID
    const versionId = $('#versionId').val();
    // 专题ID
    const subjectId = $('#subjectId').val();
    // 类型模态框
    const sectionModal = $('#sectionModal');
    // 类型模态框容器
    const sectionModalContainer = $('#sectionModalContainer');

    // #2 函数定义区~
    //==================================================================================================================

    // 加载类型列表
    function listSection() {
        $.ajax({
            url: '/section/manage?versionId=' + versionId + "&subjectId=" + subjectId,
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

    // 更新类型
    sectionModalContainer.on('click', '#sectionUpdate', function () {
        // 类型ID
        const sectionId = $('#sectionId').val();
        // 类型更新视图对象
        const sectionModifyForm = {
            "name": $('#name').val(),
            "description": $('#description').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/sections/' + sectionId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sectionModifyForm),
            success: function (data) {
                if (data.valid) {
                    $("#sectionModal").modal('hide');
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

    // 类型动态框隐藏时触发事件，加载类型列表
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    sectionModal.on('hidden.bs.modal', function () {
        listSection();
    });
    // 删除类型
    // Ajax中Put和Delete请求传递参数无效
    // 1、使用地址重写的方法传递参数。2、配置web.xml项目环境。
    // https://blog.csdn.net/u012737182/article/details/52831008
    $(".section-delete").click(function () {
        // 判断：是否确认删除
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/sections/" + $(this).attr("sectionId"),
                success: function (data) {
                    if (data.valid) {
                        listSection();
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
    $(".section-create").click(function () {
        $.ajax({
            url: "/section/create?versionId=" + versionId + "&subjectId=" + subjectId,
            success: function (data) {
                $("#sectionModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载类型更新页面点击事件
    $(".section-update").click(function () {
        // 类型ID
        const sectionId = $(this).attr("sectionId");
        $.ajax({
            url: "/section/modify?sectionId=" + sectionId,
            success: function (data) {
                $("#sectionModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
