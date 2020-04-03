/**
 * related html: pmc/project.html
 */

$(function () {
    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================

    // 列出项目
    function listProject() {
        $.ajax({
            url: "/pmc/project",
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 搜索项目
    $('#projectSearch').click(function () {
        listProject();
    });

    // 项目动态框关闭事件
    $('#projectModal').on('hidden.bs.modal', function () {
        listProject();
    });

    // 项目配置动态框关闭事件
    $('#projectConfigModal').on('hidden.bs.modal', function () {
        listProject();
    });

    // 删除项目
    $('.project-delete').click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/projects/" + $(this).attr("projectId"),
                success: function (data) {
                    if (data.valid) {
                        // 刷新列表
                        toastr.success(data.message);
                        listProject();
                        $("#projectModal").modal('hide');
                    } else {
                        toastr.error(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 创建项目
    $('.project-create').click(function () {
        $.ajax({
            url: "/project/create",
            success: function (data) {
                $("#projectModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 编辑项目
    $('.project-update').click(function () {
        const projectId = $(this).attr("projectId");
        $.ajax({
            type: 'GET',
            url: "/project/" + projectId + "/modify",
            success: function (data) {
                $("#projectModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 编辑项目配置
    $('.project-config').click(function () {
        const projectId = $(this).attr("projectId");
        $.ajax({
            type: 'GET',
            url: "/project/" + projectId + "/config/modify",
            success: function (data) {
                $("#projectConfigModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });
});
