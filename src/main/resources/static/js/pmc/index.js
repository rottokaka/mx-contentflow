/**
 * related html: pmc/index.html
 */
$(function () {
    // 菜单实践
    $(".list-group .list-group-item").click(function () {
        // 移除其他（默认）菜单项样式，添加该菜单的激活样式
        $(".list-group .list-group-item").removeClass("active");
        $(this).addClass("active");
        // 固定区域加载内容
        $.ajax({
            url: $(this).attr("url"),
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 默认选中菜单第一项
    $(".list-group .list-group-item:first").trigger("click");
});
