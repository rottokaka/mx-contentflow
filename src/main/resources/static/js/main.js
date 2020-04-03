$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    const blogType = 1;

    // #2 函数定义区 start
    //==================================================================================================================


    // #3 脚本初始化 start
    //==================================================================================================================
    // #4 事件定义区 start
    //==================================================================================================================

    // 博客搜索
    $("#searchSubmit").click(function () {
        $.ajax({
            url: "/blogs",
            contentType: 'application/json',
            data: {
                "async": true,
                "keyword": $("#keyword").val()
            },
            success: function (data) {
                $("#wrapper").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 博客创建：快速提交
    $("#snippet").click(function () {
        window.location.href = "/snippet/create";
    });

    // 博客创建：详细提交
    $("#blog").click(function () {
        window.location.href = "/blog/create";
    });

    // 文件上传
    $(".file-upload").click(function () {
        window.location.href = "/file/index"
    });
});
