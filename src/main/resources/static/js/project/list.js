/**
 * related html: project/list.html
 */

$(function () {

    // #1 变量定义区 start
    //==================================================================================================================
    // #2 函数定义区 start
    //==================================================================================================================

    // #3 脚本初始化 start
    //==================================================================================================================

    // #4 事件定义区 start
    //==================================================================================================================

    // 话题列表
    $(".list-group .list-group-item").click(function () {
        // 先移除样式所有类型
        $(".list-group .list-group-item").removeClass("active");
        // 后添加样式该类型
        $(this).addClass("active");
        // 加载主页面内容
        $.ajax({
            url: "/topics/" + $(this).attr("topicId") + "/blogs",
            success: function (data) {
                $("#mainContainer").html(data);
            }
        });
    });

    // 默认选中菜单第一项
    $(".list-group .list-group-item:first").trigger("click");

});
