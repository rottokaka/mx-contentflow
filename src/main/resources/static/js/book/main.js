/**
 * related html: book/index.html
 */

$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    // #2 函数定义区 start
    //==================================================================================================================

    // 列出话题版本
    function listVersion(topicId) {
        $.ajax({
            type: 'GET',
            url: "/versions/topic",
            data: {
                "topicId": topicId
            },
            success: function (data) {
                $("#version").empty();
                $.each(data, function (i, value) {
                    $("#version").append("<option value=\"" + value.id + "\">" + value.name + "</option>");
                });
                viewBook();
            }
        });
    }

    // 获取图书页面
    function viewBook() {
        $.ajax({
            url: "/books/view",
            data: {
                "versionId": $("#version").val(),
                "pattern": $("#pattern").val()
            },
            success: function (data) {
                $("#bookMainContainer").html(data);
            }
        });
    }

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
        listVersion($(this).attr("topicId"));
    });

    // 默认选中菜单第一项
    $(".list-group .list-group-item:first").trigger("click");

    // 版本监听
    $("#version").change(function () {
        viewBook();
    });

    // 模式更改监听
    $("#pattern").change(function () {
        viewBook();
    });
});