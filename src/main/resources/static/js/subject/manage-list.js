/**
 * related html: subject/manage-list.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================

    // 版本ID
    const versionId = $('#versionId').val();
    // 主题模态框
    const subjectModal = $('#subjectModal');
    // 主题模态框容器
    const subjectModalContainer = $('#subjectModalContainer');

    // #2 函数定义区~
    //==================================================================================================================

    // 列出主题
    function loadSubjectManage() {
        $.ajax({
            url: '/subject/manage?versionId=' + versionId,
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
    subjectModal.on('hidden.bs.modal', function () {
        loadSubjectManage();
    });

    // 更新主题
    subjectModalContainer.on('click', '#subjectUpdate', function () {
        // 主题ID
        const subjectId = $('#subjectId').val();
        // 主题插入视图对象
        const subjectModifyForm = {
            "name": $('#name').val(),
            "description": $('#description').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/subjects/' + subjectId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(subjectModifyForm),
            success: function (data) {
                if (data.valid) {
                    subjectModal.modal('hide');
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


    // 删除主题点击事件
    // Ajax中Put和Delete请求传递参数无效
    // 1、使用地址重写的方法传递参数。2、配置web.xml项目环境。
    // https://blog.csdn.net/u012737182/article/details/52831008
    $(".subject-delete").click(function () {
        // 主题ID
        const subjectId = $(this).attr("subjectId");
        // 判断：是否删除
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/subjects/" + subjectId,
                success: function (data) {
                    if (data.valid) {
                        loadSubjectManage();
                        subjectModal.modal('hide');
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
    $(".subject-create").click(function () {
        $.ajax({
            url: "/subject/create?versionId=" + versionId,
            success: function (data) {
                $("#subjectModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载主题更新页面点击事件
    $(".subject-update").click(function () {
        // 主题ID
        const subjectId = $(this).attr("subjectId");
        $.ajax({
            url: "/subject/modify?subjectId=" + subjectId,
            success: function (data) {
                $("#subjectModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
