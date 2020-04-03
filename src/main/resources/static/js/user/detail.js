/**
 * related html: user/view.html
 */
$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    // #2 函数定义区 start
    //==================================================================================================================

    // 查找所有博客通过用户账号
    function listByUsername() {
        $.ajax({
            type: 'GET',
            url: "/blog/posted?username="+ $("#username").attr("username"),
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    // #3 脚本初始化 start
    //==================================================================================================================

    // 加载用户博客
    listByUsername();

    // #4 事件定义区 start
    //==================================================================================================================

    // 项目列表，点击事件
    $(".project-group .list-group-item").click(function () {
        // 清除标签组合样式
        $('#tagList').find('.btn-info').removeClass('btn-info').addClass('btn-outline-info');
        // 先移除样式所有类型
        $(".project-group .list-group-item").removeClass("active");
        // 后添加样式该类型
        $(this).addClass("active");
        // 加载主页面内容
        $.ajax({
            url: "/axis/product?projectId=" + $(this).attr("projectId"),
            success: function (data) {
                $("#userMainContainer").html(data);
            }
        });
    });

    // 标签列表，点击事件
    $("#tagList .blog-tag").click(function () {
        // 清楚项目列表样式
        $(".project-group .list-group-item").removeClass("active");
        // 去处其他标签点击样式
        $('#tagList').find('.btn-info').removeClass('btn-info').addClass('btn-outline-info');
        // 添加标签点击样式
        $(this).removeClass('btn-outline-info').addClass('btn-info');
        // 加载主页面内容
        $.ajax({
            url: "/tag/" + $(this).attr("tagId") + "/product?productType=PRODUCT_BLOG",
            success: function (data) {
                $("#userMainContainer").html(data);
            }
        });
    });

    // 编辑博客
    $(".blog-update").click(function () {
        window.location.href = "/blogs/update" + "?username=" + $(this).attr("username") + "&code=" + $(this).attr("code");
    });

    // 删除博客
    $(".blog-delete").click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: '/blogs/' + $(this).attr("code") + '?username=' + $(this).attr("username"),
                success: function (data) {
                    if (data.valid) {
                        toastr.success(data.message);
                        window.location.href = "/user/" + username;
                    }
                },
                error: function () {
                    toastr.error("Error!");
                }
            });
        }
    });
});
