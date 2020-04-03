/**
 * related html: tag/detail.html
 */

$(function () {
    // #1 变量定义区 start
    //==================================================================================================================

    // 博客标签纽
    const blogTab = $('#pills-blog-tab');
    // 用户标签纽
    const userTab = $('#pills-user-tab');
    // 当前标签ID
    const tagId = $('#currentTag').attr("tagId");

    // #2 函数定义区 start
    //==================================================================================================================

    // 页面初始化
    function init() {
        blogTab.tab('show');
        listBlogByTag();
    }

    // 加载博客列表
    function listBlogByTag() {
        $.ajax(
            {
                url: '/tags/' + tagId + "/blogs",
                success: function (data) {
                    $('#pills-blog').html(data);
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            }
        );
    }

    // 加载用户列表
    function listUserByTag() {
        $.ajax(
            {
                url: '/tags/' + tagId + "/users",
                success: function (data) {
                    $('#pills-user').html(data);
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            }
        );
    }

    // #3 脚本初始化 start
    //==================================================================================================================

    // 默认显示博客列表
    // init();

    // #4 事件定义区 start
    //==================================================================================================================

    // 导航“博客”事件，在内容展示之前触发
    /*blogTab.on('show.bs.tab', function () {
        listBlogByTag();
    });*/
    // 导航“用户”事件，在内容展示之前触发
    /*userTab.on('show.bs.tab', function () {
        listUserByTag();
    });*/

    // 加载标签视图页面 view_self
    /*$.ajax(
        {
            contentType: 'GET',
            url: '/tagName/' + $('#currentTag').attr("tagName"),
            contentType: 'application/json',
            data: {
                async: 'true'
            },
            success: function (data) {
                $('#mainContainer').html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        }
    );*/
});
