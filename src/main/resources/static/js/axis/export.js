/**
 * related html: root/factor.html
 */

$(function () {

    // 点击查看事件，博客预览
    $(".blog-view").click(function () {
        $.ajax({
            type: 'GET',
            url: "/blog/" + $(this).attr("blogId") + "/self",
            success: function (data) {
                $("#blogViewModalContainer").html(data);
            }
        });
    });

});
