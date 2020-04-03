/**
 * related html: iaa/index.html
 */
$(function () {
    // 菜单实践
    $(".list-group .list-group-item").click(function () {
        // 获取该菜单的url
        const url = $(this).attr("url");
        // 移除其他（默认）菜单项样式，添加该菜单的激活样式
        $(".list-group .list-group-item").removeClass("active");
        $(this).addClass("active");
        // 固定区域加载菜单内容
        $.ajax({
            url: url,
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 默认选中菜单第一项
    $(".list-group .list-group-item:first").trigger("click");
});
