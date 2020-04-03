/**
 * related html: tag/card_list.html
 */

$(function () {

    /*// 话题列表
    $(".list-group .list-group-item").click(function () {
        // 先移除样式所有类型
        $(".list-group .list-group-item").removeClass("active");
        // 后添加样式该类型
        $(this).addClass("active");
        // 加载主页面内容
        $.ajax({
            contentType: 'GET',
            url: '/tagName/' + $(this).attr("tagName"),
            contentType: 'application/json',
            data: {
                async: 'true'
            },
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 默认选中菜单第一项
    $(".list-group .list-group-item:first").trigger("click");*/
});
