/**
 * topic/view.html
 */

$(function () {
    // 刷新页面
    function preview() {
        window.location = "/books?topicId="+ $("#topic").val() + "&versionId=" + $("#version").val()
            + "&pattern=" + $("#pattern").val();
    }

    // 模式更改监听
    $("#pattern").change(function () {
        preview();
    });
    // 版本监听
    $("#version").change(function () {
        preview();
    });
});