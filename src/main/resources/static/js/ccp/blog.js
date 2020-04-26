/**
 * related html: ccp/blog.html
 */
$(function () {
    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================

    // 列出博客
    function listBlog() {
        $.ajax({
            typ: 'GET',
            url: '/ccp/blog',
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        })
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 博客配置动态框关闭事件
    $('#blogConfigModal').on('hidden.bs.modal', function () {
        listBlog();
    });

    // 预览博客
    $(".blog-view").click(function () {
        $.ajax({
            type: 'GET',
            url: "/blog/" + $(this).attr("blogId") + "/content",
            success: function (data) {
                $("#blogModalContainer").html(data);
            }
        });
    });

    // 加载博客配置页面
    $(".blog-config").click(function () {
        const blogId = $(this).attr("blogId");
        $.ajax({
            type: 'GET',
            url: "/blog/" + blogId + "/config/modify",
            success: function (data) {
                $("#blogConfigModalContainer").html(data);
            }
        });
    });

    // 更新博客配置
    $('#submitBlogConfig').click(function () {
        // 博客配置修改ID
        const blogConfigModifyId = $('#blogConfigModifyId').val();
        // 范围状态
        const scope = $('#scopeState').val();
        // 是否允许收录字段
        let collectionNotAllowed = 0;
        if ($('#collectionNotAllowed').prop("checked")) {
            collectionNotAllowed = 1;
        }
        // 构建博客配置更新对象
        const blogConfigModifyForm = {
            "scope": scope,
            "collectionNotAllowed": collectionNotAllowed
        };

        $.ajax({
            type: 'PATCH',
            url: '/blogs/' + blogConfigModifyId + "/config",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(blogConfigModifyForm),
            success: function (data) {
                if (data.valid) {
                    $("#blogConfigModal").modal('hide');
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 编辑博客
    $(".blog-update").click(function () {
        window.location.href = "/blog/" + $(this).attr("blogId") + "/modify";
    });

    // 删除博客
    $(".blog-delete").click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: '/blogs/' + $(this).attr("blogId"),
                success: function (data) {
                    if (data.valid) {
                        listBlog();
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
});
