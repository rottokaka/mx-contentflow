$(function () {

    // 单选框初始化
    $('.form-control-chosen').chosen();

    // markdown 编辑器
    const testEditor = editormd.markdownToHTML("preview", {//注意：这里是上面DIV的id
        path: "../../lib/", // Autoload modules mode, codemirror, marked... dependents libs path
        htmlDecode: "style,script,iframe",
        emoji: true,
        taskList: true,
        tex: true, // 默认不解析
        flowChart: true, // 默认不解析
        codeFold: true,
    });

    function searchByKeyWord(pageHome, pageSize) {
        $.ajax({
            url: "/blog",
            contentType: 'application/json',
            data: {
                "async": true,
                "pageHome": pageHome,
                "pageSize": pageSize,
                "keyword": $("#keyword").val()
            },
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    $("#searchSubmit").click(function () {
        searchByKeyWord(0, _pageSize);
    });

    // 编辑博客
    $(".blog-update").click(function () {
        $.ajax({
            url: '/blog/' + $(this).attr("code") + '/update',
            data: {
                "username": username
            },
            success: function (data) {
                $("#blogModalContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 删除博客
    $(".blog-delete").click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: '/blog/' + $(this).attr("code") + '/delete?username=' + $(this).attr("username"),
                success: function () {
                    toastr.success("删除成功！")
                },
                error: function () {
                    toastr.error("Error!");
                }
            });
        }
    });
});